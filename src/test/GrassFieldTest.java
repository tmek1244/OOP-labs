package test;

import agh.cs.po.lab2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    IWorldMap map;
    private List<Grass> grasses = new ArrayList<>();

    @BeforeEach
    void setUp() {
//        grasses.add(new Grass(new Vector2d(-4,-4)));
//        grasses.add(new Grass(new Vector2d(7,7)));
//        grasses.add(new Grass(new Vector2d(3,6)));
//        grasses.add(new Grass(new Vector2d(2,0)));
        map = new GrassField(10);
    }

    @Test
    void mapMovementTest1()
    {
        map.placeAnimal(new Animal(map));
        map.placeAnimal(new Animal(map,new Vector2d(3,4)));
        MoveDirection[] directions = OptionsParser.fromStrings("f b r l f f r r f f f f f f f f b b b l r r r".split(" "));
        String result = map.toString();
        map.run(directions);
//        System.out.println(map);
//        System.out.println(result);
        assertTrue(map.objectAt(new Vector2d(3,6)) instanceof Animal);
        assertTrue(map.isOccupied(new Vector2d(2,1)));
//        assertEquals(result, map.toString());
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
        map.placeAnimal(new Animal(map, new Vector2d(1, 1)));
        map.placeAnimal(new Animal(map, new Vector2d(-1, 2)));
        MoveDirection[] directions = OptionsParser.fromStrings("b f f b f f b ".split(" "));
        String result = map.toString();
        map.run(directions);
//        System.out.println(map);
        assertTrue(map.isOccupied(new Vector2d(0,-3)));
        assertTrue(map.isOccupied(new Vector2d(-1,4)));
        assertTrue(map.isOccupied(new Vector2d(1,3)));
    }

    @Test
    void mapMovementTest4()
    {
        map.placeAnimal(new Animal(map, new Vector2d(0,0)));
        map.placeAnimal(new Animal(map, new Vector2d(10, 5)));
        map.placeAnimal(new Animal(map, new Vector2d(9, 5)));
        MoveDirection[] directions = OptionsParser.fromStrings("b f l r f l b f f f f r f f f b f".split(" "));
        String result = map.toString();
        map.run(directions);
        assertTrue(map.isOccupied(new Vector2d(0,-1)));
        assertTrue(map.isOccupied(new Vector2d(8,4)));
        assertTrue(map.isOccupied(new Vector2d(10,11)));
    }

}