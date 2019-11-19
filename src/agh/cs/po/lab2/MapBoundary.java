package agh.cs.po.lab2;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    private SortedSet<Vector2d> animalsSortedByX = new TreeSet<>((t1, t2) -> {
        if(t1.x == t2.x) {
            if (t1.y > t2.y)
                return 1;
            else if (t1.y < t2.y)
                return 2;
            return 0;
        }
        if(t1.x < t2.x)
            return -1;
        return 1;
    });

    private SortedSet<Vector2d> animalsSortedByY = new TreeSet<>((t1, t2) -> {
        if(t1.y == t2.y) {
            if (t1.x > t2.x)
                return 1;
            else if (t1.x < t2.x)
                return 2;
            return 0;
        }
        if(t1.y < t2.y)
            return -1;
        return 1;
    });


    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        if(newPosition.follows(animalsSortedByX.first().lowerLeft(animalsSortedByY.first())))
//        {
//
//        }
//        if(newPosition.precedes(animalsSortedByX.last().upperRight(animalsSortedByY.last())))
//        {
//
//        }
        removePosition(oldPosition);
        addPosition(oldPosition);
    }

    private void removePosition(Vector2d position)
    {
        this.animalsSortedByX.remove(position);
        this.animalsSortedByY.remove(position);
    }

    void addPosition(Vector2d position)
    {
        this.animalsSortedByY.add(position);
        this.animalsSortedByX.add(position);
    }

    Vector2d getLowerLeft()
    {
        return new Vector2d(this.animalsSortedByX.first().x, this.animalsSortedByY.first().y);
    }

    Vector2d getUpperRight()
    {
        return new Vector2d(this.animalsSortedByX.last().x, this.animalsSortedByY.last().y);
    }
}
