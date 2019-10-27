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
        if(!this.animals.contains(animal))
            this.animals.add(animal);
        return true;
    }

    @Override
    public void run(MoveDirection[] directions) {
        for(int i = 0; i < directions.length; i++)
        {
            this.moveAnimal(this.animals.get(i%animals.size()), directions[i]);
//            System.out.println(this);
        }
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

    private void moveAnimal(Animal thisAnimal, MoveDirection direction)
    {
        if(direction == MoveDirection.LEFT || direction == MoveDirection.RIGHT)
            thisAnimal.move(direction);
        else
        {
            if(!this.isOccupied(thisAnimal.getPredictedPosition(direction)))
            {
                this.usedMapCoords.remove(thisAnimal.getPosition().toString());
                thisAnimal.move(direction);
                if(!this.place(thisAnimal))
                    System.out.println("Something happened, what shouldn't happened");
            }
        }
    }
}
