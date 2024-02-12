package agh.ics.oop.model;
import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {

    private final Boundry bounds;
    public RectangularMap(int width, int height, int mapID){
        super(mapID);
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
