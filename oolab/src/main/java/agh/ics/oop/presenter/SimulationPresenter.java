package agh.ics.oop.presenter;

import agh.ics.oop.model.interfaces.WorldElement;
import agh.ics.oop.model.interfaces.WorldMap;
import javafx.scene.control.Label;

public class SimulationPresenter {
    public Label infoLabel;
    private WorldMap map;
    public void setWorldMap(WorldMap map){
        this.map = map;
    }
}
