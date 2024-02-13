package agh.ics.oop.model;
import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.interfaces.WorldElement;
import agh.ics.oop.model.util.PositionsGenerator;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap{

    Map<Vector2d, Grass> grassFields = new HashMap<>();
    private Boundry worldBounds;
    private final Boundry grassBounds;

    public GrassField(){
        this(0, new Random(), 1);
    }

    public GrassField(int grassNumber, int mapID){
        this(grassNumber,new Random(), mapID);
    }

    public GrassField(int grassNumber,Random seed, int mapID){
        super(mapID);
        Vector2d worldTopRightCorner = new Vector2d(0, 0);
        Vector2d worldDownLeftCorner = new Vector2d((int)(sqrt(grassNumber * 10)),(int) (sqrt(grassNumber * 10)));

        PositionsGenerator positions = new PositionsGenerator(worldDownLeftCorner.getX(), worldDownLeftCorner.getY(),grassNumber,seed);
        for(Vector2d grassPosition : positions){
            grassFields.put(grassPosition, new Grass(grassPosition));
            worldDownLeftCorner = worldDownLeftCorner.lowerLeft(grassPosition);
            worldTopRightCorner = worldTopRightCorner.upperRight(grassPosition);
        }
        this.grassBounds = new Boundry(worldDownLeftCorner, worldTopRightCorner);
        this.worldBounds = new Boundry(worldDownLeftCorner, worldTopRightCorner);
    }

    @Override
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        super.place(animal);

    }

    @Override
    public void move(Animal animal, MoveDirection direction) throws PositionAlreadyOccupiedException{
        super.move(animal, direction);

    }

    public void updateCorners(){
        Vector2d worldDownLeftCorner = grassBounds.leftDownCorner();
        Vector2d worldTopRightCorner = grassBounds.rightUpperCorner();
        for(Vector2d position: animals.keySet()){
            worldDownLeftCorner = worldDownLeftCorner.lowerLeft(position);
            worldTopRightCorner = worldTopRightCorner.upperRight(position);
        }
        worldBounds = new Boundry(worldDownLeftCorner, worldTopRightCorner);
    }
    @Override
    public Optional<WorldElement> objectAt(Vector2d position) {
        //returns animal if is Present on position otherwise grass field if both are absent return null
        return Optional.ofNullable(super.objectAt(position).orElse(grassFields.get(position)));

    }

    @Override
    public Boundry getCurrentBounds() {
        updateCorners();
        return worldBounds;
    }

    @Override
    public ArrayList<WorldElement> getElements() {
        return Stream.concat(super.getElements().stream(), grassFields.values().stream())
                .collect(Collectors.toCollection(ArrayList::new));

    }
}
