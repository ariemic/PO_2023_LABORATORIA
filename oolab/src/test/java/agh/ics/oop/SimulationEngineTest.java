package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.enums.MapDirection;
import agh.ics.oop.model.util.ConsoleMapDisplay;
import agh.ics.oop.model.util.OptionsParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngineTest {
    @Test
    public void TestRun() throws InterruptedException {
        ConsoleMapDisplay observer = new ConsoleMapDisplay();
        String[] args1 = {"f","b","r","l","f","f","r","r","b","f","l","l","f","f","f","f"};
        ArrayList<Vector2d> positions1 = new ArrayList<>(List.of(new Vector2d(7, 8), new Vector2d(3, 4),new Vector2d(2,8)));
        GrassField map1 = new GrassField(7,1);
        map1.addObserver(observer);
        Simulation simulation1 = new Simulation(positions1, OptionsParser.parseDirections(args1), map1);
        List<Animal> animals1= simulation1.getAnimals();

        String[] args2 = {"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"};
        ArrayList<Vector2d> positions2 = new ArrayList<>(List.of(new Vector2d(2,2), new Vector2d(3,4)));
        RectangularMap map2 = new RectangularMap(5,5,2);
        map2.addObserver(observer);
        Simulation simulation2 = new Simulation(positions2, OptionsParser.parseDirections(args2),map2);
        List<Animal> animals2= simulation2.getAnimals();

        SimulationEngine simulationEngine = new SimulationEngine(new ArrayList<>(List.of(simulation1,simulation2)),4);
        simulationEngine.runAsyncInThreadPool();
        simulationEngine.awaitSimulationEnds();


        Assertions.assertEquals(animals1.get(0).getOrientation(), MapDirection.NORTH);
        Assertions.assertEquals(animals1.get(1).getOrientation(), MapDirection.NORTH);
        Assertions.assertEquals(animals1.get(2).getOrientation(), MapDirection.NORTH);

        Assertions.assertEquals(animals2.get(0).getOrientation(), MapDirection.SOUTH);
        Assertions.assertEquals(animals2.get(1).getOrientation(), MapDirection.NORTH);


        System.out.print("System zakonczyl dzialanie");
    }
}
