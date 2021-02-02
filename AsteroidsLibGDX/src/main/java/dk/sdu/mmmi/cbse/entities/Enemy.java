package dk.sdu.mmmi.cbse.entities;

import java.util.Random;

import dk.sdu.mmmi.cbse.gamestates.PlayState;

public class Enemy extends Player {  
  private Random random;
  private PlayState playState;

  public Enemy(PlayState playState) {
    random = new Random();
    this.playState = playState;
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

    if (randomFloat >= 0.99) {
      this.playState.addBullet(new Bullet(x, y, radians));
    }
    
    super.update(dt);
  }
}
