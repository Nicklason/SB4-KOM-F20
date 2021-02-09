package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {
    private int asteroidCount = 5;
    private Entity[] asteroids;
    
    @Override
    public void start(GameData gameData, World world) {
        asteroids = new Entity[asteroidCount];
        
        for (int i = 0; i < asteroids.length; i++) {
            Entity enemy = createAsteroid(gameData);
            asteroids[i] = enemy;
            world.addEntity(enemy);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity enemy : asteroids) {
            world.removeEntity(enemy);
        }
    }
    
    private Entity createAsteroid(GameData gameData) {
        float deacceleration = 0;
        float acceleration = 300;
        float maxSpeed = 100 + 50 * (float)Math.random();
        float rotationSpeed = 0;
        float x = gameData.getDisplayWidth() * (float)Math.random();
        float y = gameData.getDisplayHeight() * (float)Math.random();
        float radians = 3.1415f * (float)Math.random();
        
        Entity asteroid = new Asteroid();
        asteroid.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroid.add(new PositionPart(x, y, radians));
        
        return asteroid;
    }
}
