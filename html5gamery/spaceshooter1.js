

var imagerepo = new function() {
	//define images
	this.background = new Image();
	
	//set src
	this.background.src = "imgs/bg.png"
}

function Drawable() {
	this.init = function(x,y) {
		this.x = x;
		this.y = y;
	}
	
	this.speed = 0;
	this.w = 0;
	this.h = 0;
	
	this.draw = function () {};
}