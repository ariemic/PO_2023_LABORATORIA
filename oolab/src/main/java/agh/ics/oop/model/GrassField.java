package agh.ics.oop.model;
import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.interfaces.WorldElement;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap{

    private final Map<Vector2d, Grass> grassFields = new HashMap<>();
    private final Vector2d grassTopRightCorner;
    private final Vector2d grassDownLeftCorner;

    public GrassField(int grassNumber){
        this(grassNumber,new Random());
    }

    public GrassField(int grassNumber,Random seed){
        worldTopRightCorner = new Vector2d(0, 0);
        worldDownLeftCorner = new Vector2d((int)(sqrt(grassNumber * 10)),(int) (sqrt(grassNumber * 10)));

        PositionsGenerator positions = new PositionsGenerator(worldDownLeftCorner.getX(), worldDownLeftCorner.getY(),grassNumber,seed);
        for(Vector2d grassPosition : positions){
            grassFields.put(grassPosition, new Grass(grassPosition));
            worldDownLeftCorner = worldDownLeftCorner.lowerLeft(grassPosition);
            worldTopRightCorner = worldTopRightCorner.upperRight(grassPosition);
        }
        grassTopRightCorner = worldTopRightCorner;
        grassDownLeftCorner = worldDownLeftCorner;
    }

    @Override

    public boolean place(Animal animal){
        if(canMoveTo(animal.getPosition())) {
            worldTopRightCorner = worldTopRightCorner.upperRight(animal.getPosition());
            worldDownLeftCorner = worldDownLeftCorner.lowerLeft(animal.getPosition());
        }
        return super.place(animal);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        super.move(animal,direction);
        updateCorners();
    }

    public void updateCorners(){
        worldDownLeftCorner = grassDownLeftCorner;
        worldTopRightCorner = grassTopRightCorner;
        animals.forEach((key,value) -> {
            worldDownLeftCorner = worldDownLeftCorner.lowerLeft(key);
            worldTopRightCorner = worldTopRightCorner.upperRight(key);
        });
    }
    @Override
    public WorldElement objectAt(Vector2d position) {
        if (super.objectAt(position) == null){
            return grassFields.get(position);
        }
        return super.objectAt(position);
    }

    @Override
    public ArrayList<WorldElement> getElements() {
        ArrayList<WorldElement> values = new ArrayList<>(super.getElements());
        values.addAll(grassFields.values());
        return values;
    }
}
