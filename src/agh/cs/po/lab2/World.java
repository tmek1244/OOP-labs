package agh.cs.po.lab2;

public class World {
    public static void main(String[] args){
        Animal zwierze = new Animal();
        MoveDirection[] movements = OptionsParser.fromStrings(args);
        for(MoveDirection move: movements)
        {
            zwierze.move(move);
        }
        System.out.println(zwierze);
    }
}
