$(document).ready(function(){
	var svgNS = "http://www.w3.org/2000/svg";
	var svg = $("svg");
	var you = $(".player");
	var it; 
	var number = 0;
	var h = 0;
	var w = 0;
	var poly;

	//Creates the player
	//Michael: You will have to replace this function with your own for spawning players
	function start(){
	    you.attr('x', 10);

	    //These lines load the map and obstacles

	    //mapOne();
	    //poly = getPolyOne();
	    
	    //mapTwo();
	    //poly = getPolyTwo();

	    mapThree();
	    poly = getPolyThree();


	    you.attr('y', 10);
	    you.attr('width', 15);
	    you.attr('height', 15);
	    w = 15;
	    h = 15;
	    you.attr('fill', "yellow");
	    you.attr('class', "player");
	    you.attr('stroke-width', 2);
	    you.attr("stroke", "black");
	    svg.append(you);
	    console.log(you);
	}

	//This spawns items -- again, replace it with your own
	function spawn(){
	    it = document.createElementNS(svgNS, 'circle');

	    var x = 1 + Math.floor(Math.random() * 700);
	    var y = 1 + Math.floor(Math.random() * 500);
	    it.setAttribute('cx', x);
	    it.setAttribute('cy', y);
	    it.setAttribute('r', 10);
	    it.setAttribute('fill', "green");
	    it.setAttribute("class", "item");
	    svg.append(it);

	}

	//This is what happens when a player picks up an item
	//Once again, replace it with your own method
	function shift(){
	    var randomColor = function() {
		var letters = '0123456789ABCDEF'.split('');
		var color = '#';
		for (var i = 0; i < 6; i++){
		    color = color + letters[Math.round(Math.random() * 15)]
			}
		return color;
	    }
	    var x = 1 + Math.floor(Math.random() * 700);
	    var y = 1 + Math.floor(Math.random() * 500);
	    $(".item").attr("cx",x);
	    $(".item").attr("cy",y);
	    $(".player").attr("fill",$(".item").attr("fill"));
	    $(".item").attr('fill', randomColor);
	    number = number + 1;
	    $(".score").text("Current Score: " + number.toString());
	}

	//Movement
	//copy the code from this
	/* Here is how it works:

	  It takes your position and finds out where you are moving
	  Then, it sees if you will crash by comparing your position with the obstacle positions
	  If you will not crash, you can move
	 */
	$(document).keydown(function(e){
	    var dx=0;
	    var dy=0;
	    var x=parseInt($(".player").attr('x'));
	    var y=parseInt($(".player").attr('y'));

	    var step=1;
	    var a = e.keyCode;

	    crash = false;

	    if (a == 37){
		dx=-1*step;
		for(i = 0; i < poly.length; i++){
		    crash = pointpoly(poly[i], x-3, y-1) || pointpoly(poly[i], x-3, y+h+1);
		    if (crash == true){
			break;
		    }
		}
	    }
	    if (a == 38){
		dy=-1*step;
		for(i = 0; i < poly.length; i++){
		    crash = pointpoly(poly[i], x-1, y-3) || pointpoly(poly[i], x+w+1, y-3);
		    if (crash == true){
			break;
		    }
		}
	    }
	    if (a == 39){
		dx=1*step;
		for(i = 0; i < poly.length; i++){
		    crash = pointpoly(poly[i], x+w+2, y-1) || pointpoly(poly[i], x+w+2, y+h+1);
		    if (crash == true){
			break;
		    }
		}
	    }
	    if (a == 40){
		dy=1*step;
		for(i = 0; i < poly.length; i++){
		    crash = pointpoly(poly[i], x, y+h+2) || pointpoly(poly[i], x+w+1, y+h+2);
		    if (crash == true){
			break;
		    }
		}
	    }

	    if(!crash){
		x = x + dx;
		y = y + dy;

		if ((x < (700-w-1)) && (x > 0) && (y > 0) && (y < 500-h-1)){  

		    $(".player").attr("x",x);
		    $(".player").attr("y",y);
		}
	    }

	    itemx = parseInt($(".item").attr("cx"));
	    itemy = parseInt($(".item").attr("cy"));
	    r = parseInt($(".item").attr("r"));

	    if((itemx + r> x) && (itemx - x - r< w) &&
	       (itemy + r> y) && (itemy - y - r< h)){
		shift();
	    }

    });

	start();
	spawn();



	//obstacle collision method
	//give it the two points on the player that are moving
	// (ex: if moving up, needs top left and top right points)
	//Give it the defined obstacles by the map
	//ex: getPolyOne()
	//returns true (if crashing) or false (if not crashing)

	function pointpoly(polyCords, pointX, pointY){	
	    var i, j, c = false;
	    for (i = 0, j = polyCords.length - 1; i < polyCords.length; j = i++)
		{			
		    if (((polyCords[i][1] > pointY) != (polyCords[j][1] > pointY)) && (pointX < (polyCords[j][0] - polyCords[i][0]) *									       (pointY - polyCords[i][1]) / (polyCords[j][1] - polyCords[i][1]) + polyCords[i][0])){
			c = !c;
		    }	
		}
	    return c;
	}

    });