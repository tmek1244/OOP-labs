package test;

import agh.cs.po.lab2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    IWorldMap map;

    @BeforeEach
    void setUp() {
        map = new RectangularMap(11, 6);
    }

    @Test
    void mapMovementTest1()
    {
        map.placeAnimal(new Animal(map));
        map.placeAnimal(new Animal(map,new Vector2d(3,4)));
        MoveDirection[] directions = OptionsParser.fromStrings("f b r l f f r r f f f f f f f f b b b l r r r".split(" "));
        String result = map.toString();
        map.run(directions);
        assertEquals(result, map.toString());
    }

    @Test
    void mapMovementTest2()
    {
        map.placeAnimal(new Animal(map));
        map.placeAnimal(new Animal(map,new Vector2d(3,4)));
        map.placeAnimal(new Animal(map, new Vector2d(1,1)));
        MoveDirection[] directions = OptionsParser.fromStrings("f l l l f l f f r l b r f b l l r r f r r l l l".split(" "));
        String result = map.toString();
        map.run(directions);
        assertEquals(result, map.toString());
    }

    @Test
    void mapMovementTest3()
    {
        map.placeAnimal(new Animal(map, new Vector2d(0,0)));
        map.placeAnimal(new Animal(map, new Vector2d(7, 5)));
        map.placeAnimal(new Animal(map, new Vector2d(9, 5)));
        map.placeAnimal(new Animal(map, new Vector2d(-10, -10)));
        MoveDirection[] directions = OptionsParser.fromStrings("f r b b b b l r l f f f f f l f f f".split(" "));
        map.run(directions);
        System.out.println(map);
        assertTrue(map.isOccupied(new Vector2d(0,0)));
        assertTrue(map.isOccupied(new Vector2d(6,2)));
        assertTrue(map.isOccupied(new Vector2d(8,2)));

    }
}