package agh.cs.po.lab2;

public class Stone implements IMapElement{
    private Vector2d position;

    public Stone(Vector2d position)
    {
        this.position = position;
    }

    public Vector2d getPosition()
    {
        return this.position;
    }

    public String toString()
    {
        return "s";
    }
}
