package agh.cs.po.lab2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public class RectangularMap extends AbstractWorldMap implements IWorldMap{
    public RectangularMap(int width, int height)
    {
        super(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }

    @Override
    public boolean placeAnimal(Animal animal)
    {
        if(super.placeAnimal(animal)) {
            animal.addObserver(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(this.upperRight) && position.follows(this.lowerLeft) && super.canMoveTo(position);
    }
}
