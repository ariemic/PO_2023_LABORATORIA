package agh.ics.oop.presenter;

import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.Boundry;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.interfaces.MapChangeListener;
import agh.ics.oop.model.interfaces.WorldElement;
import agh.ics.oop.model.interfaces.WorldMap;
import agh.ics.oop.model.util.OptionsParser;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.List;

public class SimulationPresenter implements MapChangeListener{
    private WorldMap map;
    public Button startBtn;
    public TextField getParameters;
    public Label animalMoves;
    public GridPane gridMap;

    public void setWorldMap(WorldMap map){
        this.map = map;
    }

    private void drawMap(){
        Boundry bounds = this.map.getCurrentBounds();
        int width = bounds.rightUpperCorner().getX() - bounds.leftDownCorner().getX() + 1;
        int height = bounds.rightUpperCorner().getY() - bounds.leftDownCorner().getY() + 1;
        clearGrid();
        createGrid(width, height, bounds);
        putElements(height, bounds);
    }

    private void putElements(int height, Boundry bounds) {
        for(WorldElement element: map.getElements()){
            int newY = element.getPosition().getX() - bounds.leftDownCorner().getX() + 1;
            int newX = height - ( element.getPosition().getY() - bounds.leftDownCorner().getY()); //because I input values inot column from biggest to smallest
            Label elem = new Label(element.toString());
            gridMap.add(elem, newY, newX);
            GridPane.setHalignment(elem, HPos.CENTER);
        }
    }

    private void clearGrid() {
        gridMap.getChildren().retainAll(gridMap.getChildren().get(0)); // hack to retain visible grid lines
        gridMap.getColumnConstraints().clear();
        gridMap.getRowConstraints().clear();
    }

    private void createGrid(int width, int height, Boundry bounds) {
        gridMap.getColumnConstraints().add(new ColumnConstraints(30));
        gridMap.getRowConstraints().add(new RowConstraints(30));
        Label separator = new Label("y\\x");
        gridMap.add(separator, 0, 0);
        GridPane.setHalignment(separator, HPos.CENTER);

        //problem z tworzeniem cyfr
        for(int i=1; i <= width; i++){
            gridMap.getColumnConstraints().add(new ColumnConstraints(30));
//            Label digit = new Label(String.valueOf(i));
            Label digit = new Label(String.valueOf(i+bounds.leftDownCorner().getX()));
            gridMap.add(digit, i, 0); //row
            GridPane.setHalignment(digit, HPos.CENTER);
        }
//        int j = height;
        for(int i=1; i <= height; i++){
            gridMap.getRowConstraints().add(new RowConstraints(30));
//            Label digit = new Label(String.valueOf(j));
            Label digit = new Label(String.valueOf(height+bounds.leftDownCorner().getY()-i));
            gridMap.add(digit, 0, i);
            GridPane.setHalignment(digit, HPos.CENTER);
//            j--;
        }



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
