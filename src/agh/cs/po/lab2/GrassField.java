package agh.cs.po.lab2;

import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    protected Hashtable<Vector2d, Grass> grassCoords = new Hashtable<Vector2d, Grass>();
    private MapBoundary mapBoundary = new MapBoundary();

    public GrassField(int howManyGrasses)
    {
        super(new Vector2d(0,0), new Vector2d(0,0));

        while(howManyGrasses > 0)
        {
            int x = (int)(Math.random()*Math.sqrt(howManyGrasses*10));
            int y = (int)(Math.random()*(Math.sqrt(howManyGrasses*10)));
            Vector2d grassPosition = new Vector2d(x,y);
            if(!this.grassCoords.containsKey(grassPosition)) {
                Grass grass = new Grass(grassPosition);
                this.grassCoords.put(grassPosition, grass);
                this.mapBoundary.addPosition(grassPosition);
//                updateCorners(grass.getPosition());
                howManyGrasses--;
            }
        }
    }

    @Override
    public boolean placeAnimal(Animal animal) throws IllegalArgumentException{
        if(super.placeAnimal(animal)) {
            animal.addObserver(this);
            animal.addObserver(this.mapBoundary);
//            updateCorners(animal.getPosition());
            return true;
        }
        throw new IllegalArgumentException("Position " +animal.getPosition()+ " is occupied!");
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !super.isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || this.grassCoords.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object objectAtThisPosition = super.objectAt(position);
        if(objectAtThisPosition instanceof Animal)
            return objectAtThisPosition;
        return this.grassCoords.getOrDefault(position, null);
    }

//    private void updateCorners(Vector2d point)
//    {
//        this.upperRight = this.upperRight.upperRight(point);
//        this.lowerLeft = this.lowerLeft.lowerLeft(point);
//    }
//
//    @Override
//    protected void moveAnimal(Animal thisAnimal, MoveDirection direction)
//    {
//        super.moveAnimal(thisAnimal, direction);
//        updateCorners(thisAnimal.getPosition());
//    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        super.positionChanged(oldPosition, newPosition);
//        mapBoundary.positionChanged(oldPosition, newPosition);
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(this.mapBoundary.getLowerLeft(), this.mapBoundary.getUpperRight());
    }
}
