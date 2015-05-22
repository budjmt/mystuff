class Star extends GameObject {
  boolean active = true;  
  
  public Star(float x, float y) {
    super(x, y);
    dims.x = 40;
    dims.y = 40;
    maxForce = 0.25;
    maxSpeed = 10;

    shape = createShape();
    shape.beginShape();
    shape.vertex(0, dims.y);
    shape.vertex(0.25 * dims.x, 0.25 * dims.y);
    shape.vertex(dims.x, 0.25 * dims.y);
    shape.vertex(0.5 * dims.x, -0.25 * dims.y);
    shape.vertex(0.75 * dims.x, -dims.y);
    shape.vertex(0, -0.25 * dims.y);
    shape.vertex(-0.75 * dims.x, -dims.y);
    shape.vertex(-0.5 * dims.x, -0.25 * dims.y);
    shape.vertex(-dims.x, 0.25 * dims.y);
    shape.vertex(-0.25 * dims.x, 0.25 * dims.y);
    shape.endShape(CLOSE);
    shape.disableStyle();

    colliderType = ColliderTypes.RECT;
  }
  
  public PVector incRotate() {
    PVector rotForce = new PVector(-forward.y, -forward.x);
    rotForce.rotate(TWO_PI / 16);
    rotForce.mult(mass);
    return rotForce;
  }
  
  public void draw() {
   if(!active)
    return;
   super.draw(); 
  }
  
  public void update() {
    PVector force = new PVector(0, 0);
    //force.add(incRotate());

    force.limit(maxForce);
    applyForce(force);
    super.update();
  }

  void display() {
    fill(255, 255, 0);
    pushMatrix();
    translate(position.x, position.y);
    rotate(forward.heading());
    shape(shape, -dims.x/2, -dims.y/2);
    popMatrix();
  }
}

