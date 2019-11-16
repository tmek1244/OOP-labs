package agh.cs.po.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Animal implements IMapElement{
    private MapDirection orientation;
    private Vector2d position;
    private IWorldMap map;
    private List<IPositionChangeObserver> observers = new ArrayList<>();

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

    public void move(MoveDirection direction)
    {
        Vector2d oldPosition = this.getPosition();
        if(direction == MoveDirection.RIGHT) {
            this.orientation = this.orientation.next();
        }
        else if(direction == MoveDirection.LEFT) {
            this.orientation = this.orientation.previous();
        }
        else if(direction == MoveDirection.FORWARD)
            moveBy(this.orientation.toUnitVector());
        else  if(direction == MoveDirection.BACKWARD)
            moveBy(Objects.requireNonNull(this.orientation.toUnitVector()).opposite());
        this.positionChanged(oldPosition);
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

    private void moveBy(Vector2d moveDirection) {
        Vector2d nextPosition = this.position.add(moveDirection);
        if (map.canMoveTo(nextPosition)) {
            this.position = nextPosition;
        }
    }
    public MapDirection getOrientation()
    {
        return this.orientation;
    }

    public Vector2d getPosition()
    {
        return this.position;
    }


    void addObserver(IPositionChangeObserver observer)
    {
        this.observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer)
    {
        this.observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition)
    {
        for(IPositionChangeObserver observer: this.observers)
        {
            observer.positionChanged(oldPosition, this.getPosition());
        }
    }
}
