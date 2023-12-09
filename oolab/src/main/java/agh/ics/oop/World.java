package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.interfaces.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class World {

    public static void main(String[] args) {
        try {
            ArrayList<Vector2d> positions = new ArrayList<>(List.of(new Vector2d(2, 8), new Vector2d(3, 4),new Vector2d(2,8)));
            ConsoleMapDisplay mapDisplay = new ConsoleMapDisplay();
            GrassField map = new GrassField(9, 1);
            map.addObserver(mapDisplay);
            Simulation simulation1 = new Simulation(positions, OptionsParser.parseDirections(args), map);

            String[] args2 = {"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"};
            ConsoleMapDisplay mapDisplay2 = new ConsoleMapDisplay();
            RectangularMap map2 = new RectangularMap(7,  7,2);
            map2.addObserver(mapDisplay2);
            ArrayList<Vector2d> positions2 = new ArrayList<>(List.of(new Vector2d(7, 8), new Vector2d(3, 4),new Vector2d(2,8)));
            Simulation simulation2 = new Simulation(positions2, OptionsParser.parseDirections(args2), map2);

            SimulationEngine simulationEngine = new SimulationEngine(new ArrayList<>(List.of(simulation1, simulation2)), 4);
            simulationEngine.runSync();
            simulationEngine.runAsync();
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        } catch (PositionAlreadyOccupiedException e) {
            throw new RuntimeException(e);
        }
    }

}
