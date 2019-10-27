package agh.cs.po.lab2;

public enum MoveDirection {
    FORWARD, BACKWARD, LEFT, RIGHT;

    public String toString()
    {
        if(this == FORWARD)
            return "Forward";
        else if(this == BACKWARD)
            return "Backward";
        else if(this == LEFT)
            return "Left";
        else if(this == RIGHT)
            return "Right";
        return null;
    }
}
