class Ball extends GameObject {
  Paddle[] paddles;
  Star[] stars;
  
  int numGot = 0;

  public Ball(float x, float y, Paddle[] p,Star[] s) {
    super(x, y);
    dims.x = 40;
    dims.y = 40;
    maxForce = 0.25;
    maxSpeed = 10; 
    shape = createShape(ELLIPSE, 0, 0, dims.x, dims.y);
    shape.disableStyle();

    colliderType = ColliderTypes.RECT;
    paddles = p;
    stars = s;
  }

  public void update() {
    PVector force = new PVector(0, 0);
    force.add(PVector.mult(getGravity(),0.5));
    
    for (int i = 0; i < paddles.length; i++) {
      if (collide.collidesWith(paddles[i])) {
        collide.handleCollision(paddles[i]);
      }
    }
    for (int i = 0; i < stars.length; i++) {
      if (stars[i].active && collide.collidesWith(stars[i])) {
        stars[i].active = false;
        numGot++;
      }
    }
    if (mousePressed) {
      position.x = mouseX;
      position.y = mouseY;
      velocity.x = 0;
      velocity.y = 0;
    }

    force.limit(maxForce);
    applyForce(force);
    super.update();
  }

  public void display() {
    fill(255,255,255);
    pushMatrix();
    translate(position.x, position.y);
    rotate(forward.heading());
    shape(shape, -dims.x/2, -dims.y/2);
    popMatrix();
  }
}

