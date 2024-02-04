package agh.ics.oop.model;

import agh.ics.oop.model.enums.MapDirection;
import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.interfaces.WorldMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AnimalTest {
    @Test
    public void testOrientation(){
        WorldMap map = new RectangularMap(4,4, 1);
        Animal animal = new Animal(new Vector2d(2,2));
        assertSame(animal.getOrientation(), MapDirection.NORTH);
        assertNotSame(animal.getOrientation(), MapDirection.EAST);
        assertNotSame(animal.getOrientation(), MapDirection.WEST);
        assertNotSame(animal.getOrientation(), MapDirection.SOUTH);

        animal.move(MoveDirection.RIGHT, map);
        assertSame(animal.getOrientation(), MapDirection.EAST);
        assertNotSame(animal.getOrientation(), MapDirection.NORTH);
        assertNotSame(animal.getOrientation(), MapDirection.WEST);
        assertNotSame(animal.getOrientation(), MapDirection.SOUTH);

        animal.move(MoveDirection.LEFT, map);
        assertSame(animal.getOrientation(), MapDirection.NORTH);
        assertNotSame(animal.getOrientation(), MapDirection.EAST);
        assertNotSame(animal.getOrientation(), MapDirection.WEST);
        assertNotSame(animal.getOrientation(), MapDirection.SOUTH);

        animal.move(MoveDirection.BACKWARD, map);
        assertSame(animal.getOrientation(), MapDirection.NORTH);
        assertNotSame(animal.getOrientation(), MapDirection.EAST);
        assertNotSame(animal.getOrientation(), MapDirection.WEST);
        assertNotSame(animal.getOrientation(), MapDirection.SOUTH);

        animal.move(MoveDirection.FORWARD, map);
        assertSame(animal.getOrientation(), MapDirection.NORTH);
        assertNotSame(animal.getOrientation(), MapDirection.EAST);
        assertNotSame(animal.getOrientation(), MapDirection.WEST);
        assertNotSame(animal.getOrientation(), MapDirection.SOUTH);

        animal.move(MoveDirection.RIGHT, map);
        animal.move(MoveDirection.FORWARD, map);
        animal.move(MoveDirection.BACKWARD, map);
        animal.move(MoveDirection.RIGHT, map);
        assertSame(animal.getOrientation(), MapDirection.SOUTH);

        Animal goat = new Animal(new Vector2d(4, 5));
        assertSame(goat.getOrientation(), MapDirection.NORTH);
        assertNotSame(goat.getOrientation(), MapDirection.SOUTH);
    }

        @Test
        public void testAnimalMovement() {
            WorldMap map = new RectangularMap(4, 4, 2);
            Animal tiger = new Animal(new Vector2d(2,2));
            tiger.move(MoveDirection.FORWARD, map);
            assertTrue(tiger.isAt(new Vector2d(2, 3)));
            tiger.move(MoveDirection.LEFT, map);
            tiger.move(MoveDirection.FORWARD, map);
            assertTrue(tiger.isAt(new Vector2d(1, 3)));
            tiger.move(MoveDirection.BACKWARD, map);
            assertTrue(tiger.isAt(new Vector2d(2,3)));
            tiger.move(MoveDirection.LEFT, map);
            tiger.move(MoveDirection.FORWARD, map);
            assertTrue(tiger.isAt(new Vector2d(2, 2)));

            Animal animal = new Animal();
            animal.move(MoveDirection.FORWARD, map);
            assertEquals(new Vector2d(2, 3), animal.getPosition());
            assertEquals(MapDirection.NORTH, animal.getOrientation());

            animal.move(MoveDirection.RIGHT, map);
            assertEquals(new Vector2d(2, 3), animal.getPosition());
            assertEquals(MapDirection.EAST, animal.getOrientation());

            animal.move(MoveDirection.FORWARD, map);
            assertEquals(new Vector2d(3, 3), animal.getPosition());
            assertEquals(MapDirection.EAST, animal.getOrientation());

            animal.move(MoveDirection.BACKWARD, map);
            assertEquals(new Vector2d(2, 3), animal.getPosition());
            assertEquals(MapDirection.EAST, animal.getOrientation());
        }

        @Test
        public void testAnimalBoundary() {
            WorldMap map = new RectangularMap(4, 4, 3);
            Animal animal = new Animal(new Vector2d(0, 0));
            animal.move(MoveDirection.BACKWARD, map);
            assertEquals(new Vector2d(0, 0), animal.getPosition());

            animal = new Animal(new Vector2d(4, 4));
            animal.move(MoveDirection.FORWARD, map);
            assertEquals(new Vector2d(4, 4), animal.getPosition());
        }


}
