package dk.sdu.mmmi.cbse.gamestates;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import dk.sdu.mmmi.cbse.entities.Bullet;
import dk.sdu.mmmi.cbse.entities.Enemy;
import dk.sdu.mmmi.cbse.entities.Player;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;

public class PlayState extends GameState {
	
	private ShapeRenderer sr;
	
	private Player player;

	private Enemy enemy;

	private Set<Bullet> bullets;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		sr = new ShapeRenderer();
		
		player = new Player();

		enemy = new Enemy(this);

		bullets = new HashSet<Bullet>();
		
	}
	
	public void update(float dt) {
		
		handleInput();
		
		player.update(dt);
		enemy.update(dt);

		Iterator<Bullet> iterator = bullets.iterator();
		while (iterator.hasNext()) {
			Bullet bullet = iterator.next();

			if (bullet.isOutsideScreen()) {
				iterator.remove();
			} else {
				bullet.update(dt);
			}
		}
	}

	public void addBullet(Bullet bullet) {
		this.bullets.add(bullet);
	}
	
	public void draw() {
		player.draw(sr);
		enemy.draw(sr);

		for (Bullet bullet: bullets){
			bullet.draw(sr);
	 	}
	}
	
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
	}
	
	public void dispose() {}
	
}









