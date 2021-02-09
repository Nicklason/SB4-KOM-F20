package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {
    
    private int enemyCount = 5;
    private Entity[] enemies;

    @Override
    public void start(GameData gameData, World world) {
        enemies = new Entity[enemyCount];
        
        for (int i = 0; i < enemies.length; i++) {
            Entity enemy = createShip(gameData);
            enemies[i] = enemy;
            world.addEntity(enemy);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity enemy : enemies) {
            world.removeEntity(enemy);
        }
    }
    
    private Entity createShip(GameData gameData) {
        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 60;
        float rotationSpeed = 2;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        float radians = 3.1415f / 2;
        
        Entity ship = new Enemy();
        ship.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        ship.add(new PositionPart(x, y, radians));
        
        return ship;
    }
}
