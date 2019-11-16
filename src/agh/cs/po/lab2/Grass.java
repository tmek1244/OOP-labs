package agh.cs.po.lab2;

public class Grass implements IMapElement{
    private Vector2d position;

    public Grass(Vector2d position)
    {
        this.position = position;
    }

    public Vector2d getPosition()
    {
        return this.position;
    }

    public String toString()
    {
        return "G";
    }
}
