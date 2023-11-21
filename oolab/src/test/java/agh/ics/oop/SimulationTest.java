package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SimulationTest {
    @Test
    public void runTest(){
        String[] args = {"r", "r", "f",  "f",  "f",  "b", "r" , "r",  "r",  "b",  "b" };
        List<MoveDirection> moveDirections = OptionsParser.parseDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap map = new RectangularMap(4, 4);
        Simulation simulation = new Simulation(positions, moveDirections, map);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();

        WorldMap testMap = new RectangularMap(4,4);
        Animal animal1 = new Animal(new Vector2d(3, 4), MapDirection.SOUTH);
        Animal animal2 = new Animal(new Vector2d(4, 2), MapDirection.WEST);

        testMap.place(animal1);
        testMap.place(animal2);

        assertTrue(animals.get(0).isAt(animal2.getPosition()));
        assertTrue(animals.get(1).isAt(animal1.getPosition()));
        assertEquals(animals.get(0).getOrientation(), animal2.getOrientation());
        assertEquals(animals.get(1).getOrientation(), animal1.getOrientation());

//        assertEquals(testMap, map);
    }
    @Test
    public void runTest2(){
        String[] args = {"l", "l", "f", "b", "r", "b", "f", "b"};
        List<MoveDirection> moveDirections = OptionsParser.parseDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(4,4), new Vector2d(0,0));
        WorldMap map = new RectangularMap(4, 4);
        Simulation simulation = new Simulation(positions, moveDirections, map);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();


        WorldMap testMap = new RectangularMap(4,4);
        Animal animal1 = new Animal(new Vector2d(3, 4), MapDirection.NORTH);
        Animal animal2 = new Animal(new Vector2d(3, 0), MapDirection.WEST);
        testMap.place(animal1);
        testMap.place(animal2);

        assertTrue(animals.get(1).isAt(animal2.getPosition()));
        assertTrue(animals.get(0).isAt(animal1.getPosition()));
        assertEquals(animals.get(1).getOrientation(), animal2.getOrientation());
        assertEquals(animals.get(0).getOrientation(), animal1.getOrientation());

    }
    @Test
    public void runTest3(){
        String[] args = {"l", "l", "f", "b", "r", "b", "f", "b"};
        List<MoveDirection> moveDirections = OptionsParser.parseDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(1,2), new Vector2d(0,4), new Vector2d(0, 0));
        WorldMap map = new RectangularMap(4, 4);
        Simulation simulation = new Simulation(positions, moveDirections, map);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();

        WorldMap testMap = new RectangularMap(4,4);
        Animal animal1 = new Animal(new Vector2d(0, 3), MapDirection.NORTH);
        Animal animal2 = new Animal(new Vector2d(1, 2), MapDirection.WEST);
        Animal animal3 = new Animal(new Vector2d(0, 0), MapDirection.NORTH);
        testMap.place(animal1);
        testMap.place(animal2);
        testMap.place(animal3);

        assertTrue(animals.get(0).isAt(animal2.getPosition()));
        assertTrue(animals.get(2).isAt(animal3.getPosition()));
        assertTrue(animals.get(1).isAt(animal1.getPosition()));
        assertEquals(animals.get(1).getOrientation(), animal1.getOrientation());
        assertEquals(animals.get(0).getOrientation(), animal2.getOrientation());
        assertEquals(animals.get(2).getOrientation(), animal3.getOrientation());

    }

    @Test
    public void runTest4(){
        String[] args = {"b", "b", "b", "b", "r", "r", "b", "b", "b", "b"};
        List<MoveDirection> moveDirections = OptionsParser.parseDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(3,3));
        WorldMap map = new RectangularMap(3, 3);
        Simulation simulation = new Simulation(positions, moveDirections, map);
        simulation.run();
        List<Animal> animals = simulation.getAnimals();
        WorldMap testMap = new RectangularMap(3,3);
        Animal animal1 = new Animal(new Vector2d(3, 3), MapDirection.SOUTH);
        testMap.place(animal1);

        assertTrue(animals.get(0).isAt(animal1.getPosition()));
        assertEquals(animals.get(0).getOrientation(), animal1.getOrientation());


    }
}




