package agh.cs.po.lab2;

import java.util.Objects;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal()
    {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }

    @Override
    public String toString()
    {
        return "Kierunek: " + this.orientation + ", pozycja: " + this.position;
    }

    public void move(MoveDirection direction)
    {
        if(direction == MoveDirection.RIGHT)
            this.orientation = this.orientation.next();
        else if(direction == MoveDirection.LEFT)
            this.orientation = this.orientation.previous();
        else if(direction == MoveDirection.FORWARD)
            moveForward(this.orientation.toUnitVector());
        else if(direction == MoveDirection.BACKWARD)
            moveForward(Objects.requireNonNull(this.orientation.toUnitVector()).opposite());

    }
    private void moveForward(Vector2d moveDirection)
    {
        Vector2d nextPosition = this.position.add(moveDirection);
        if(nextPosition.precedes(new Vector2d(4,4)) && nextPosition.follows(new Vector2d(0,0)))
            this.position = nextPosition;
    }

    public MapDirection getOrientation()
    {
        return this.orientation;
    }

    public Vector2d getPosition()
    {
        return this.position;
    }

}
