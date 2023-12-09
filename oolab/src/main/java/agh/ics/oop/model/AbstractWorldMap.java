package agh.ics.oop.model;

import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.interfaces.WorldElement;
import agh.ics.oop.model.interfaces.WorldMap;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected Vector2d worldTopRightCorner;
    protected Vector2d worldDownLeftCorner;
    protected MapVisualizer map = new MapVisualizer(this);

    public AbstractWorldMap(int width,int height){
        worldTopRightCorner = new Vector2d(width,height);
        worldDownLeftCorner = new Vector2d(0,0);
    }
    public AbstractWorldMap(){
        this(4,4);
    }

    public void move(Animal animal, MoveDirection direction) throws PositionAlreadyOccupiedException {
        if(objectAt(animal.getPosition()) == animal){
            this.animals.remove(animal.getPosition());
            animal.move(direction,this);
            this.place(animal);
        }
    }

    public boolean place(Animal animal) throws PositionAlreadyOccupiedException {
        if(canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            return true;
        }
        else{
            throw new PositionAlreadyOccupiedException(animal.getPosition());

        }
    }
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }
    public ArrayList<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

    @Override
    public abstract Boundry getCurrentBounds();

    @Override
    public String toString() {
        Boundry bounds = getCurrentBounds();
        return map.draw(bounds.leftDownCorner(), bounds.rightUpperCorner());
    }

}

