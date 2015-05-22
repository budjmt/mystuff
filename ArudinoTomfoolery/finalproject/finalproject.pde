import processing.serial.*;

//data for/from the arduino
String portname = "COM3";
Serial port;
boolean contacted = false;

float[] knobs = {0,0,0};
boolean win;

//data for the game
Paddle[] paddles;
Ball ball;

void setup() {
  size(800, 800, P2D);
  background(0);
  frameRate(60);
  gameSetup();
  arduinoSetup();
}

void gameSetup() {
  ellipseMode(CENTER);
  paddles = new Paddle[3];
  paddles[0] = new Paddle(width/4, height/4);
  paddles[1] = new Paddle(3 * width/4, height/2);
  paddles[2] = new Paddle(width/3, 3*height/4);
  ball = new Ball(width/2, 0, paddles);
}

void arduinoSetup() {
  if(Serial.list().length == 0)
    return;
  portname = Serial.list()[0];
  port = new Serial(this, portname, 57600);
  port.bufferUntil('\n');
}

void serialEvent(Serial myPort) {
  String ardString = myPort.readStringUntil('\n');
  ardString = trim(ardString);

  if(!contacted) {
    port.clear();
    port.write('A');
    contacted = true;
  }
  else
    knobs = float(split(ardString, ','));
}

void sendResult() {
 port.write(win + ""); 
}

void draw() {
  background(32, 64, 128);
  for (int i = 0; i < paddles.length; i++) {
    paddles[i].updateAngle(knobs[i]);
    paddles[i].draw();
  }
  ball.draw();
}

