package agh.ics.oop.model.util;

import agh.ics.oop.model.interfaces.MapChangeListener;
import agh.ics.oop.model.interfaces.WorldMap;


public class ConsoleMapDisplay implements MapChangeListener {
    private int updatesCnt =0;
    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        updatesCnt++;
        System.out.print(worldMap);
        System.out.println("Number of changes is equal to:  " + updatesCnt + "\n\n");
        System.out.println("---------------------------------------");

    }
}