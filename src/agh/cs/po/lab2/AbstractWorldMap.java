package agh.cs.po.lab2;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected List<Animal> animals = new ArrayList<>();
    protected Hashtable<Vector2d, Animal> usedMapCoords = new Hashtable<Vector2d, Animal>();


    protected AbstractWorldMap(Vector2d lowerLeft, Vector2d upperRight)
    {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    @Override
    public boolean placeAnimal(Animal animal)
    {
        if(!canMoveTo(animal.getPosition()))
            throw new IllegalArgumentException("Position " +animal.getPosition()+ " is occupied or is out of map!");
        this.usedMapCoords.put(animal.getPosition(), animal);
        this.animals.add(animal);
        return true;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !this.isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.usedMapCoords.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.usedMapCoords.getOrDefault(position, null);
    }

    public String toString()
    {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(this.lowerLeft, this.upperRight);
    }

    @Override
    public void run(MoveDirection[] directions) {
        for(int i = 0; i < directions.length; i++)
        {
            this.moveAnimal(this.animals.get(i%this.animals.size()), directions[i]);
//            System.out.println(this.toString() + " " + directions[i]);
        }
    }

    protected void moveAnimal(Animal thisAnimal, MoveDirection direction)
    {
        Vector2d previousPosition = thisAnimal.getPosition();
        thisAnimal.move(direction);
        if(!thisAnimal.getPosition().equals(previousPosition)) {
            this.positionChanged(previousPosition, thisAnimal.getPosition());
        }
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        Animal thisAnimal = this.usedMapCoords.get(oldPosition);
        this.usedMapCoords.remove(oldPosition);
        this.usedMapCoords.put(newPosition, thisAnimal);
    }

}
