package agh.ics.oop;

import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.interfaces.MapChangeListener;
import agh.ics.oop.model.util.ConsoleMapDisplay;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SimulationApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GrassField map = new GrassField(7, 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //addObserver przyjmuje jeden argument typu MapChangeListener który jest interfejsem funkcyjnym z jedną metodą abstrakcyją
        //wyrażenie lambda zachowuje się jak klasa anonimowa implementuje tą metode inaczej mówic robi to samo co klasa ConsoleMapDisplay() ale
        //bez tworzenia nowej klasy!!!
        //consoleMapDisplay ma dodatkowy atrubut któru zlicza ilość zmian na mapie, w lambdzie nie możemy tego zrobić bo nie ma gdzie go zadeklarować
        //nawet bez tego nie mogę całkowiecie pozbyć się tego obserwator bo są z nim połaczone zmiany mapy w GUI
        map.addObserver(((worldMap, message) -> {
            System.out.println(formatter.format(now) + " " + message);
        } ));

        map.addObserver(new ConsoleMapDisplay());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();

        SimulationPresenter presenter = loader.getController();
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
