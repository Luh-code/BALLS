package engine.data.linear;

public class Vec2Int extends VecInt{
    public Vec2Int() {
        super(2);
    }
    protected Vec2Int(int size)
    {
        super(size);
    }
    public Vec2Int(int x, int y)
    {
        super(2);
        setData(new Integer[] {x, y});
    }
    public Vec2Int(Vec2Int val)
    {
        setData(val.getData().clone());
    }

    public int getX()
    {
        return getData()[0];
    }
    public int getY()
    {
        return getData()[1];
    }
    public Vec2Int getXY()
    {
        return this;
    }

    public void setX(int val)
    {
        set(0, val);
    }
    public void setY(int val)
    {
        set(1, val);
    }
    public void setXY(Vec2Int val)
    {
        set(0, val.getX());
        set(1, val.getY());
    }
    public void setXY(int x, int y)
    {
        setXY(new Vec2Int(x, y));
    }

    @Override
    public String toString() {
        return String.format("[ x:%d | y:%d ]", getX(), getY());
    }
}
