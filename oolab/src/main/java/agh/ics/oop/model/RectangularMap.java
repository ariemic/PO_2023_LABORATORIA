package agh.ics.oop.model;

import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.interfaces.WorldMap;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RectangularMap extends AbstractWorldMap {
    Map<Vector2d, Animal> animals = new HashMap<>();

    private final Boundry bounds;
    public RectangularMap(int width, int height){
        bounds = new Boundry(new Vector2d(0, 0), new Vector2d(width, height));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && position.follows(bounds.leftDownCorner()) && position.precedes(bounds.rightUpperCorner());
    }

    @Override
    public Boundry getCurrentBounds() {
        return bounds;
    }


}
