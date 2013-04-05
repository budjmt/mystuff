/*
All this code does is load stuff for maps
map#() loads the map with #
getPoly#() loads the boundaries for #


 */


var svgNS = "http://www.w3.org/2000/svg";
var svg = $("svg");

var pspawnone = [[[0,0],[75,50]],[[625,450],[700,500]]];
var ifirst = [[[0,450],[50,500]],[[650,0],[700,50]],[[250,150],[300,200]],[[400,300],[450,350]]];

var pspawntwo = [[[0,200],[50,300]],[[650,200],[700,300]]];
var isecond = [[[325,225],[375,275]],[[190,115],[240,165]],[[460,115],[510,165]],[[480,335],[530,385]],[[170,335],[220,386]]];

var pspawnthree = [[[55,0],[130,50]],[[650,370],[700,445]]];
var ithird = [[[320,65],[380,115]],[[320,445],[380,495]],[[0,220],[50,280]],[[177,308],[277,358]],[[473,308],[523,358]]];

function getPone(){
    return pspawnone;
}
function getIone(){
    return ifirst;
}
function getPtwo(){
    return pspawntwo;
}
function getItwo(){
    return isecond;
}
function getPthree(){
    return pspawnthree;
}
function getIthree(){
    return ithird;
}



function mapOne(){
	    spawnone = document.createElementNS(svgNS, "path");
	    var d = "M " + 75 + " " + 0 + " L " + 75 + " " + 50
		+ " L " + 0 + " " + 50 + " L " + 0 + " " + 0 + "Z"
	    spawnone.setAttribute("d", d);
	    spawnone.setAttribute("stroke", "black");
	    spawnone.setAttribute("fill", "blue");
	    spawnone.setAttribute("stroke-width", "1");
	    spawnone.setAttribute("class", "spawnone");

	    spawntwo = document.createElementNS(svgNS, "path");
	    d = "M " + 700 + " " + 500 + " L " + 625 + " " + 500
		+ " L " + 625 + " " + 450 + " L " + 700 + " " + 450 + "Z"
	    spawntwo.setAttribute("d", d);
	    spawntwo.setAttribute("stroke", "black");
	    spawntwo.setAttribute("fill", "blue");
	    spawntwo.setAttribute("stroke-width", "1");
	    spawntwo.setAttribute("class", "spawntwo");

	    tone = document.createElementNS(svgNS, "path");
	    d = "M " + 50 + " " + 75 + " L " + 50 + " " + 450
		+ " L " + 425 + " " + 450 + " L " + 50 + " " + 75 + "Z"
	    tone.setAttribute("d", d);
	    tone.setAttribute("stroke", "black");
	    tone.setAttribute("fill", "black");
	    tone.setAttribute("stroke-width", "1");
	    tone.setAttribute("class", "wall");

	    ttwo = document.createElementNS(svgNS, "path");
	    d = "M " + 650 + " " + 425 + " L " + 650 + " " + 50
		+ " L " + 275 + " " + 50 + " L " + 650 + " " + 425 + "Z"
	    ttwo.setAttribute("d", d);
	    ttwo.setAttribute("stroke", "black");
	    ttwo.setAttribute("fill", "black");
	    ttwo.setAttribute("stroke-width", "1");
	    ttwo.setAttribute("class", "wall");

	    rone = document.createElementNS(svgNS, "path");
	    d = "M " + 175 + " " + 75 + " L " + 225 + " " + 75
		+ " L " + 225 + " " + 125 + " L " + 175 + " " + 125 + "Z"
	    rone.setAttribute("d", d);
	    rone.setAttribute("stroke", "black");
	    rone.setAttribute("fill", "black");
	    rone.setAttribute("stroke-width", "1");
	    rone.setAttribute("class", "wall");

	    rtwo = document.createElementNS(svgNS, "path");
	    d = "M " + 525 + " " + 425 + " L " + 475 + " " + 425
		+ " L " + 475 + " " + 375 + " L " + 525 + " " + 375 + "Z"
	    rtwo.setAttribute("d", d);
	    rtwo.setAttribute("stroke", "black");
	    rtwo.setAttribute("fill", "black");
	    rtwo.setAttribute("stroke-width", "1");
	    rtwo.setAttribute("class", "wall");

	    rthree = document.createElementNS(svgNS, "path");
	    d = "M " + 325 + " " + 225 + " L " + 375 + " " + 225
		+ " L " + 375 + " " + 275 + " L " + 325 + " " + 275 + "Z"
	    rthree.setAttribute("d", d);
	    rthree.setAttribute("stroke", "black");
	    rthree.setAttribute("fill", "black");
	    rthree.setAttribute("stroke-width", "1");
	    rthree.setAttribute("class", "wall");

	    ispawnone = document.createElementNS(svgNS, "path");
	    d = "M " + 0 + " " + 450 + " L " + 0 + " " + 500
		+ " L " + 50 + " " + 500 + " L " + 50 + " " + 450 + "Z"
	    ispawnone.setAttribute("d", d);
	    ispawnone.setAttribute("stroke", "black");
	    ispawnone.setAttribute("fill", "red");
	    ispawnone.setAttribute("stroke-width", "1");
	    ispawnone.setAttribute("class", "ispawnone");

	    ispawntwo = document.createElementNS(svgNS, "path");
	    d = "M " + 650 + " " + 0 + " L " + 700 + " " + 0
		+ " L " + 700 + " " + 50 + " L " + 650 + " " + 50 + "Z"
	    ispawntwo.setAttribute("d", d);
	    ispawntwo.setAttribute("stroke", "black");
	    ispawntwo.setAttribute("fill", "red");
	    ispawntwo.setAttribute("stroke-width", "1");
	    ispawntwo.setAttribute("class", "ispawntwo");

	    ispawnthree = document.createElementNS(svgNS, "path");
	    d = "M " + 250 + " " + 150 + " L " + 250 + " " + 200
		+ " L " + 300 + " " + 200 + " L " + 300 + " " + 150 + "Z"
	    ispawnthree.setAttribute("d", d);
	    ispawnthree.setAttribute("stroke", "black");
	    ispawnthree.setAttribute("fill", "red");
	    ispawnthree.setAttribute("stroke-width", "1");
	    ispawnthree.setAttribute("class", "ispawnthree");

	    ispawnfour = document.createElementNS(svgNS, "path");
	    d = "M " + 400 + " " + 350 + " L " + 400 + " " + 300
		+ " L " + 450 + " " + 300 + " L " + 450 + " " + 350 + "Z"
	    ispawnfour.setAttribute("d", d);
	    ispawnfour.setAttribute("stroke", "black");
	    ispawnfour.setAttribute("fill", "red");
	    ispawnfour.setAttribute("stroke-width", "1");
	    ispawnfour.setAttribute("class", "ispawnfour");

	    //svg.append(spawnone);
	    //svg.append(spawntwo);
	    svg.append(tone);
	    svg.append(ttwo);
	    svg.append(rone);
	    svg.append(rtwo);
	    svg.append(rthree);
	    //svg.append(ispawnone);
	    //svg.append(ispawntwo);
	    //svg.append(ispawnthree);
	    //svg.append(ispawnfour);
	}

function getPolyOne(){
	var poly = [];
	poly[0] = [];
	poly[0][0] = [];
	poly[0][0][0] = 50;
	poly[0][0][1] = 75;
	poly[0][1] = [];
	poly[0][1][0] = 50;
	poly[0][1][1] = 450;
	poly[0][2] = [];
	poly[0][2][0] = 425;
	poly[0][2][1] = 450;

	poly[1] = [];
	poly[1][0] = [];
	poly[1][0][0] = 650;
	poly[1][0][1] = 425;
	poly[1][1] = [];
	poly[1][1][0] = 650;
	poly[1][1][1] = 50;
	poly[1][2] = [];
	poly[1][2][0] = 275;
	poly[1][2][1] = 50;

	poly[2] = [];
	poly[2][0] = [];
	poly[2][0][0] = 175;
	poly[2][0][1] = 75;
	poly[2][1] = [];
	poly[2][1][0] = 225;
	poly[2][1][1] = 75;
	poly[2][2] = [];
	poly[2][2][0] = 225;
	poly[2][2][1] = 125;
	poly[2][3] = [];
	poly[2][3][0] = 175;
	poly[2][3][1] = 125;

	poly[3] = [];
	poly[3][0] = [];
	poly[3][0][0] = 525;
	poly[3][0][1] = 425;
	poly[3][1] = [];
	poly[3][1][0] = 475;
	poly[3][1][1] = 425;
	poly[3][2] = [];
	poly[3][2][0] = 475;
	poly[3][2][1] = 375;
	poly[3][3] = [];
	poly[3][3][0] = 525;
	poly[3][3][1] = 375;

	poly[4] = [];
	poly[4][0] = [];
	poly[4][0][0] = 325;
	poly[4][0][1] = 225;
	poly[4][1] = [];
	poly[4][1][0] = 375;
	poly[4][1][1] = 225;
	poly[4][2] = [];
	poly[4][2][0] = 375;
	poly[4][2][1] = 275;
	poly[4][3] = [];
	poly[4][3][0] = 325;
	poly[4][3][1] = 275;

	return poly;
}


function mapTwo(){
    spawnone = document.createElementNS(svgNS, "path");
    var d = "M " + 650 + " " + 200 + " L " + 650 + " " + 300
	+ " L " +  700 + " " + 300 + " L " + 700 + " " + 200 + "Z"
    spawnone.setAttribute("d", d);
    spawnone.setAttribute("stroke", "black");
    spawnone.setAttribute("fill", "blue");
    spawnone.setAttribute("stroke-width", "1");
    spawnone.setAttribute("class", "spawnone");
    
    spawntwo = document.createElementNS(svgNS, "path");
    d = "M " + 0 + " " + 200 + " L " + 0 + " " + 300
	+ " L " +  50 + " " + 300 + " L " + 50 + " " + 200 + "Z"
    spawntwo.setAttribute("d", d);
    spawntwo.setAttribute("stroke", "black");
    spawntwo.setAttribute("fill", "blue");
    spawntwo.setAttribute("stroke-width", "1");
    spawntwo.setAttribute("class", "spawntwo");


    rone = document.createElementNS(svgNS, "path");
    d = "M " + 625 + " " + 50 + " L " + 525 + " " + 50
	+ " L " +  525 + " " + 100 + " L " + 625 + " " + 100 + "Z"
    rone.setAttribute("d", d);
    rone.setAttribute("stroke", "black");
    rone.setAttribute("fill", "black");
    rone.setAttribute("stroke-width", "1");
    rone.setAttribute("class", "wall");

    rtwo = document.createElementNS(svgNS, "path");
    d = "M " + 650 + " " + 450 + " L " + 550 + " " + 450
	+ " L " +  550 + " " + 400 + " L " + 650 + " " + 400 + "Z"
    rtwo.setAttribute("d", d);
    rtwo.setAttribute("stroke", "black");
    rtwo.setAttribute("fill", "black");
    rtwo.setAttribute("stroke-width", "1");
    rtwo.setAttribute("class", "wall");

    rthree = document.createElementNS(svgNS, "path");
    d = "M " + 75 + " " + 50 + " L " + 75 + " " + 100
	+ " L " +  175 + " " + 100 + " L " + 175 + " " + 50 + "Z"
    rthree.setAttribute("d", d);
    rthree.setAttribute("stroke", "black");
    rthree.setAttribute("fill", "black");
    rthree.setAttribute("stroke-width", "1");
    rthree.setAttribute("class", "wall");

    rfour = document.createElementNS(svgNS, "path");
    d = "M " + 50 + " " + 450 + " L " + 50 + " " + 400
	+ " L " +  150 + " " + 400 + " L " + 150 + " " + 450 + "Z"
    rfour.setAttribute("d", d);
    rfour.setAttribute("stroke", "black");
    rfour.setAttribute("fill", "black");
    rfour.setAttribute("stroke-width", "1");
    rfour.setAttribute("class", "wall");

    sone = document.createElementNS(svgNS, "path");
    d = "M " + 290 + " " + 185 + " L " + 410 + " " + 185
	+ " L " +  460 + " " + 35 + " L " + 240 + " " + 35 + "Z"
    sone.setAttribute("d", d);
    sone.setAttribute("stroke", "black");
    sone.setAttribute("fill", "black");
    sone.setAttribute("stroke-width", "1");
    sone.setAttribute("class", "wall");

    stwo = document.createElementNS(svgNS, "path");
    d = "M " + 80 + " " + 150 + " L " + 80 + " " + 350
	+ " L " +  230 + " " + 300 + " L " + 230 + " " + 200 + "Z"
    stwo.setAttribute("d", d);
    stwo.setAttribute("stroke", "black");
    stwo.setAttribute("fill", "black");
    stwo.setAttribute("stroke-width", "1");
    stwo.setAttribute("class", "wall");

    sthree = document.createElementNS(svgNS, "path");
    d = "M " + 620 + " " + 150 + " L " + 620 + " " + 350
	+ " L " +  470 + " " + 300 + " L " + 470 + " " + 200 + "Z"
    sthree.setAttribute("d", d);
    sthree.setAttribute("stroke", "black");
    sthree.setAttribute("fill", "black");
    sthree.setAttribute("stroke-width", "1");
    sthree.setAttribute("class", "wall");

    sfour = document.createElementNS(svgNS, "path");
    d = "M " + 315 + " " + 450 + " L " + 325 + " " + 320
	+ " L " +  280 + " " + 295 + " L " + 215 + " " + 425 + "Z"
    sfour.setAttribute("d", d);
    sfour.setAttribute("stroke", "black");
    sfour.setAttribute("fill", "black");
    sfour.setAttribute("stroke-width", "1");
    sfour.setAttribute("class", "wall");

    sfive = document.createElementNS(svgNS, "path");
    d = "M " + 385 + " " + 450 + " L " + 375 + " " + 320
	+ " L " +  420 + " " + 295 + " L " + 485 + " " + 425 + "Z"
    console.log(d);
    sfive.setAttribute("d", d);
    sfive.setAttribute("stroke", "black");
    sfive.setAttribute("fill", "black");
    sfive.setAttribute("stroke-width", "1");
    sfive.setAttribute("class", "wall");

    ispawnone = document.createElementNS(svgNS, "path");
    d = "M " + 325 + " " + 225 + " L " + 375 + " " + 225
	+ " L " +  375 + " " + 275 + " L " + 325 + " " + 275 + "Z"
    ispawnone.setAttribute("d", d);
    ispawnone.setAttribute("stroke", "black");
    ispawnone.setAttribute("fill", "red");
    ispawnone.setAttribute("stroke-width", "1");
    ispawnone.setAttribute("class", "ispawnone");

    ispawntwo = document.createElementNS(svgNS, "path");
    d = "M " + 190 + " " + 115 + " L " + 190 + " " + 165
	+ " L " +  240 + " " + 165 + " L " + 240 + " " + 115 + "Z"
    ispawntwo.setAttribute("d", d);
    ispawntwo.setAttribute("stroke", "black");
    ispawntwo.setAttribute("fill", "red");
    ispawntwo.setAttribute("stroke-width", "1");
    ispawntwo.setAttribute("class", "ispawntwo");

    ispawnthree = document.createElementNS(svgNS, "path");
    d = "M " + 510 + " " + 115 + " L " + 510 + " " + 165
	+ " L " +  460 + " " + 165 + " L " + 460 + " " + 115 + "Z"
    ispawnthree.setAttribute("d", d);
    ispawnthree.setAttribute("stroke", "black");
    ispawnthree.setAttribute("fill", "red");
    ispawnthree.setAttribute("stroke-width", "1");
    ispawnthree.setAttribute("class", "ispawnthree");

    ispawnfour = document.createElementNS(svgNS, "path");
    d = "M " + 530 + " " + 385 + " L " + 530 + " " + 335
	+ " L " +  480 + " " + 335 + " L " + 480 + " " + 385 + "Z"
    ispawnfour.setAttribute("d", d);
    ispawnfour.setAttribute("stroke", "black");
    ispawnfour.setAttribute("fill", "red");
    ispawnfour.setAttribute("stroke-width", "1");
    ispawnfour.setAttribute("class", "ispawnthree");

    ispawnfive = document.createElementNS(svgNS, "path");
    d = "M " + 170 + " " + 385 + " L " + 170 + " " + 335
	+ " L " +  220 + " " + 335 + " L " + 220 + " " + 385 + "Z"
    ispawnfive.setAttribute("d", d);
    ispawnfive.setAttribute("stroke", "black");
    ispawnfive.setAttribute("fill", "red");
    ispawnfive.setAttribute("stroke-width", "1");
    ispawnfive.setAttribute("class", "ispawnfive");

    //svg.append(spawnone);
    //svg.append(spawntwo);
    svg.append(rone);
    svg.append(rtwo);
    svg.append(rthree);
    svg.append(rfour);
    svg.append(sone);
    svg.append(stwo);
    svg.append(sthree);
    svg.append(sfour);
    svg.append(sfive);
    //svg.append(ispawnone);
    //svg.append(ispawntwo);
    //svg.append(ispawnthree);
    //svg.append(ispawnfour);
    //svg.append(ispawnfive);
 
}

function getPolyTwo(){
    var poly = [];

	poly[0] = [];
	poly[0][0] = [];
	poly[0][0][0] = 625;
	poly[0][0][1] = 50;
	poly[0][1] = [];
	poly[0][1][0] = 525;
	poly[0][1][1] = 50;
	poly[0][2] = [];
	poly[0][2][0] = 525;
	poly[0][2][1] = 100;
	poly[0][3] = [];
	poly[0][3][0] = 625;
	poly[0][3][1] = 100;

	poly[1] = [];
	poly[1][0] = [];
	poly[1][0][0] = 650;
	poly[1][0][1] = 450;
	poly[1][1] = [];
	poly[1][1][0] = 550;
	poly[1][1][1] = 450;
	poly[1][2] = [];
	poly[1][2][0] = 550;
	poly[1][2][1] = 400;
	poly[1][3] = [];
	poly[1][3][0] = 650;
	poly[1][3][1] = 400;

	poly[2] = [];
	poly[2][0] = [];
	poly[2][0][0] = 75;
	poly[2][0][1] = 50;
	poly[2][1] = [];
	poly[2][1][0] = 75;
	poly[2][1][1] = 100;
	poly[2][2] = [];
	poly[2][2][0] = 175;
	poly[2][2][1] = 100;
	poly[2][3] = [];
	poly[2][3][0] = 175;
	poly[2][3][1] = 50;

	poly[3] = [];
	poly[3][0] = [];
	poly[3][0][0] = 50;
	poly[3][0][1] = 450;
	poly[3][1] = [];
	poly[3][1][0] = 50;
	poly[3][1][1] = 400;
	poly[3][2] = [];
	poly[3][2][0] = 150;
	poly[3][2][1] = 400;
	poly[3][3] = [];
	poly[3][3][0] = 150;
	poly[3][3][1] = 450;

	poly[4] = [];
	poly[4][0] = [];
	poly[4][0][0] = 290;
	poly[4][0][1] = 185;
	poly[4][1] = [];
	poly[4][1][0] = 410;
	poly[4][1][1] = 185;
	poly[4][2] = [];
	poly[4][2][0] = 460;
	poly[4][2][1] = 35;
	poly[4][3] = [];
	poly[4][3][0] = 240;
	poly[4][3][1] = 35;

	poly[5] = [];
	poly[5][0] = [];
	poly[5][0][0] = 80;
	poly[5][0][1] = 150;
	poly[5][1] = [];
	poly[5][1][0] = 80;
	poly[5][1][1] = 350;
	poly[5][2] = [];
	poly[5][2][0] = 230;
	poly[5][2][1] = 300;
	poly[5][3] = [];
	poly[5][3][0] = 230;
	poly[5][3][1] = 200;

	poly[6] = [];
	poly[6][0] = [];
	poly[6][0][0] = 620;
	poly[6][0][1] = 150;
	poly[6][1] = [];
	poly[6][1][0] = 620;
	poly[6][1][1] = 350;
	poly[6][2] = [];
	poly[6][2][0] = 470;
	poly[6][2][1] = 300;
	poly[6][3] = [];
	poly[6][3][0] = 470;
	poly[6][3][1] = 200;

	poly[7] = [];
	poly[7][0] = [];
	poly[7][0][0] = 315;
	poly[7][0][1] = 450;
	poly[7][1] = [];
	poly[7][1][0] = 325;
	poly[7][1][1] = 320;
	poly[7][2] = [];
	poly[7][2][0] = 280;
	poly[7][2][1] = 295;
	poly[7][3] = [];
	poly[7][3][0] = 215;
	poly[7][3][1] = 425;

	poly[8] = [];
	poly[8][0] = [];
	poly[8][0][0] = 385;
	poly[8][0][1] = 450;
	poly[8][1] = [];
	poly[8][1][0] = 375;
	poly[8][1][1] = 320;
	poly[8][2] = [];
	poly[8][2][0] = 420;
	poly[8][2][1] = 295;
	poly[8][3] = [];
	poly[8][3][0] = 485;
	poly[8][3][1] = 425;

    return poly;
}

function mapThree(){

    spawnone = document.createElementNS(svgNS, "path");
    var d = "M " + 55 + " " + 0 + " L " + 55 + " " + 50
	+ " L " + 130 + " " + 50 + " L " + 130 + " " + 0 + "Z"
    spawnone.setAttribute("d", d);
    spawnone.setAttribute("stroke", "black");
    spawnone.setAttribute("fill", "blue");
    spawnone.setAttribute("stroke-width", "1");
    spawnone.setAttribute("class", "spawnone");

    spawntwo = document.createElementNS(svgNS, "path");
    d = "M " + 700 + " " + 445 + " L " + 700 + " " + 370
	+ " L " + 650 + " " + 370 + " L " + 650 + " " + 445 + "Z"
    spawntwo.setAttribute("d", d);
    spawntwo.setAttribute("stroke", "black");
    spawntwo.setAttribute("fill", "blue");
    spawntwo.setAttribute("stroke-width", "1");
    spawntwo.setAttribute("class", "spawntwo");

    tone = document.createElementNS(svgNS, "path");
    d = "M " + 55 + " " + 55 + " L " + 300 + " " + 55
	+ " L " + 55 + " " + 225 + " L " + 55 + " " + 55 + "Z"
    tone.setAttribute("d", d);
    tone.setAttribute("stroke", "black");
    tone.setAttribute("fill", "black");
    tone.setAttribute("stroke-width", "1");
    tone.setAttribute("class", "wall");

    ttwo = document.createElementNS(svgNS, "path");
    d = "M " + 645 + " " + 55 + " L " + 400 + " " + 55
	+ " L " + 645 + " " + 225 + " L " + 645 + " " + 55 + "Z"
    ttwo.setAttribute("d", d);
    ttwo.setAttribute("stroke", "black");
    ttwo.setAttribute("fill", "black");
    ttwo.setAttribute("stroke-width", "1");
    ttwo.setAttribute("class", "wall");

    tthree = document.createElementNS(svgNS, "path");
    d = "M " + 645 + " " + 445 + " L " + 400 + " " + 445
	+ " L " + 645 + " " + 275 + " L " + 645 + " " + 445 + "Z"
    tthree.setAttribute("d", d);
    tthree.setAttribute("stroke", "black");
    tthree.setAttribute("fill", "black");
    tthree.setAttribute("stroke-width", "1");
    tthree.setAttribute("class", "wall");

    tfour = document.createElementNS(svgNS, "path");
    d = "M " + 55 + " " + 445 + " L " + 300 + " " + 445
	+ " L " + 55 + " " + 275 + " L " + 55 + " " + 445 + "Z"
    tfour.setAttribute("d", d);
    tfour.setAttribute("stroke", "black");
    tfour.setAttribute("fill", "black");
    tfour.setAttribute("stroke-width", "1");
    tfour.setAttribute("class", "wall");

    tfive = document.createElementNS(svgNS, "path");
    d = "M " + 325 + " " + 130 + " L " + 325 + " " + 370
	+ " L " + 150 + " " + 250 + " L " + 325 + " " + 130 + "Z"
    tfive.setAttribute("d", d);
    tfive.setAttribute("stroke", "black");
    tfive.setAttribute("fill", "black");
    tfive.setAttribute("stroke-width", "1");
    tfive.setAttribute("class", "wall");

    tsix = document.createElementNS(svgNS, "path");
    d = "M " + 375 + " " + 130 + " L " + 375 + " " + 370
	+ " L " + 550 + " " + 250 + " L " + 375 + " " + 130 + "Z"
    tsix.setAttribute("d", d);
    tsix.setAttribute("stroke", "black");
    tsix.setAttribute("fill", "black");
    tsix.setAttribute("stroke-width", "1");
    tsix.setAttribute("class", "wall");

    ispawnone = document.createElementNS(svgNS, "path");
    d = "M " + 320 + " " + 65 + " L " + 380 + " " + 65
	+ " L " + 380 + " " + 115 + " L " + 320 + " " + 115 + "Z"
    ispawnone.setAttribute("d", d);
    ispawnone.setAttribute("stroke", "black");
    ispawnone.setAttribute("fill", "red");
    ispawnone.setAttribute("stroke-width", "1");
    ispawnone.setAttribute("class", "ispawnone");

    ispawntwo = document.createElementNS(svgNS, "path");
    d = "M " + 320 + " " + 495 + " L " + 380 + " " + 495
	+ " L " + 380 + " " + 445 + " L " + 320 + " " + 445 + "Z"
    ispawntwo.setAttribute("d", d);
    ispawntwo.setAttribute("stroke", "black");
    ispawntwo.setAttribute("fill", "red");
    ispawntwo.setAttribute("stroke-width", "1");
    ispawntwo.setAttribute("class", "ispawntwo");

    ispawnthree = document.createElementNS(svgNS, "path");
    d = "M " + 0 + " " + 220 + " L " + 0 + " " + 280
	+ " L " + 50 + " " + 280 + " L " + 50 + " " + 220 + "Z"
    ispawnthree.setAttribute("d", d);
    ispawnthree.setAttribute("stroke", "black");
    ispawnthree.setAttribute("fill", "red");
    ispawnthree.setAttribute("stroke-width", "1");
    ispawnthree.setAttribute("class", "ispawnthree");

    ispawnfour = document.createElementNS(svgNS, "path");
    d = "M " + 177 + " " + 358 + " L " + 177 + " " + 308
	+ " L " + 227 + " " + 308 + " L " + 227 + " " + 358 + "Z"
    ispawnfour.setAttribute("d", d);
    ispawnfour.setAttribute("stroke", "black");
    ispawnfour.setAttribute("fill", "red");
    ispawnfour.setAttribute("stroke-width", "1");
    ispawnfour.setAttribute("class", "ispawnfour");

    ispawnfive = document.createElementNS(svgNS, "path");
    d = "M " + 523 + " " + 358 + " L " + 523 + " " + 308
	+ " L " + 473 + " " + 308 + " L " + 473 + " " + 358 + "Z"
    ispawnfive.setAttribute("d", d);
    ispawnfive.setAttribute("stroke", "black");
    ispawnfive.setAttribute("fill", "red");
    ispawnfive.setAttribute("stroke-width", "1");
    ispawnfive.setAttribute("class", "ispawnfive");


    //svg.append(spawnone);
    //svg.append(spawntwo);
    svg.append(tone);
    svg.append(ttwo);
    svg.append(tthree);
    svg.append(tfour);
    svg.append(tfive);
    svg.append(tsix);
    //svg.append(ispawnone);
    //svg.append(ispawntwo);
    //svg.append(ispawnthree);
    //svg.append(ispawnfour);
    //svg.append(ispawnfive);

}

function getPolyThree(){
    var poly = [];

	poly[0] = [];
	poly[0][0] = [];
	poly[0][0][0] = 55;
	poly[0][0][1] = 55;
	poly[0][1] = [];
	poly[0][1][0] = 300;
	poly[0][1][1] = 55;
	poly[0][2] = [];
	poly[0][2][0] = 55;
	poly[0][2][1] = 225;

	poly[1] = [];
	poly[1][0] = [];
	poly[1][0][0] = 645;
	poly[1][0][1] = 55;
	poly[1][1] = [];
	poly[1][1][0] = 400;
	poly[1][1][1] = 55;
	poly[1][2] = [];
	poly[1][2][0] = 645;
	poly[1][2][1] = 225;

	poly[2] = [];
	poly[2][0] = [];
	poly[2][0][0] = 645;
	poly[2][0][1] = 445;
	poly[2][1] = [];
	poly[2][1][0] = 400;
	poly[2][1][1] = 445;
	poly[2][2] = [];
	poly[2][2][0] = 645;
	poly[2][2][1] = 275;

	poly[3] = [];
	poly[3][0] = [];
	poly[3][0][0] = 55;
	poly[3][0][1] = 445;
	poly[3][1] = [];
	poly[3][1][0] = 300;
	poly[3][1][1] = 445;
	poly[3][2] = [];
	poly[3][2][0] = 55;
	poly[3][2][1] = 275;

	poly[4] = [];
	poly[4][0] = [];
	poly[4][0][0] = 325;
	poly[4][0][1] = 130;
	poly[4][1] = [];
	poly[4][1][0] = 325;
	poly[4][1][1] = 370;
	poly[4][2] = [];
	poly[4][2][0] = 150;
	poly[4][2][1] = 250;

	poly[5] = [];
	poly[5][0] = [];
	poly[5][0][0] = 375;
	poly[5][0][1] = 130;
	poly[5][1] = [];
	poly[5][1][0] = 375;
	poly[5][1][1] = 370;
	poly[5][2] = [];
	poly[5][2][0] = 550;
	poly[5][2][1] = 250;

    return poly;
}