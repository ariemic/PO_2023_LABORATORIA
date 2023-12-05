package agh.ics.oop.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RectangularMapTest {
    @Test
    public void TestPlace(){
        RectangularMap map = new RectangularMap(10,10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(5,8));
        Animal animal3 = new Animal(new Vector2d(4,10));
        Animal animal4 = new Animal(new Vector2d(5,8));

        assertTrue(map.place(animal1));
        assertTrue(map.place(animal2));
//        assertFalse(map.place(animal3));
        assertFalse(map.place(animal4));
    }
    @Test
    public void TestMove(){
        RectangularMap map = new RectangularMap(5,5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(3, 1));
        Animal animal3 = new Animal(new Vector2d(4,3));
        Animal animal4 = new Animal(new Vector2d(2,1));

        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        map.place(animal4);

        map.move(animal1,MoveDirection.FORWARD);
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
//        assertTrue(animal3.isAt(new Vector2d(4,3)));

        map.move(animal4,MoveDirection.BACKWARD);
        assertTrue(animal4.isAt(new Vector2d(2,0)));

        map.move(animal2,MoveDirection.FORWARD);
        assertTrue(animal2.isAt(new Vector2d(2,1)));
    }
    @Test
    public void TestIsOccupied(){
        Vector2d vec1 = new Vector2d(2,2);
        Vector2d vec2 = new Vector2d(3,1);
        Vector2d vec3 = new Vector2d(3,2);

        RectangularMap map = new RectangularMap(5,5);
        Animal animal1 = new Animal(vec1);
        Animal animal2 = new Animal(vec2);
        map.place(animal1);
        map.place(animal2);

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
    public void TestObjectAt(){
        Vector2d vec1 = new Vector2d(2,2);
        Vector2d vec2 = new Vector2d(3,1);
        Vector2d vec3 = new Vector2d(3,2);

        RectangularMap map = new RectangularMap(5,5);
        RectangularMap map2 = new RectangularMap(5,5);
        Animal animal1 = new Animal(vec1);
        Animal animal2 = new Animal(vec2);
        Animal animal3 = new Animal(vec1);
        map.place(animal1);
        map.place(animal2);
        map2.place(animal3);

        assertEquals(map.objectAt(vec1),animal1);
        assertEquals(map.objectAt(vec2),animal2);
        assertNotEquals(map.objectAt(vec1),animal2);
        assertNotEquals(map.objectAt(vec2),animal1);
        assertNotEquals(map.objectAt(vec3),animal1);

        assertEquals(map2.objectAt(vec1),animal3);
        assertNotEquals(map2.objectAt(vec1),animal1);
    }
    @Test
    public void TestCanMoveTo(){
        RectangularMap map1 = new RectangularMap(5,5);
        RectangularMap map2 = new RectangularMap(2,8);

        Vector2d vec1 = new Vector2d(2,2);
        Vector2d vec2 = new Vector2d(3,1);
        Vector2d vec3 = new Vector2d(0,2);
        Vector2d vec4 = new Vector2d(1,7);

        Animal animal1 = new Animal(vec1);
        Animal animal2 = new Animal(vec2);
        Animal animal3 = new Animal(vec3);
        Animal animal4 = new Animal(vec4);

        assertTrue(map1.canMoveTo(vec1));
        assertTrue(map1.canMoveTo(vec2));
        assertTrue(map1.canMoveTo(vec3));
        assertFalse(map1.canMoveTo(vec4));
        map1.place(animal1);
        assertFalse(map1.canMoveTo(vec1));
        map1.place(animal2);
        assertFalse(map1.canMoveTo(vec2));

//        assertFalse(map2.canMoveTo(vec1));
        assertFalse(map2.canMoveTo(vec2));
        assertTrue(map2.canMoveTo(vec3));
        assertTrue(map2.canMoveTo(vec4));
        map2.place(animal3);
        map2.place(animal4);
        assertFalse(map2.canMoveTo(vec3));
        assertFalse(map2.canMoveTo(vec4));
    }
}
