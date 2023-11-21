package agh.ics.oop.model;

import agh.ics.oop.model.MapDirection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class MapDirectionTest {
    @Test
    public void testNext() {
        assertEquals(MapDirection.EAST, MapDirection.NORTH.next());
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next());
        assertEquals(MapDirection.WEST, MapDirection.SOUTH.next());
        assertEquals(MapDirection.NORTH, MapDirection.WEST.next());

        assertNotEquals(MapDirection.SOUTH, MapDirection.NORTH.next());
        assertNotEquals(MapDirection.SOUTH, MapDirection.SOUTH.next());
        assertNotEquals(MapDirection.SOUTH, MapDirection.WEST.next());

        assertNotEquals(MapDirection.EAST, MapDirection.EAST.next());
        assertNotEquals(MapDirection.EAST, MapDirection.SOUTH.next());
        assertNotEquals(MapDirection.EAST, MapDirection.WEST.next());

        assertNotEquals(MapDirection.NORTH, MapDirection.NORTH.next());
        assertNotEquals(MapDirection.NORTH, MapDirection.EAST.next());
        assertNotEquals(MapDirection.NORTH, MapDirection.SOUTH.next());

        assertNotEquals(MapDirection.WEST, MapDirection.NORTH.next());
        assertNotEquals(MapDirection.WEST, MapDirection.EAST.next());
        assertNotEquals(MapDirection.WEST, MapDirection.WEST.next());

    }

    @Test
    public void testPrevious() {
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous());
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous());
        assertEquals(MapDirection.WEST, MapDirection.NORTH.previous());
        assertEquals(MapDirection.NORTH, MapDirection.EAST.previous());

        assertNotEquals(MapDirection.SOUTH, MapDirection.NORTH.previous());
        assertNotEquals(MapDirection.SOUTH, MapDirection.SOUTH.previous());
        assertNotEquals(MapDirection.SOUTH, MapDirection.EAST.previous());

        assertNotEquals(MapDirection.WEST, MapDirection.SOUTH.previous());
        assertNotEquals(MapDirection.WEST, MapDirection.EAST.previous());
        assertNotEquals(MapDirection.WEST, MapDirection.WEST.previous());

        assertNotEquals(MapDirection.EAST, MapDirection.EAST.previous());
        assertNotEquals(MapDirection.EAST, MapDirection.NORTH.previous());
        assertNotEquals(MapDirection.EAST, MapDirection.WEST.previous());


        assertNotEquals(MapDirection.NORTH, MapDirection.NORTH.previous());
        assertNotEquals(MapDirection.NORTH, MapDirection.WEST.previous());
        assertNotEquals(MapDirection.NORTH, MapDirection.SOUTH.previous());
    }

}



