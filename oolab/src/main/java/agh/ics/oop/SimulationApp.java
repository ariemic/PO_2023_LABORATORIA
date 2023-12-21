package agh.ics.oop;

import agh.ics.oop.model.GrassField;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class SimulationApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GrassField map = new GrassField(7, 1);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();

        SimulationPresenter presenter = loader.getController();
//        presenter.onSimulationStartClicked(); runs as you click start button, no need to run it manually
        map.addObserver(presenter);
        presenter.setWorldMap(map);

        configureStage(primaryStage, viewRoot);
        primaryStage.show();
    }


    private void configureStage(Stage primaryStage, BorderPane viewRoot){
        var scene = new Scene(viewRoot);
        primaryStage.setScene(viewRoot.getScene());
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
