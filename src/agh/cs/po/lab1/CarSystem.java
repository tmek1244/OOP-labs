package agh.cs.po.lab1;

public class CarSystem {
    public static void main(String[] args) {
        System.out.println("Start");
        run(Direction.fromStrings(args));
        System.out.println("Stop");
    }

    private static void run(Direction[] directions)
    {
        for(int i = 0; i < directions.length; i++) {
            if(directions[i] == null)
                continue;
            switch(directions[i]){
                case FORWARD:
                    System.out.println("Jade do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Jade do tylu");
                    break;
                case LEFT:
                    System.out.println("Jade w lewo");
                    break;
                case RIGHT:
                    System.out.println("Jade w prawo");
                    break;
            }
        }
    }
}

