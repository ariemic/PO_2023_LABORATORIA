package agh.ics.oop.model;

import agh.ics.oop.model.interfaces.MapChangeListener;
import agh.ics.oop.model.interfaces.WorldMap;

public class ConsoleMapDisplay implements MapChangeListener {
    private int updatesCnt = 0;
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println(message);
        System.out.println(worldMap);
        updatesCnt++;
        System.out.println("Number of changes is equal to: " + updatesCnt + "\n");
    }
}
