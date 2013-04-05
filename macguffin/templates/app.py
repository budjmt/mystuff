#!/usr/bin/python
from flask import Flask, render_template, session, url_for, redirect, request
import json
import db
import os

app=Flask(__name__)
app.secret_key = os.urandom(24)#sets random secret key

@app.route("/",methods=["GET","POST"])
def index():#choose player name
	if request.method=="GET":
		if "player" not in session:
			return render_template("loginpage.html")
		elif db.getplayer(session["player"]) == False:
			return render_template("loginpage.html", pname = session["player"])#for when the player gets redirected here after the game's done
		else:
			return render_template("loginpage.html", pname = session["player"], loggedin= True)#for when the player opens the page when they're already logged in
	else:
		if db.addplayer(str(request.form["name"])):#addplayer returns false if there's already a player with name "name"
			if "player" in session:#logs the user out if they're trying to log in while already being logged in
				session.pop("player", None)
			session["player"] = str(request.form["name"])
			if db.inprogress():#if a game has already been set up(map and gametype chosen), redirects to game page.
				return redirect(url_for("game"))
			else:
				return redirect(url_for("playlist"))
		else: 
			return render_template("loginpage.html", failed = True)#log in has failed

@app.route("/playlist",methods=["GET","POST"])
def playlist():#where the map and gametype are chosen (by 1st player to log in)
	if "player" not in session:#player never logged in
		return redirect(url_for("index"))
	elif db.getplayer(session["player"]) == False:#player never logged back in after the last game ended
		return redirect(url_for("index"))
	elif db.inprogress():#game already set up
		return redirect(url_for("game"))
	elif request.method == "GET":
		 return render_template("mapselectionpage.html", user = session["player"])
	else: 
		db.setupgame("game", int(request.form["map"]), -1)
		return redirect(url_for("game"))

@app.route("/game",methods=["GET","POST"])
def game():
	if "player" not in session:#player never logged in
		return redirect(url_for("index"))
	elif db.getplayer(session["player"]) == False:#player never logged back in after the last game ended
		return redirect(url_for("index"))
	elif db.inprogress():
		if int(db.getplayer(session["player"])[2]) < 4:#checks if the player was one of the first 4 to log in
			return render_template("gamepage.html", playing = True, game = (db.getgame())[0], _map = (db.getgame())[1], player=session["player"])
		else:
			return render_template("gamepage.html", playing = False, game = (db.getgame())[0], _map = (db.getgame())[1], player=session["player"])
	else:
		return redirect(url_for("playlist"))#redirects to playlist if the game's not set up yet.

@app.route("/getMap", methods=["GET","POST"])
def getMap():
	return str((db.getgame())[1]) #sends map number

@app.route("/updatePositions", methods=["GET","POST"])
def updatePositions():
	ps = db.get_players_in_lobby()
	"""
	ret = {}
	i = 0
	for p in ps:
		ii = 0
		ret2 = {}
		while ii < len(p):
			if ii !=1:
				ret[ii] = p[ii]
			else:
				ret[ii] = {0:p[ii][0],1:p[ii][1]}
			ii+= 1
		ret[i] = ret2
		i+= 1
	return json.dumps(ret) #sends all player names and coordinates
	"""
	return json.dumps(ps)


@app.route("/updatePlayer", methods=["GET","POST"])
def updatePlayer():
	db.moveplayer(str(session["player"]), float(request.args.get("x", -1)), float(request.args.get("y", -1)), int(request.args.get("item", -1)))#gets player's name and coordinates

@app.route("/spawn", methods=["GET","POST"])
def spawn():
	db.moveplayer(float(request.args.get("pname", "")), float(request.args.get("x", -1)), float(request.args.get("y", -1)), 0)#gets player's name and coordinates

@app.route("/placeTeam", methods=["GET","POST"])
def placeTeam():
	db.placeteam(session["player"], int(request.args.get("t", -1)))

@app.route("/placeMacguffin", methods=["GET","POST"])
def placeMacguffin():
	db.addmacguffin(float(request.args.get("x", -1)), float(request.args.get("y", -1)))#gets macg's coordinates

@app.route("/removeMacguffin", methods=["GET","POST"])
def removeMacguffin():
	db.remmacguffin(float(request.args.get("x", -1)), float(request.args.get("y", -1)))#gets macg's coordinates

@app.route("/updateMacguffins", methods=["GET","POST"])
def updateMacguffins():
	return json.dumps(db.retmacguffin())#gets macgs' coordinates

@app.route("/endgame", methods=["GET","POST"])
def endgame():
	if request.args.get("gameover", False) == True:
		db.closegame()

@app.route("/getSelf", methods=["GET","POST"])
def getSelf():
	return str(session["player"])

@app.route("/logout", methods=["GET","POST"])
def logout():
	return str(logout(str(session["player"])))

if __name__=="__main__":
    db.startup()
    db.closegame()
    app.run(host="0.0.0.0", port=6399)#port#?
