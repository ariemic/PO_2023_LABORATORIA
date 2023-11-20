package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationTest {
    @Test
    public void runTest(){
        String[] args = {"f", "b", "r", "l", "f", "f"};
        List<MoveDirection> moveDirections = OptionsParser.parseDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap map = new RectangularMap(4, 4);
        Simulation simulation = new Simulation(positions, moveDirections, map);
        simulation.run();

        WorldMap testMap = new RectangularMap(4,4);
        Animal animal1 = new Animal(new Vector2d(2, 3), MapDirection.EAST);
        Animal animal2 = new Animal(new Vector2d(3, 3), MapDirection.WEST);
        testMap.place(animal1);
        testMap.place(animal2);

        assertEquals(map, testMap);
    }
    @Test
    public void runTest2(){
        String[] args = {"f", "r", "f", "f", "b", "r", "b", "f", "l", "b"};
        List<MoveDirection> moveDirections = OptionsParser.parseDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(4,4), new Vector2d(0,0));
        WorldMap map = new RectangularMap(4, 4);
        Simulation simulation = new Simulation(positions, moveDirections, map);
        simulation.run();

        WorldMap testMap = new RectangularMap(4,4);
        Animal animal1 = new Animal(new Vector2d(4, 2), MapDirection.WEST);
        Animal animal2 = new Animal(new Vector2d(1, 1), MapDirection.SOUTH);
        testMap.place(animal1);
        testMap.place(animal2);
        assertEquals(map, testMap);
    }
    @Test
    public void runTest3(){
        String[] args = {"r", "l", "r", "r", "f", "f"};
        List<MoveDirection> moveDirections = OptionsParser.parseDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(0, 0));
        WorldMap map = new RectangularMap(4, 4);
        Simulation simulation = new Simulation(positions, moveDirections, map);
        simulation.run();

        WorldMap testMap = new RectangularMap(4,4);
        Animal animal1 = new Animal(new Vector2d(2, 2), MapDirection.SOUTH);
        Animal animal2 = new Animal(new Vector2d(2, 4), MapDirection.WEST);
        Animal animal3 = new Animal(new Vector2d(1, 0), MapDirection.EAST);
        testMap.place(animal1);
        testMap.place(animal2);
        testMap.place(animal3);
        assertEquals(map, testMap);
    }

    @Test
    public void runTest4(){
        String[] args = {"f", "f", "f", "r", "f", "f", "f", "f", "f", "f", "r", "f", "f", "f", "f",
                "f", "f", "r", "f", "f", "f", "f", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> moveDirections = OptionsParser.parseDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2));
        WorldMap map = new RectangularMap(2, 2);
        Simulation simulation = new Simulation(positions, moveDirections, map);
        simulation.run();

        WorldMap testMap = new RectangularMap(2,2);
        Animal animal1 = new Animal(new Vector2d(0, 2), MapDirection.NORTH);
        testMap.place(animal1);
        assertEquals(map, testMap);
    }
}
