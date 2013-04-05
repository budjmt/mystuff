#!usr/bin/python
from pymongo import Connection

global con
con = Connection("mongo.stuycs.org")
#not using lobbies anymore, setting them all to -1.
global db
global col

def startup():
	global db
	db = con.admin
	global res
	res = db.authenticate("ml7","ml7")
	db = con["macguffin"]
	global col
	col = db["matches"]

def inprogress():#checks if a game has been set up
	global col
	return len([x for x in col.find({"type":"g"})]) == 1

def setupgame(game, _map, lobby=-1):#makes game
	if inprogress():
		return False
	global col
	game = {"type" : "g", "game": str(game), "map": int(_map), "lobby": lobby}
	col.insert(game)

def logout(pname=""):
	s = [x["spot"] for x in col.find({"type":"p","pname":pname})][0]
	while len([x for x in col.find({"type":"p","spot":int(s+1)})]) != 0:
		col.update({"type" : "p", "spot":int(s+1)}, {"$set": {"spot":int(s)}})
		s+= 1
	col.remove({"type":"p","pname":pname})
	return True

def getgame(lobby=-1):#returns tuple of game, map, and lobby number(should always be -1).
	global col
	res = [(str(x["game"]), int(x["map"]), int(x["lobby"])) for x in col.find({"type":"g", "lobby":lobby})]
	if len(res) == 0:
		return False
	return res[0]

def closegame(lobby=-1):#logs everyone out too, they'll have an option to log back in
	global col
	lob = col.find({"lobby": lobby})
	col.drop()

def addplayer(pname):
	global col
	r = col.find({"type" : "p", "pname":str(pname)})
	for p in r:
		return False
	player = {"type" : "p", "team":int(-1), "pname": str(pname), "pos": [-1, -1], "lobby": -1, "items": int(0), "spot" : len([x for x in col.find({"type":"p"})])}
	col.insert(player)
	return True

def addmacguffin(x,y):
	global col
	macg = {"type" : "m", "pos": [x, y], "lobby": -1}
	col.insert(macg)

def remmacguffin(x,y):
	global col
	macg = {"type" : "m", "pos": [x, y], "lobby": -1}
	col.remove(macg)

def retmacguffin():
	global col
	if len([x for x in col.find({"type":"m"})]) > 0:
		return [x["pos"] for x in col.find({"type":"m"})]
	return []

"""b4 col.rem
	res = col.find({"lobby":lobby, "type" : "p"})
	for p in res:
		if int(p["spot"]) > int(player["spot"]):
			p["spot"] = p["spot"] - 1
def removeplayer(pname, lobby=-1):#not used
	global col
	player = col.find({"type" : "p","pname":pname})
	col.remove(player)


"""

def getplayer(pname):#returns tuple of players name, position, and spot
	global col
	res = [(str(x["pname"]), x["pos"], int(x["spot"]), int(x["items"]), int(x["team"])) for x in col.find({"type" : "p","pname":pname})]
	if len(res) == 1:	
		return res[0]
	return False#if the player doesn't exist

def placeteam(pname,t=-1):
	global col
	col.update({"type" : "p", "pname":pname}, {"$set": {"team":int(t)}})

def moveplayer(pname, x=-1, y=-1, item=-1):
	global col
	x2 = float(x)
	y2 = float(y)
	item2 = int(item)
	if item == -1:
		res = [z["items"] for z in col.find({"type" : "p","pname":pname})]
		item2 = int(res[0])
	if x == -1:
		res = [z["pos"] for z in col.find({"type" : "p","pname":pname})]
		x2 = float(res[0][0])
	if y == -1:
		res = [z["pos"] for z in col.find({"type" : "p","pname":pname})]
		y2 = float(res[0][1])
	pos = [x2,y2]
	col.update({"type" : "p", "pname":pname}, {"$set": {"pos": pos, "items":item2}})
	

def get_players_in_lobby(lobby=-1):#returns list of tuples of names and positions of players in game 
	res = col.find({"lobby":lobby, "type" : "p"})
	return [[str(x["pname"]), x["pos"], int(x["items"]), int(x["team"])] for x in res if int(x["spot"]) < 4]

