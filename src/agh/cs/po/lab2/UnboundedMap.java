package agh.cs.po.lab2;

import java.util.List;

public class UnboundedMap extends AbstractWorldMap implements IWorldMap {

    public UnboundedMap(List<Stone> stoneList)
    {
        super(new Vector2d(0,0), new Vector2d(0,0));

        for(Stone stone: stoneList)
        {
            this.usedMapCoords.put(stone.getPosition().toString(), stone);
            updateCorners(stone.getPosition());
        }
    }

    @Override
    public boolean placeAnimal(Animal animal) {
        if(super.placeAnimal(animal)) {
            updateCorners(animal.getPosition());
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return super.objectAt(position);
    }
    private void updateCorners(Vector2d point)
    {
        this.upperRight = this.upperRight.upperRight(point);
        this.lowerLeft = this.lowerLeft.lowerLeft(point);
    }

    @Override
    protected void moveAnimal(Animal thisAnimal, MoveDirection direction)
    {
        Vector2d previousPosition = thisAnimal.getPosition();
        if(thisAnimal.move(direction)) {
            this.usedMapCoords.remove(previousPosition.toString());
            this.usedMapCoords.put(thisAnimal.getPosition().toString(), thisAnimal);
            updateCorners(thisAnimal.getPosition());
        }
    }
}
