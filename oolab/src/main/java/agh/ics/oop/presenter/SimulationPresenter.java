package agh.ics.oop.presenter;

import agh.ics.oop.model.Boundry;
import agh.ics.oop.model.interfaces.MapChangeListener;
import agh.ics.oop.model.interfaces.WorldMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SimulationPresenter implements MapChangeListener {
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
        this.clearGrid();
        createGrid(width, height, bounds);
    }

    private void clearGrid() {
        gridMap.getChildren().retainAll(gridMap.getChildren().get(0))
    }

    private void createGrid(int width, int height, Boundry bounds) {

    }

    @Override
    public void mapChanged(WorldMap worldMap, String message){
        this.drawMap();
    }

    public void onSimulationStartClicked(ActionEvent actionEvent) {
    }
}
