package agh.cs.po.lab2;

import java.util.Objects;

public class Animal implements IMapElement{
    private MapDirection orientation;
    private Vector2d position;
    private IWorldMap map;

    public Animal(IWorldMap map)
    {
        this(map, new Vector2d(2,2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition)
    {
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
    }

    @Override
    public String toString()
    {
        return this.orientation.toString();
    }

    public boolean move(MoveDirection direction)
    {
        if(direction == MoveDirection.RIGHT) {
            this.orientation = this.orientation.next();
            return false;
        }
        if(direction == MoveDirection.LEFT) {
            this.orientation = this.orientation.previous();
            return false;
        }
        if(direction == MoveDirection.FORWARD)
            return moveBy(this.orientation.toUnitVector());
        if(direction == MoveDirection.BACKWARD)
            return moveBy(Objects.requireNonNull(this.orientation.toUnitVector()).opposite());
        return false;
    }

    public Vector2d getPredictedPosition(MoveDirection direction)
    {
        if(direction == MoveDirection.FORWARD)
            return this.position.add(Objects.requireNonNull(this.orientation.toUnitVector()));
        else if(direction == MoveDirection.BACKWARD)
            return this.position.add(Objects.requireNonNull(this.orientation.toUnitVector()).opposite());
        else
            return this.position;
    }

    private boolean moveBy(Vector2d moveDirection) {
        Vector2d nextPosition = this.position.add(moveDirection);
        if (map.canMoveTo(nextPosition)) {
            this.position = nextPosition;
            return true;
        }
        return false;
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
