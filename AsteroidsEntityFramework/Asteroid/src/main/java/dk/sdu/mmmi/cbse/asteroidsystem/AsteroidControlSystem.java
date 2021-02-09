package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            asteroid.getPart(PositionPart.class).process(gameData, asteroid);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            
            movingPart.setUp(true);
            movingPart.setLeft(false);
            movingPart.setRight(false);
            
            movingPart.process(gameData, asteroid);

            updateShape(asteroid);
        }
    }
    
    private void updateShape(Entity entity){
        int points = 6;
        float[] shapex = new float[points];
        float[] shapey = new float[points];

        PositionPart positionPart = entity.getPart(PositionPart.class);

        float x = positionPart.getX();
        float y = positionPart.getY();

        float radians = positionPart.getRadians();
        
        for (int i = 0; i < points; i++) {
            float angle = i * (2 * 3.1415f / points);
            shapex[i] = x + (float) Math.cos(angle + radians)*10 ;
            shapey[i] = y + (float) Math.sin(angle + radians)*10 ;
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
