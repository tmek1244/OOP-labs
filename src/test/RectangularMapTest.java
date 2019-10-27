package test;

import agh.cs.po.lab2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    IWorldMap map;

    @BeforeEach
    void setUp() {
        map = new RectangularMap(10, 5);
    }

    @Test
    void mapMovementTest1()
    {
        map.place(new Animal(map));
        map.place(new Animal(map,new Vector2d(3,4)));
        MoveDirection[] directions = OptionsParser.fromStrings("f b r l f f r r f f f f f f f f b b b l r r r".split(" "));
        String result = map.toString();
        map.run(directions);
        assertEquals(result, map.toString());
    }

    @Test
    void mapMovementTest2()
    {
        map.place(new Animal(map));
        map.place(new Animal(map,new Vector2d(3,4)));
        map.place(new Animal(map, new Vector2d(1,1)));
        MoveDirection[] directions = OptionsParser.fromStrings("f l l l f l f f r l b r f b l l r r f r r l l l".split(" "));
        String result = map.toString();
        map.run(directions);
        assertEquals(result, map.toString());
    }

    @Test
    void mapMovementTest3()
    {
        map.place(new Animal(map, new Vector2d(0,0)));
        map.place(new Animal(map, new Vector2d(10, 5)));
        map.place(new Animal(map, new Vector2d(9, 5)));
        MoveDirection[] directions = OptionsParser.fromStrings("".split("b f f b f f b "));
        String result = map.toString();
        map.run(directions);
        assertEquals(result, map.toString());
    }
}