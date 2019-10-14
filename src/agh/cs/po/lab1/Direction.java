package agh.cs.po.lab1;

public enum Direction {
    FORWARD, BACKWARD, LEFT, RIGHT;

    static Direction[] fromStrings(String[] args){
        Direction[] directions = new Direction[args.length];
        for(int i = 0; i < args.length; i++){
            directions[i] = changeStringToDirection(args[i]);
        }
        return directions;
    }

    private static Direction changeStringToDirection(String arg){
        switch (arg){
            case "f":
                return FORWARD;
            case "b":
                return BACKWARD;
            case "l":
                return LEFT;
            case "r":
                return RIGHT;
            default:
                return null;
        }
    }
}
