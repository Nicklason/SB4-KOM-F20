package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

public class Bullet extends SpaceObject {
  private float maxSpeed;

  public Bullet(float x, float y, float radians) {
    this.x = x;
    this.y = y;
    this.radians = radians;

    shapex = new float[4];
		shapey = new float[4];

    this.maxSpeed = 300;
    this.dx = MathUtils.cos(radians) * maxSpeed;
    this.dy = MathUtils.sin(radians) * maxSpeed;
  }

  private void setShape() {
    shapex[0] = x + MathUtils.cos(radians) * 8;
    shapey[0] = y + MathUtils.sin(radians) * 8;
    
    shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
    shapey[1] = y + MathUtils.sin(radians - 4 * 3.1145f / 5) * 8;
    
    shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
    shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 5;
    
    shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
    shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;
  }
  
  public void update(float dt) {		
    // set position
		x += dx * dt;
		y += dy * dt;
		
		// set shape
    setShape();
  }
  
  public void draw(ShapeRenderer sr) {
    
    sr.setColor(1, 0, 0, 1);
    
    sr.begin(ShapeType.Line);
    
    for(int i = 0, j = shapex.length - 1;
      i < shapex.length;
      j = i++) {
      
      sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
      
    }
    
    sr.end();
    
  }

  public boolean isOutsideScreen() {
		return x < 0 || x > Game.WIDTH || y < 0 || y > Game.HEIGHT;
	}
}
