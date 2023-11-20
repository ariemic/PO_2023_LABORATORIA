package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;


public class AnimalTest {
    @Test
    public void testOrientation(){
        Animal animal = new Animal(new Vector2d(2,2));
        assertSame(animal.getOrientation(), MapDirection.NORTH);
        assertNotSame(animal.getOrientation(), MapDirection.EAST);
        assertNotSame(animal.getOrientation(), MapDirection.WEST);
        assertNotSame(animal.getOrientation(), MapDirection.SOUTH);

        animal.move(MoveDirection.RIGHT, null);
        assertSame(animal.getOrientation(), MapDirection.EAST);
        assertNotSame(animal.getOrientation(), MapDirection.NORTH);
        assertNotSame(animal.getOrientation(), MapDirection.WEST);
        assertNotSame(animal.getOrientation(), MapDirection.SOUTH);

        animal.move(MoveDirection.LEFT, null);
        assertSame(animal.getOrientation(), MapDirection.NORTH);
        assertNotSame(animal.getOrientation(), MapDirection.EAST);
        assertNotSame(animal.getOrientation(), MapDirection.WEST);
        assertNotSame(animal.getOrientation(), MapDirection.SOUTH);

        animal.move(MoveDirection.BACKWARD, null);
        assertSame(animal.getOrientation(), MapDirection.NORTH);
        assertNotSame(animal.getOrientation(), MapDirection.EAST);
        assertNotSame(animal.getOrientation(), MapDirection.WEST);
        assertNotSame(animal.getOrientation(), MapDirection.SOUTH);

        animal.move(MoveDirection.FORWARD, null);
        assertSame(animal.getOrientation(), MapDirection.NORTH);
        assertNotSame(animal.getOrientation(), MapDirection.EAST);
        assertNotSame(animal.getOrientation(), MapDirection.WEST);
        assertNotSame(animal.getOrientation(), MapDirection.SOUTH);

        animal.move(MoveDirection.RIGHT, null);
        animal.move(MoveDirection.FORWARD, null);
        animal.move(MoveDirection.BACKWARD, null);
        animal.move(MoveDirection.RIGHT, null);
        assertSame(animal.getOrientation(), MapDirection.SOUTH);

        Animal goat = new Animal(new Vector2d(4, 5));
        assertSame(goat.getOrientation(), MapDirection.NORTH);
        assertNotSame(goat.getOrientation(), MapDirection.SOUTH);
    }

        @Test
        public void testAnimalMovement() {
            Animal tiger = new Animal(new Vector2d(2,2));
            tiger.move(MoveDirection.FORWARD, null);
            assertTrue(tiger.isAt(new Vector2d(2, 3)));
            tiger.move(MoveDirection.LEFT, null);
            tiger.move(MoveDirection.FORWARD, null);
            assertTrue(tiger.isAt(new Vector2d(1, 3)));
            tiger.move(MoveDirection.BACKWARD, null);
            assertTrue(tiger.isAt(new Vector2d(2,3)));
            tiger.move(MoveDirection.LEFT, null);
            tiger.move(MoveDirection.FORWARD, null);
            assertTrue(tiger.isAt(new Vector2d(2, 2)));

            Animal animal = new Animal();
            animal.move(MoveDirection.FORWARD, null);
            assertEquals(new Vector2d(2, 3), animal.getPosition());
            assertEquals(MapDirection.NORTH, animal.getOrientation());

            animal.move(MoveDirection.RIGHT, null);
            assertEquals(new Vector2d(2, 3), animal.getPosition());
            assertEquals(MapDirection.EAST, animal.getOrientation());

            animal.move(MoveDirection.FORWARD, null);
            assertEquals(new Vector2d(3, 3), animal.getPosition());
            assertEquals(MapDirection.EAST, animal.getOrientation());

            animal.move(MoveDirection.BACKWARD, null);
            assertEquals(new Vector2d(2, 3), animal.getPosition());
            assertEquals(MapDirection.EAST, animal.getOrientation());
        }

        @Test
        public void testAnimalBoundary() {
            Animal animal = new Animal(new Vector2d(0, 0));
            animal.move(MoveDirection.BACKWARD, null);
            assertEquals(new Vector2d(0, 0), animal.getPosition());

            animal = new Animal(new Vector2d(4, 4));
            animal.move(MoveDirection.FORWARD, null);
            assertEquals(new Vector2d(4, 4), animal.getPosition());
        }
        @Test
        public void testSimulation() {
            List<Vector2d> positions = Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 3));
            String [] Data = {"f", "b", "kot", "mysz", "r", "pies", "l"};
            List<MoveDirection> moves = OptionsParser.parseDirections(Data);
//            Simulation simulation = new Simulation(positions, moves);
//            simulation.run();
//            assertEquals(new Vector2d(2, 3), simulation.getAnimals().get(0).getPosition());
//            assertEquals(MapDirection.EAST, simulation.getAnimals().get(0).getOrientation());
//            assertEquals(new Vector2d(3, 2), simulation.getAnimals().get(1).getPosition());
//            assertEquals(MapDirection.WEST, simulation.getAnimals().get(1).getOrientation());
        }


}
