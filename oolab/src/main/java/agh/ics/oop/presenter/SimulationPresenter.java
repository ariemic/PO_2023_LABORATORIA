package agh.ics.oop.presenter;

import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.Boundry;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.interfaces.MapChangeListener;
import agh.ics.oop.model.interfaces.WorldMap;
import agh.ics.oop.model.util.OptionsParser;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class SimulationPresenter implements MapChangeListener{
    public Label testMap;
    private WorldMap map;
    public Button startBtn;
    public TextField getParameters;
    public Label animalMoves;
    public GridPane gridMap;

    public void setWorldMap(WorldMap map){
        this.map = map;
    }

    private void drawMap(){
        testMap.setText(String.valueOf(map));
        Boundry bounds = this.map.getCurrentBounds();
        int width = bounds.rightUpperCorner().getX() - bounds.leftDownCorner().getX() + 1;
        int height = bounds.rightUpperCorner().getY() - bounds.leftDownCorner().getY() + 1;

    }

    private void clearGrid() {

    }


    private void createGrid(int width, int height, Boundry bounds) {

    }

    @Override
    public void mapChanged(WorldMap worldMap, String message){
        Platform.runLater(this::drawMap);

    }

    public void onSimulationStartClicked() throws IllegalArgumentException{
        List<MoveDirection> moves = OptionsParser.parseDirections(getParameters.getText().split(" "));
        List<Vector2d> animalPositions = new ArrayList<>(List.of(new Vector2d(2, 2), new Vector2d(3, 5)));
        Simulation simulation = new Simulation(animalPositions, moves, map);
        SimulationEngine engine = new SimulationEngine(new ArrayList<>( List.of(simulation)), 6);
        engine.runAsync();
    }
}
