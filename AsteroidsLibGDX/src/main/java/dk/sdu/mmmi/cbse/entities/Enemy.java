package dk.sdu.mmmi.cbse.entities;

import java.util.Random;

public class Enemy extends Player {  
  private Random random;
	
	public Enemy() {
    random = new Random();
	}
	
	public void update(float dt) {
    float randomFloat = random.nextFloat();

    if (randomFloat >= 0.9) {
      this.setUp(true);
      this.setRight(false);
      this.setLeft(false);
    } else if (randomFloat >= 0.45) {
      this.setUp(false);
      this.setRight(true);
      this.setLeft(false);
    } else {
      this.setUp(false);
      this.setRight(false);
      this.setLeft(true);
    }
		
		super.update(dt);
	}
}
