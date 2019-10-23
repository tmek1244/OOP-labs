package agh.cs.po.lab2;

public class World {
    public static void main(String[] args){
        RectangularMap map = new RectangularMap(5,5);
        Animal newAnimal = new Animal(map, new Vector2d(3,4));
        MoveDirection[] movements = OptionsParser.fromStrings(args);
        for(MoveDirection move: movements)
        {
            newAnimal.move(move);
        }
        System.out.println(newAnimal);
    }
}
