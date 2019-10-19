package test;

import agh.cs.po.lab2.Vector2d;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    private Vector2d x;
    private Vector2d y;
    private Vector2d z;
    private Vector2d t;
    @BeforeEach
    void setUp()
    {
        x = new Vector2d(1,2);
        y = new Vector2d(-2, 3);
        z = new Vector2d(-2, -1);
        t = new Vector2d(10, 0);
    }
    @Test
    void testToString() {
        assertEquals("(1,2)", x.toString());
        assertEquals("(-2,3)", y.toString());
        assertEquals("(-2,-1)", z.toString());
        assertEquals("(10,0)", t.toString());
    }

    @Test
    void precedes() {
        assertFalse(x.precedes(y));
        assertFalse(x.precedes(z));
        assertFalse(x.precedes(t));
        assertTrue(z.precedes(y));
    }

    @Test
    void follows() {
        assertFalse(x.follows(y));
        assertFalse(x.follows(y));
        assertFalse(x.follows(y));
        assertTrue(y.follows(z));
    }

    @Test
    void upperRight() {
        assertEquals(x, x.upperRight(z));
        assertEquals(new Vector2d(-2, 3), y.upperRight(z));
        assertEquals(t, z.upperRight(t));
        assertEquals(new Vector2d(10, 2), t.upperRight(x));
    }

    @Test
    void lowerLeft() {
        assertEquals(z, y.lowerLeft(z));
        assertEquals(z, z.lowerLeft(z));
        assertEquals(new Vector2d(1, 0), t.lowerLeft(x));
        assertEquals(new Vector2d(-2, 0), t.lowerLeft(y));
    }

    @Test
    void add() {
        assertEquals(new Vector2d(2, 4), x.add(x));
        assertEquals(new Vector2d(11, 2), t.add(x));
        assertEquals(new Vector2d(-4, 2), z.add(y));
        assertEquals(x.add(t), t.add(x));
    }

    @Test
    void subtract() {
        assertEquals(new Vector2d(0, 0), x.subtract(x));
        assertEquals(new Vector2d(-3, -3), z.subtract(x));
        assertEquals(new Vector2d(12, -3), t.subtract(y));
        assertEquals(new Vector2d(0, 4), y.subtract(z));
    }

    @Test
    void testEquals() {
        assertEquals(x, x);
        assertNotEquals(x, y);
        assertNotEquals(y, z);
    }

    @Test
    void opposite() {
        assertEquals(new Vector2d(-1, -2), x.opposite());
        assertEquals(new Vector2d(2, -3), y.opposite());
        assertEquals(new Vector2d(2, 1), z.opposite());
        assertEquals(new Vector2d(-10, 0), t.opposite());
    }
}