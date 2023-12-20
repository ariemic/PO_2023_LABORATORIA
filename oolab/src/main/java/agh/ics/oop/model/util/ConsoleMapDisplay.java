package agh.ics.oop.model.util;

import agh.ics.oop.model.interfaces.MapChangeListener;
import agh.ics.oop.model.interfaces.WorldMap;
import agh.ics.oop.presenter.SimulationPresenter;

public class ConsoleMapDisplay implements MapChangeListener {
    private int updatesCnt =0;
    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        System.out.println("MAP " + worldMap.getId());
        System.out.println(message);
        System.out.print(worldMap);
        updatesCnt++;
        System.out.println("Number of changes is equal to:  " + updatesCnt + "\n\n");
    }
}