package agh.cs.po.lab2;

public class World {
    public static void main(String[] args){
//        RectangularMap map = new RectangularMap(5,5);
//        Animal newAnimal = new Animal(map, new Vector2d(3,4));
//        MoveDirection[] movements = OptionsParser.fromStrings(args);
//        for(MoveDirection move: movements)
//        {
//            newAnimal.move(move);
//        }
//        System.out.println(newAnimal);
        try {
            MoveDirection[] directions = OptionsParser.fromStrings(args);
            IWorldMap map = new RectangularMap(10, 5);
            //        map.place(new Animal(map));
            map.placeAnimal(new Animal(map, new Vector2d(0, 0)));
            map.placeAnimal(new Animal(map, new Vector2d(9, 3)));
//            map.placeAnimal(new Animal(map, new Vector2d(9, 3)));
            System.out.println(map);
            map.run(directions);
            System.out.println(map);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e);
        }
    }
}
