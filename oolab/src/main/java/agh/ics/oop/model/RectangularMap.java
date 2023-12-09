package agh.ics.oop.model;

import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.interfaces.WorldMap;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RectangularMap implements WorldMap {
    Map<Vector2d, Animal> animals = new HashMap<>();

    int width;
    int height;
    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.getX() >= 0 && position.getX() <= width && position.getY() >= 0 && position.getY() <= height){
            return !isOccupied(position);
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (!animals.containsKey(animal.getPosition())) return;
        animals.remove(animal.getPosition());
        animal.move(direction, this);
        animals.put(animal.getPosition(), animal);

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        if (!animals.containsKey(position)) return null;
        return animals.get(position);
    }

    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(new Vector2d(0, 0), new Vector2d(width, height));
    }

    public Map<Vector2d, Animal> getAnimals() {
        return animals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RectangularMap map)) return false;
        return getWidth() == map.getWidth() && getHeight() == map.getHeight() && Objects.equals(getAnimals(), map.getAnimals());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnimals(), getWidth(), getHeight());
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
