package agh.ics.oop.model;

import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.interfaces.MapChangeListener;
import agh.ics.oop.model.interfaces.WorldElement;
import agh.ics.oop.model.interfaces.WorldMap;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected MapVisualizer map = new MapVisualizer(this);
    protected final ArrayList<MapChangeListener> observers = new ArrayList<>();

    public void addObserver(MapChangeListener observer){
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer){
        observers.remove(observer);
    }

    private void showMessage(String message){
        for(MapChangeListener observer: observers){
            observer.mapChanged(this, message);
        }
    }


    public void move(Animal animal, MoveDirection direction) throws PositionAlreadyOccupiedException {
        if(objectAt(animal.getPosition()) == animal){
            this.animals.remove(animal.getPosition());
            animal.move(direction,this);
            this.place(animal);
        }

        switch (direction){
            case FORWARD -> showMessage("Animal %s moved forward".formatted(animal));
            case BACKWARD -> showMessage("Animal %s moved backward".formatted(animal));
            case RIGHT -> showMessage("Animal %s turned right".formatted(animal));
            case LEFT -> showMessage("Animal %s turned left".formatted(animal));
        }
    }

    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        if(canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            showMessage("Animal moved into position: " + animal.getPosition());
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

