public class Collide {
  GameObject gam;
  PVector[] corners;
  ArrayList<PVector> normals;

  public Collide(GameObject g) {
    this.gam = g;
    corners = new PVector[4];
  }

  public void updateCorners() {
    PVector toCorner = PVector.mult(gam.dims, 0.5);
    PVector toOppositeCorner = new PVector(toCorner.x, -toCorner.y);
    float heading = gam.forward.heading();
    toCorner.rotate(heading);
    toOppositeCorner.rotate(heading);
    //top left
    corners[0] = PVector.sub(gam.position, toCorner);
    //top right
    corners[1] = PVector.add(gam.position, toOppositeCorner);
    //bottom right
    corners[2] = PVector.add(gam.position, toCorner);
    //bottom left
    corners[3] = PVector.sub(gam.position, toOppositeCorner);
  }

  public void updateNormals() {
    normals = new ArrayList<PVector>();
    switch(gam.colliderType) {
    case RECT:
      for (int i = 0; i < corners.length; i++) {
        PVector norm = PVector.sub(corners[i]
          , corners[(i + 1) % corners.length]);
        norm.rotate(HALF_PI);
        norm.normalize();
        normals.add(norm);
      }
      break;
    }
  }

  public float[] getMaxMin(GameObject g, PVector axis) {
    float[] maxmin = { 
      PVector.dot(g.collide.corners[0], axis), 1
    };
    for (int i = 1; i < g.collide.corners.length; i++) {
      float proj = PVector.dot(g.collide.corners[i], axis);
      if (maxmin[1] > proj)
        maxmin[1] = proj;
      if (proj > maxmin[0])
        maxmin[0] = proj;
    }
    return maxmin;
  }

  public ArrayList<PVector> getNormals(GameObject g, GameObject other) {
    ArrayList<PVector> combNormals = g.collide.normals;
    for (int i = 0; i < other.collide.normals.size (); i++)
      combNormals.add(other.collide.normals.get(i));
    return combNormals;
  }

  public boolean collidesWith(GameObject other) {
    //first check if the circular bounding boxes are colliding
    if (PVector.sub(gam.position, other.position).mag()
      > max(gam.dims.x, gam.dims.y) + max(other.dims.x, other.dims.y))
      return false;
    //separating axis theorem
    ArrayList<PVector> axes = getNormals(gam, other);
    for (int i = 0; i < axes.size (); i++) {
      float[] gamProjs = getMaxMin(gam, axes.get(i)); 
      float[] otherProjs = getMaxMin(other, axes.get(i));
      if (gamProjs[0] < otherProjs[1] || otherProjs[0] < gamProjs[1])
        return false;
    }
    return true;
  }

  public PVector adjustPosition(GameObject other) {
    PVector origPos = gam.position;
    do {
      float xDist = gam.position.x - other.position.x;
      float yDist = gam.position.y - other.position.y;
      float xInc = xDist / gam.dims.x;
      float yInc = yDist / gam.dims.y;
      if(xInc < -1 && xInc > 1)
        xInc = 1 / xInc;
      if(yInc < -1 && yInc > 1)
        yInc = 1 / yInc;
      
      gam.position.x += xInc;
      gam.position.y += yInc;
      for(int i = 0;i < corners.length;i++) {
         corners[i].x += xInc;
         corners[i].y += yInc; 
      }
    }
    while (collidesWith (other));
    return origPos;
  }

  public void handleCollision(GameObject other) {
    PVector origPos = adjustPosition(other);

    float xDist = origPos.x - other.position.x;
    float yDist = origPos.y - other.position.y;
    float collisionAngle = atan(yDist / xDist);

    float gamMag = gam.velocity.mag();
    float otherMag = other.velocity.mag();

    float gamAngle = gam.forward.heading();
    float otherAngle = other.forward.heading();

    float gamXSpeed = gamMag 
      * cos(gamAngle - collisionAngle);
    float gamYSpeed = gamMag 
      * sin(gamAngle - collisionAngle);
    float otherXSpeed = otherMag 
      * cos(otherAngle - collisionAngle);

    float finalgamXSpeed = ((gam.mass - other.mass)
      * gamXSpeed + (2 * other.mass)
      * otherXSpeed);
    finalgamXSpeed /= (gam.mass + other.mass);

    gam.velocity.x = cos(collisionAngle)
      * finalgamXSpeed + 
        cos(collisionAngle + HALF_PI) 
        * gamYSpeed;
    gam.velocity.y = sin(collisionAngle) 
      * finalgamXSpeed + 
        sin(collisionAngle + HALF_PI) 
        * gamYSpeed;
  }
}

