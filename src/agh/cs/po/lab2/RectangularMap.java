package agh.cs.po.lab2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public class RectangularMap implements IWorldMap{
    private Vector2d lowerLeft;
    private Vector2d upperRight;
    private List<Animal> animals = new ArrayList<>();
    private Hashtable<String, Animal> usedMapCoords = new Hashtable<String, Animal>();

    public RectangularMap(int width, int height)
    {
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(this.upperRight) && position.follows(this.lowerLeft);
    }

    @Override
    public boolean place(Animal animal)
    {
        if(this.isOccupied(animal.getPosition()))
            return false;
        this.usedMapCoords.put(animal.getPosition().toString(), animal);
        this.animals.add(animal);
        return true;
    }

    @Override
    public void run(MoveDirection[] directions) {

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
}
