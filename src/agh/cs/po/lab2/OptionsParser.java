package agh.cs.po.lab2;

public class OptionsParser {
    static MoveDirection[] fromStrings(String[] args){
        MoveDirection[] directions = new MoveDirection[args.length];
        for(int i = 0; i < args.length; i++){
            directions[i] = changeStringToDirection(args[i].toLowerCase());
        }
        return directions;
    }

    private static MoveDirection changeStringToDirection(String arg){
        switch (arg){
            case "forward":
            case "f":
                return MoveDirection.FORWARD;
            case "backward":
            case "b":
                return MoveDirection.BACKWARD;
            case "l":
            case "left":
                return MoveDirection.LEFT;
            case "r":
            case "right":
                return MoveDirection.RIGHT;
            default:
                return null;
        }
    }
}
