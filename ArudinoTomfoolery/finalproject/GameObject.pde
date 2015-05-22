class GameObject {
  PVector position, velocity, acceleration;
  PVector forward, right, dims;
  float maxForce, maxSpeed, mass;
  PShape shape;

  Collide collide;
  ColliderTypes colliderType;

  public GameObject(float x, float y) {
    position = new PVector(x, y);
    velocity = new PVector(0, 0);
    acceleration = new PVector(0, 0);
    forward = new PVector(1, 0);
    right = new PVector(forward.y, -forward.x);
    dims = new PVector(0, 0);
    collide = new Collide(this);
    mass = 1;
  }

  void update() {
    velocity.add(acceleration);
    velocity.limit(maxSpeed);
    position.add(velocity);
    acceleration.mult(0);
    if (velocity.mag() > 0) {
      forward = velocity.get();
      forward.normalize();
      right = new PVector(forward.y, -forward.x);
    }
    collide.updateCorners();
    collide.updateNormals();
  }

  void applyForce(PVector force) {
    acceleration.add(PVector.div(force, mass));
  }

  PVector getGravity() {
    return new PVector(0, 9.8 * mass, 0);
  }

  public void display() {
  }

  void draw() {
    update();
    display();
    /*for (int i = 0; i < collide.corners.length; i++) {
     stroke(0, i * 50, 0, 128);
     line(0, 0, collide.corners[i].x, collide.corners[i].y);
     }
     stroke(255,128,0,128);
     line(position.x,position.y,position.x + forward.x * 50,position.y + forward.y * 50);
     stroke(0,0,0);*/
  }
}

