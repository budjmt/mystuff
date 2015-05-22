import processing.serial.*;

//data for/from the arduino
String portname = "COM3";
Serial port;
boolean contacted = false;

float[] knobs = {
  0, 0, 0
};
boolean win;

//data for the game
Paddle[] paddles;
Star[] stars;
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
  stars = new Star[5];
  for (int i = 0; i < stars.length; i++) {
    stars[i] = new Star(random(width / 3, width / 3 * 2)
      , random(height / 3, height / 3 * 2));
  }
  ball = new Ball(width/2, 0, paddles, stars);
}

void arduinoSetup() {
  if (Serial.list().length == 0)
    return;
  portname = Serial.list()[1];
  port = new Serial(this, portname, 57600);
  port.bufferUntil('\n');
}

void serialEvent(Serial myPort) {
  String ardString = myPort.readStringUntil('\n');
  ardString = trim(ardString);

  if (!contacted) {
    port.clear();
    port.write('A');
    contacted = true;
  } else {
    float[] knobVals = float(split(ardString, ','));
    for (int i = 0; i < knobVals.length; i++)
      knobs[i] = knobVals[i];
  }
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
  for (int i = 0; i < stars.length; i++) {
    stars[i].draw();
  }
  ball.draw();
  if(ball.numGot >= stars.length) {
    win = true;
    sendResult();
  }
}

