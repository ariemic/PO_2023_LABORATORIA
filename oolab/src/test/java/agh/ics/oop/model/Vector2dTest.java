package agh.ics.oop.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void testEquals() {
        Vector2d v1 = new Vector2d(2, 1);
        Vector2d v2 = new Vector2d(2, 1);
        Vector2d v3 = new Vector2d(5, 1);

        assertEquals(v1, v2);
        assertNotEquals(v1, v3);
    }

    @Test
    public void testToString() {
        Vector2d v = new Vector2d(80, 2);
        assertEquals("(80,2)", v.toString());
        assertNotEquals("(800, 2)", v.toString());
    }

    @Test
    public void testPrecedes() {
        Vector2d v1 = new Vector2d(4, 4);
        Vector2d v2 = new Vector2d(2, 4);
        Vector2d v3 = new Vector2d(4, 2);
        Vector2d v4 = new Vector2d(2, 2);
        Vector2d v5 = new Vector2d(4, 4);
        Vector2d v6 = new Vector2d(12, 4);
        Vector2d v7 = new Vector2d(4, 12);
        Vector2d v8 = new Vector2d(12, 12);
        Vector2d v9 = new Vector2d(12, 2);
        Vector2d v10 = new Vector2d(2, 10);


        assertFalse(v1.precedes(v2));
        assertFalse(v1.precedes(v3));
        assertFalse(v1.precedes(v4));
        assertTrue(v1.precedes(v5));
        assertTrue(v1.precedes(v6));
        assertTrue(v1.precedes(v7));
        assertTrue(v1.precedes(v8));
        assertFalse(v1.precedes(v9));
        assertFalse(v1.precedes(v10));
    }

    @Test
    public void testFollows() {
        Vector2d v1 = new Vector2d(4, 4);
        Vector2d v2 = new Vector2d(2, 4);
        Vector2d v3 = new Vector2d(4, 2);
        Vector2d v4 = new Vector2d(2, 2);
        Vector2d v5 = new Vector2d(4, 4);
        Vector2d v6 = new Vector2d(12, 4);
        Vector2d v7 = new Vector2d(4, 12);
        Vector2d v8 = new Vector2d(12, 12);
        Vector2d v9 = new Vector2d(12, 2);
        Vector2d v10 = new Vector2d(2, 10);

        assertTrue(v1.follows(v2));
        assertTrue(v1.follows(v3));
        assertTrue(v1.follows(v4));
        assertTrue(v1.follows(v5));
        assertFalse(v1.follows(v6));
        assertFalse(v1.follows(v7));
        assertFalse(v1.follows(v8));
        assertFalse(v1.follows(v9));
        assertFalse(v1.follows(v10));
    }
    @Test
    public void testUpperRight() {
        Vector2d v1 = new Vector2d(2, 12);
        Vector2d v2 = new Vector2d(8, 10);
        Vector2d v3 = new Vector2d(2, 12);

        assertEquals(new Vector2d(8, 12), v1.upperRight(v2));
        assertEquals(new Vector2d(2, 12), v1.upperRight(v3));
    }
    @Test
    public void testLowerLeft() {
        Vector2d v1 = new Vector2d(2, 12);
        Vector2d v2 = new Vector2d(8, 10);
        Vector2d v3 = new Vector2d(2, 12);

        assertEquals(new Vector2d(2, 10), v1.lowerLeft(v2));
        assertEquals(new Vector2d(2, 12), v1.lowerLeft(v3));
    }
    @Test
    public void testAdd() {
        Vector2d v1 = new Vector2d(12, 2);
        Vector2d v2 = new Vector2d(8, 3);

        assertEquals(new Vector2d(20, 5), v1.add(v2));
    }
    @Test
    public void testSubtract() {
        Vector2d v1 = new Vector2d(12, 2);
        Vector2d v2 = new Vector2d(8, 3);

        assertEquals(new Vector2d(4, -1), v1.subtract(v2));
    }
    @Test
    public void testOpposite() {
        Vector2d v1 = new Vector2d(0, 9);
        Vector2d v2 = new Vector2d(-2, 8);

        assertEquals(new Vector2d(0, -9), v1.opposite());
        assertEquals(new Vector2d(2, -8), v2.opposite());
    }

}
