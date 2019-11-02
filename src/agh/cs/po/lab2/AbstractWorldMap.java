package agh.cs.po.lab2;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected List<Animal> animals = new ArrayList<>();
    protected Hashtable<String, IMapElement> usedMapCoords = new Hashtable<String, IMapElement>();

    protected AbstractWorldMap(Vector2d lowerLeft, Vector2d upperRight)
    {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    @Override
    public boolean placeAnimal(Animal animal)
    {
        if(!canMoveTo(animal.getPosition()))
            return false;
        this.usedMapCoords.put(animal.getPosition().toString(), animal);
        this.animals.add(animal);
        return true;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !this.isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.usedMapCoords.containsKey(position.toString());
    }

    @Override
    public Object objectAt(Vector2d position) {
        if(this.isOccupied(position))
            return this.usedMapCoords.get(position.toString());
        return null;
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
        if(thisAnimal.move(direction)) {
            this.usedMapCoords.remove(previousPosition.toString());
            this.usedMapCoords.put(thisAnimal.getPosition().toString(), thisAnimal);
        }
    }
}
