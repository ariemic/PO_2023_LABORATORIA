package agh.ics.oop.model;

import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.interfaces.MapChangeListener;
import agh.ics.oop.model.interfaces.WorldElement;
import agh.ics.oop.model.interfaces.WorldMap;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements WorldMap {
    protected final int mapID;
    protected  Map<Vector2d, Animal> animals;
    protected MapVisualizer map;
    protected final ArrayList<MapChangeListener> observers;

    public AbstractWorldMap(int mapID){
        this.mapID = mapID;
        this.map = new MapVisualizer(this);
        this.animals = new HashMap<>();
        this.observers = new ArrayList<>();

    }


    @Override
    public List<Animal> getOrderedAnimals(){
        return animals.values().stream()
                .sorted(Comparator
                        .comparing(Animal::getPosition)
                        .thenComparing(animal -> animal.getPosition().getX())
                        .thenComparing(animal -> animal.getPosition().getY()))
                .collect(Collectors.toList());

    }


    public void addObserver(MapChangeListener observer){
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer){
        observers.remove(observer);
    }

    private void mapChanged(String message){
        for(MapChangeListener observer: observers){
            observer.mapChanged(this, message);
        }
    }


    public void move(Animal animal, MoveDirection direction) throws PositionAlreadyOccupiedException {
        Vector2d oldPosition = animal.getPosition();
        String oldDirection = animal.toString();
        Optional<WorldElement> element = objectAt(animal.getPosition());
        if(element.isPresent() && element.get() == animal){
            this.animals.remove(animal.getPosition());
            animal.move(direction,this);
            this.place(animal);
        }

        switch (direction){
            case FORWARD -> {
                if (oldPosition != animal.getPosition()){
                mapChanged("Animal moved forward from %s to %s".formatted(oldPosition, animal.getPosition()));
            }}
            case BACKWARD -> {
                if (oldPosition != animal.getPosition()){
                    mapChanged("Animal moved backward from %s to %s".formatted(oldPosition, animal.getPosition()));
                }
            }
            case RIGHT -> mapChanged("Animal from position %s turned right, orientation changed from %s to %s".formatted(oldPosition, oldDirection, animal.toString()));
            case LEFT -> mapChanged("Animal from position %s turned left, orientation changed from %s to %s".formatted(oldPosition, oldDirection, animal.toString()));
        }
    }

    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        if(canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            mapChanged("Animal moved into position: " + animal.getPosition());
        }
        else{
            throw new PositionAlreadyOccupiedException(animal.getPosition());

        }
    }
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }
    public Optional<WorldElement> objectAt(Vector2d position) {
        return Optional.ofNullable(animals.get(position));
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

