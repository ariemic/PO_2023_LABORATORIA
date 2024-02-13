package agh.ics.oop.model.interfaces;

import java.io.IOException;

@FunctionalInterface
public interface MapChangeListener {
    void mapChanged(WorldMap worldMap, String message);
}
