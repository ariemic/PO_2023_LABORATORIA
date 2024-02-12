package agh.ics.oop.model.interfaces;
@FunctionalInterface
public interface MapChangeListener {
    void mapChanged(WorldMap worldMap, String message);
}
