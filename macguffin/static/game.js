/*
  What needs to be done on my end:
  player movement
  logging stuff in nevin's database
  using amanpreet's stuff to check collisions
  loading the players and macguffins into the svg
  scoring, timer

*/

var svgns = "http://www.w3.org/2000/svg";

function addSvg(container, obtype, obname) {
    var shape = document.createElementNS(svgns, obtype);
    $(shape).attr("id", obname);
    $(container).append(shape);
}

var TIME_LIMIT = 2;
var NUM_REQ = 30;
var NUM_DROP = 5;
var colors = ["red", "blue", "green", "yellow", 
	      "orange", "purple", "cyan", "majenta"];

var teams = [[],[]];//empty 2D array to hold the appropriate players
var macguffins = [];//all the macguffins coordinates go here
var mapnum;
$.getJSON('/getMap', function(num) { mapnum = num; });//map number
var poly;

var player = function(number) {
    this.num = number;
    this.color = colors[num];
    this.x;//appropriate location x for this.num
    this.y;//appropriate location y for this.num
    this.name;//player name, set at login
    this.held = 0;//number of macguffins held
    this.picker = null;//becomes index in teams when added there
};

player.prototype.makeSelf = function() {
//inserts the player into the svg
    id = this.name;
    you = $("#" + id);
    you.attr('x', this.x);
    you.attr('y', this.y);
    you.attr('width', 15);
    you.attr('height', 15);
    you.attr('fill', this.color);
    you.attr('stroke', 'black');
    you.attr('stroke-width', 2);
    svg.append(you);
};

player.prototype.eraseSelf = function() {
//erases the player from the svg
    id = this.name;
    svg.remove($("#" + id));
};

function getSelf() {
    var pname = "";
    $.getJSON('/getSelf', function(name) { pname += name; });
    console.log(pname);
    for(var t in teams){
	for(var p in t){
	    if(p.name == pname)
		return p;
	}
    }
}

function addMDB(cx, cy) {
    $.getJSON('/placeMacguffin', {x:cx,y:cy}, function(){});
    renderM();
}
    
function removeMDB(cx, cy) {
    $.getJSON('/removeMacguffin', {x:cx,y:cy}, function(){});//for these 2, cx and cy are the coordinates of the macg being                            
    //placed/removed
    renderM();
}
 
function renderM() {
//renders the macguffins
    $.getJSON('/updateMacguffins',function (macgs) {
	macguffins = macgs;
	removeMacguffins();
	makeMacguffins();//render the macg
    });
}

function renderT() {
//renders the teams
    updateTeams();
    for(var t = 0; t < teams.length; t++){
	for(var i = 0; i < t.length; i++) {
	    (teams[t][i]).eraseSelf();
	    (teams[t][i]).makeSelf();
	}
    }
}

function updateTeams() {
    $.getJSON('/updatePositions',function (players) {
	for (var i in players) {
	    for(var t in teams) {
		for(var p in t) {
		    if(p.name == i[0]) {
			//locate the member of teams with i[0] for name
			p.x = i[1][0];
			p.y = i[1][1];
			//give them the coords from i[1]
		    }
		}
	    }
	    ////i[1] gives the xy coordinates in an array (i.e. [10,100]) 
            //i[0] gives their names, if you need that
	}
    });
}

player.prototype.move = function(dir, axis) {
    var disp = 0;
    if(dir)
	disp++;
    else
	disp--;
    if(axis)
	this.x += dir;
    else
	this.y += dir;
    var cx = this.x;
    var cy = this.y;
    
    $.getJSON('/updatePlayer', {x:cx,y:cy,item:this.held}, function(){});
    renderM();
    renderT();
    checkPCols(this, dir, axis);
    checkMCols(this, dir, axis);
}

function makePlayers() {
//generates the players' data and images
    $.getJSON('/updatePositions',function (players) {
	var a = 0;
	var zerox = 0;
	var zeroy = 0;
	var onex = 0;
	var oney = 0;
	if (mapnum == 1) {
	    zerox = 30;
	    zeroy = 20;
	    onex = 655;
	    oney = 470;
	}
	else if (mapnum == 2) {
	    zerox = 20;
	    zeroy = 245;
	    onex = 670;
	    oney = 245;
	}
	else {
	    zerox = 87;
	    zeroy = 20;
	    onex = 670;
	    oney = 402;
	}

	for(j = 0;j<3;j++) {
	    teams[0][j] = 0;
	    teams[1][j] = 0;
	}
	
	for (var i in players){
	    if (a % 2 == 0){
		teams[0][a/2] = new player(a);
		teams[0][a/2].name = i;
		teams[0][a/2].picker = 0;
		teams[0][a/2].x = zerox;
		teams[0][a/2].y = zeroy;
	    }
	    else {
		teams[0][(a-1)/2] = new player(a);
		teams[1][(a-1)/2].name = i;
		teams[1][(a-1)/2].picker = 1;
		teams[1][(a-1)/2].x = onex;
		teams[1][(a-1)/2].y = oney;
	    }
	    a++;
	}
    });
    renderT();
}

function makeMacguffins(){
    for (var i = 0; i < macguffins.length; i++) {
	id = i.toString();    
	item = document.createElementNS(svgNS, 'circle');
	item.attr('id', id);
	item.attr('cx', macguffins[i][0]);
	item.attr('cy', macguffins[i][1]);
	item.attr('r', 10);
	item.attr('fill', colors[6]);
	svg.append(item);
    }
}

function removeMacguffins(i){
    var id = i.toString();
    svg.remove($("#" + id));
}

function removeMacguffins(){
    for (var i = 0; i < macguffins.length; i++)
	removeMacguffins(i);
}

function makeMacs() {
//generates the macguffins
    var randomx = 0;
    var randomy = 0;
    var numSpawn = 0;
    var iSpawn;

    if (mapnum == 1) {
	numSpawn = 4;
	iSpawn = getIone();
    }
    else if (mapnum == 2) {
	numSpawn = 5;
	iSpawn = getItwo();
    }
    else {
	numSpawn = 5;
	iSpawn = getIthree();
    }  

    for(var i = 0; i < NUM_REQ; i++) {
	zone = Math.floor(Math.random() * numSpawn);
	randomx = Math.floor((Math.random() * 50) + iSpawn[zone][0][0] - 5);
	randomy = Math.floor((Math.random() * 50) + iSpawn[zone][0][1] - 5);
	$.getJSON('/update',{x:randomx,y:randomy},function() {});	
    }
    
    renderM();
}

function canMove(me, dir, axis) {
    var poly = [];
	    if (mapnum == 1){
		poly = getPolyOne();
	    }

	    else if (mapnum == 2){
		poly = getPolyTwo();
	    }
	    else{
		poly = getPolyTwo();
	    }
    var crash = false;
    if(dir == 0 && axis == 1){
	crash = pointpoly(poly[i], x-3, y-1) || pointpoly(poly[i], x-3, y+h+1);
    }
    if (dir == 0 && axis == 0){
	crash = pointpoly(poly[i], x-1, y-3) || pointpoly(poly[i], x+w+1, y-3);
    }
    if (dir == 1 && axis == 1){
	crash = pointpoly(poly[i], x+w+2, y-1) || pointpoly(poly[i], x+w+2, y+h+1);
    }
    if (dir == 1 && axis == 0){
	crash = pointpoly(poly[i], x+w+2, y-1) || pointpoly(poly[i], x+w+2, y+h+1);
    }
    crash = !crash;
    return crash;
}

function getMove(keySig) {
//registers the keystroke and moves the character in the appropriate direction
    var dir = -1;
    var axis = -1;
    
    if(keySig == 37) { axis = 1;dir = 0; }//left
    else if(keySig == 38) { axis = 0;dir = 0; }//up
    else if(keySig == 39) { axis = 1;dir = 1; }//right
    else if(keySig == 40) { axis = 0;dir = 1; }//down

    if(dir < 0 || axis < 0)
	return;
    if(canMove(getSelf(), dir, axis))
	getSelf().move(dir, axis);
    else
	return;
}

function checkPCols(me, dir, axis) {
//checks the players in teams 0 and 1 for any collisions, and does stuff
//note i think hit players return to spawn point and lost macs are back
    var x = me.x;
    var y = me.y;
    var w = 15;
    var h = 15;

    for(var i = 0; i < enemies.length; i++) {
	mx = teams[0][i].x;
	my = teams[0][i].y;

	if(axis == 1 && dir == 0) {//left
	    if ((x > mx && x < mx + 15 && y > my && y < my + 15)
		|| (x > mx && x < mx + 15 && y + h > my && y + h < my + 15)) {
		    tagged(me);
		    break;
		}
	}
	else if(axis == 0 && dir == 0) {//up
	    if ((x > mx && x < mx + 15 && y > my && y < my + 15)
		|| (x + w > mx && x + w < mx + 15 && y > my && y < my + 15)) {
		    tagged(me);
		    break;
		}
	}
	else if(axis == 1 && dir == 1) {//right
	    if ((x + w > mx && x + w < mx + 15 && y > my && y < my + 15)
		|| (x+w > mx && x+w < mx + 15 && y + h > my && y + h < my + 15)){
		    tagged(me);
		    break;
		}
	}
	else {//down
	    if ((x + w > mx && x + w < mx + 15 && y + h > my && y + h < my + 15)
		|| (x > mx && x < mx + 15 && y + h > my && y + h < my + 15)) {
		    tagged(me);
		    break;
		}
	}
    }
}

function tagged(me) {
//changes your player by decreasing their held items and respawning

    //spawns NUM_DROP around and puts them in the db
    var randomx = 0;
    var randomy = 0;
    var numSpawn = 0;
    var spawnx = 0;
    var spawny = 0;
    var iSpawn;

    if (mapnum == 1) {
	numSpawn = 4;
	iSpawn = getIone();
	spawnx = 655;
	spawny = 470;	
    }
	
    else if (mapnum == 2) {
	numSpawn = 5;
	iSpawn = getItwo();
	spawnx = 670;
	spawny = 245;
    }
    
    else {
	numSpawn = 5;
	iSpawn = getIthree();
	spawnx = 670;
	spawny = 402;
    }

    //decreases your held items
    me.held -= NUM_DROP;
    var extra;
    if(me.held < 0) {
	extra = me.held;
	me.held = 0;
    }
    
    for(i = macguffins.length; i < NUM_DROP+macguffins.length+extra; i++) {
	zone = Math.floor(math.random() * numSpawn);
	randomx = Math.floor((Math.random() * 50) + iSpawn[zone][0][0] - 5);
	randomy = Math.floor((Math.random() * 50) + iSpawn[zone][0][1] - 5);
	$.getJSON('/update',{x:randomx,y:randomy},function() {});
	
	//updates macguffins
	macguffins[i] = [];
	macguffins[i][0] = randomx;
	macguffins[i][1] = randomy;
    }

    //respawns the player
    me.x = spawnx;
    me.y = spawny;

    //updates the db
    $.getJSON('/updatePlayer', {x:me.x,y:me.y,item:me.held}, function(){});
    
    //updates the screen
    renderM();
    renderT();
} 

function checkMCols(me, dir, axis) {
//checks if team 1 is in a position to pick up.
    var x = me.x;
    var y = me.y;
    var h = 15;
    var w = 15;
    var macradius = 10;

    for(i = 0; i < macguffins.length; i++) {
	mx = macguffins[i][0];
	my = macguffins[i][1];

	if(axis == 1 && dir == 0) {//left
	    if ((Math.abs(x - mx) < 10 && Math.abs(y - my) < 10)
		|| (Math.abs(x - mx) < 10 && Math.abs(y + h - my) < 10)) {
		    pickUp(me, i);
		    break;
		}
	}
	else if(axis == 0 && dir == 0) {//up
	    if ((Math.abs(x - mx) < 10 && Math.abs(y - my) < 10)
		|| (Math.abs(x + w - mx) < 10 && Math.abs(y - my) < 10)) {
		    pickUp(me, i);
		    break;
		}
	}
	else if(axis == 1 && dir == 1) {//right
	    if ((Math.abs(x + w - mx) < 10 && Math.abs(y - my) < 10)
		|| (Math.abs(x + w - mx) < 10 && Math.abs(y + h - my) < 10)) {
		    pickUp(me, i);
		    break;
		}
	}
	else {//down
	    if ((Math.abs(x - mx) < 10 && Math.abs(y + h - my) < 10)
		|| (Math.abs(x + w - mx) < 10 && Math.abs(y + h - my) < 10)) {
		    pickUp(me, i);
		    break;
		}
	}
    }
}

function pickUp(me, index) {
//picks up the macguffin at index by updating the db, screen, items held, etc.
    $.getJSON('/removeMacguffin'
	      , {x:macguffins[index][0],y:macguffins[index][1]}, function(){});
    me.held++;
    removeMacguffins();
    macguffins.splice(index, 1);
    makeMacguffins();
}

function loadMap() {
    if(mapnum == 1) {
	mapOne();
	poly = getPolyOne();
    }
    else if(mapnum == 2) {
	mapTwo();
	poly = getPolyTwo();
    }
    else {
	mapThree();
	poly = getPolyThree();
    }
}

var time = TIME_LIMIT * 60;
function timer() {
    dispTimer();
    setInterval(dispTimer, 1000);
}

function updateTimer() {
    setInterval(renderT, 100);
    if(getSelf().picker)
	setInterval(checkPCols, 500);
}

function dispTimer() {
//increments the timer, maybe updates database
    function timeString(num) {
	return ( num < 10 ? "0" : "" ) + num;
    }
    var curr = timeString(Math.floor(time/60)) + ":" 
	+ timeString(Math.floor(time%60));
    
    $("#timer").text("" + curr);
    time--;
    endGame();
}

function getMacSum() {
//adds the macguffins of each member of team 1
    var i = 0;
    for(var guy in teams[1])
	i += guy.held;
    return i;
}

function endGame() {
//checks for end conditions, either team 1 gets enough macguffins or timer = 0
    if(!time)
	;//end with victory team 0
    else if(getMacSum() == NUM_REQ)
	;//end with victory for team 1
}

$(document).ready(function() {
    $("svg").keypress(function(e) {
	if(e.which >= 37 || e.which <= 40)
	    getMove(e.which);
    });
    loadMap();//make map
    makePlayers();//make players
    makeMacs();//make items
    timer();
    updateTimer();
});