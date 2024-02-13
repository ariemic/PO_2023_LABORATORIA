package agh.ics.oop.model;

import agh.ics.oop.model.enums.MapDirection;
import agh.ics.oop.model.enums.MoveDirection;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.interfaces.WorldElement;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
public class GrassFieldTest {
    @Test
    public void TestPlace() throws PositionAlreadyOccupiedException {
        GrassField map = new GrassField(5, 1);

        Vector2d v1 = new Vector2d(2,2);
        Vector2d v2 = new Vector2d(100,-100);
        Animal animal1 = new Animal(v1);
        Animal animal2 = new Animal(v2);

        map.place(animal1);
        map.place(animal2);

        assertTrue(animal1.isAt(v1));
        assertTrue(animal2.isAt(v2));
    }

    @Test
    public void TestMove() throws PositionAlreadyOccupiedException{
        GrassField map = new GrassField(2,new Random(150), 1);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(3, 1));
        Animal animal3 = new Animal(new Vector2d(4,3));
        Animal animal4 = new Animal(new Vector2d(2,1));

        assertEquals(new Vector2d(2,1),map.getCurrentBounds().leftDownCorner());
        assertEquals(new Vector2d(4,2),map.getCurrentBounds().rightUpperCorner());

        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        map.place(animal4);

        assertEquals(new Vector2d(2,1),map.getCurrentBounds().leftDownCorner());
        assertEquals(new Vector2d(4,3),map.getCurrentBounds().rightUpperCorner());

        map.move(animal1, MoveDirection.FORWARD);
        assertTrue(animal1.isAt(new Vector2d(2,3)));
        assertEquals(MapDirection.NORTH,animal1.getOrientation());

        map.move(animal1,MoveDirection.LEFT);
        assertTrue(animal1.isAt(new Vector2d(2,3)));
        assertEquals(MapDirection.WEST,animal1.getOrientation());

        map.move(animal2,MoveDirection.LEFT);
        map.move(animal2,MoveDirection.FORWARD);
        assertTrue(animal2.isAt(new Vector2d(3,1)));
        assertEquals(MapDirection.WEST,animal2.getOrientation());

        map.move(animal3,MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST,animal3.getOrientation());
        map.move(animal3,MoveDirection.FORWARD);
        assertTrue(animal3.isAt(new Vector2d(5,3)));

        map.move(animal4,MoveDirection.BACKWARD);
        assertTrue(animal4.isAt(new Vector2d(2,0)));

        map.move(animal2,MoveDirection.FORWARD);
        assertTrue(animal2.isAt(new Vector2d(2,1)));

        assertEquals(new Vector2d(2,0),map.getCurrentBounds().leftDownCorner());
        assertEquals(new Vector2d(5,3),map.getCurrentBounds().rightUpperCorner());
    }
    @Test
    public void TestIsOccupied() throws PositionAlreadyOccupiedException{
        Vector2d vec1 = new Vector2d(2,2);
        Vector2d vec2 = new Vector2d(3,1);
        Vector2d vec3 = new Vector2d(3,2);

        GrassField map = new GrassField(3,new Random(300), 1);

        Animal animal1 = new Animal(vec1);
        Animal animal2 = new Animal(vec2);
        map.place(animal1);
        map.place(animal2);


        assertFalse(map.isOccupied(new Vector2d(0,2)));

        assertTrue(map.isOccupied(vec1));
        assertTrue(map.isOccupied(vec2));
        assertFalse(map.isOccupied(vec3));
        assertFalse(map.isOccupied(new Vector2d(3,7)));

        map.move(animal2,MoveDirection.FORWARD);

        assertTrue(map.isOccupied(vec1));
        assertFalse(map.isOccupied(vec2));
        assertTrue(map.isOccupied(vec3));
    }


    @Test
    public void TestObjectAt() throws PositionAlreadyOccupiedException{
        Vector2d animalPosition = new Vector2d(2,2);
        Vector2d grassPosition = new Vector2d(3, 3);
        Vector2d emptyPosition = new Vector2d(0, 0);

        Map<Vector2d, Animal> animals = new HashMap<>();
        Map<Vector2d, Grass> grasses = new HashMap<>();


        Animal animal = mock(Animal.class);
        animals.put(animalPosition, animal);

        Grass grass = mock(Grass.class);
        grasses.put(grassPosition, grass);

        GrassField grassField = new GrassField();
        grassField.grassFields = grasses;
        grassField.animals = animals;


        assertTrue(grassField.objectAt(animalPosition).isPresent());
        assertTrue(grassField.objectAt(grassPosition).isPresent());
        assertFalse(grassField.objectAt(emptyPosition).isPresent());

        //just one world element on position
        assertEquals(Optional.of(grass), grassField.objectAt(grassPosition));
        assertEquals(Optional.of(animal), grassField.objectAt(animalPosition));

        //empty position
        assertEquals(Optional.empty(), grassField.objectAt(emptyPosition));


        //grass and animal on the same position
        Vector2d repeatedPosition = new Vector2d(1, 1);
        animals.put(repeatedPosition, animal);
        grasses.put(repeatedPosition, grass);
        assertEquals(Optional.of(animal), grassField.objectAt(repeatedPosition));

    }





    @Test
    public void TestCanMoveTo() throws PositionAlreadyOccupiedException{
        GrassField map1 = new GrassField(5,new Random(100), 1);
        System.out.println(map1);
        Vector2d vec1 = new Vector2d(2,2);
        Vector2d vec2 = new Vector2d(3,1);
        Vector2d vec3 = new Vector2d(0,2);
        Vector2d vec4 = new Vector2d(1,7);

        Animal animal1 = new Animal(vec1);
        Animal animal2 = new Animal(vec2);

        assertTrue(map1.canMoveTo(vec1));
        assertTrue(map1.canMoveTo(vec2));
        assertTrue(map1.canMoveTo(vec3));
        assertTrue(map1.canMoveTo(vec4));
        map1.place(animal1);
        assertFalse(map1.canMoveTo(vec1));
        map1.place(animal2);
        assertFalse(map1.canMoveTo(vec2));

    }
}
