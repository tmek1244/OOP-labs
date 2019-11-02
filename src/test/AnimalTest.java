package test;

import agh.cs.po.lab2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    private Animal testAnimal;
    RectangularMap map = new RectangularMap(5,5);

    @BeforeEach
    void setUp()
    {
        testAnimal = new Animal(map);
    }

    @Test
    void checkOrientation()
    {
        assertEquals(MapDirection.NORTH, testAnimal.getOrientation());
        testAnimal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST, testAnimal.getOrientation());
        testAnimal.move(MoveDirection.LEFT);
        testAnimal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.EAST, testAnimal.getOrientation());
        testAnimal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.SOUTH, testAnimal.getOrientation());
    }

    @Test
    void checkPosition()
    {
        assertEquals(new Vector2d(2,2), testAnimal.getPosition());
        testAnimal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,3), testAnimal.getPosition());
        testAnimal.move(MoveDirection.FORWARD);
        System.out.println(map);
        testAnimal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,4), testAnimal.getPosition());
        testAnimal.move(MoveDirection.LEFT);
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(0,4), testAnimal.getPosition());
        testAnimal.move(MoveDirection.LEFT);
        testAnimal.move(MoveDirection.LEFT);
        testAnimal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(1,4), testAnimal.getPosition());
        testAnimal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(0,4), testAnimal.getPosition());
    }

    @Test
    void stayInMap()
    {
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);
        assertTrue(testAnimal.getPosition().precedes(new Vector2d(4,4)) &&
                testAnimal.getPosition().follows(new Vector2d(0,0)));
        testAnimal.move(MoveDirection.LEFT);
        testAnimal.move(MoveDirection.BACKWARD);
        testAnimal.move(MoveDirection.BACKWARD);
        testAnimal.move(MoveDirection.BACKWARD);
        testAnimal.move(MoveDirection.BACKWARD);
        assertTrue(testAnimal.getPosition().precedes(new Vector2d(4,4)) &&
                testAnimal.getPosition().follows(new Vector2d(0,0)));
    }
}