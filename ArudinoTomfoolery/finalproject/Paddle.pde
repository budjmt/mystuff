class Paddle extends GameObject {
  float prevAngle = 0, angle = 0;
  float tilt = 64 * PI / 180;

  public Paddle(float x, float y) {
    super(x, y);
    dims.x = 150;
    dims.y = 20;
    mass = 100000;
    maxForce = 0.05;
    maxSpeed = 4; 
    shape = createShape(RECT, 0, 0, dims.x, dims.y);
    shape.disableStyle();

    colliderType = ColliderTypes.RECT;
  }

  public PVector incRotate() {
    PVector rotForce = new PVector(-forward.y, -forward.x);
    rotForce.rotate(angle - prevAngle);
    rotForce.mult(mass);
    return rotForce;
  }

  public void updateAngle(float a) {
    prevAngle = angle;
    angle = map(a,0,1024,tilt,-tilt);
  }

  void update() {
    PVector force = new PVector(0, 0);
    //force.add(getGravity());
    if(abs(angle - prevAngle) > 0.01) {
      forward.x = -1;
      forward.y = 0;
      forward.rotate(angle);
      //force.add(incRotate());
    }

    force.limit(maxForce);
    applyForce(force);
    super.update();
  }

  public void display() {
    fill(200, 200, 220);
    pushMatrix();
    translate(position.x, position.y);
    rotate(forward.heading());
    shape(shape, -dims.x/2, -dims.y/2);
    popMatrix();
  }
}

