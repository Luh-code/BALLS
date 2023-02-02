package engine.data.linear;

public class Vec2Float extends VecFloat{
    public Vec2Float() {
        super(2);
    }
    protected Vec2Float(int size)
    {
        super(size);
    }
    public Vec2Float(float x, float y)
    {
        super(2);
        setData(new Float[] {x, y});
    }
    public Vec2Float(Vec2Float val)
    {
        setData(val.getData().clone());
    }

    public float getX()
    {
        return getData()[0];
    }
    public float getY()
    {
        return getData()[1];
    }
    public Vec2Float getXY()
    {
        return this;
    }

    public void setX(float val)
    {
        set(0, val);
    }
    public void setY(float val)
    {
        set(1, val);
    }
    public void setXY(Vec2Float val)
    {
        set(0, val.getX());
        set(1, val.getY());
    }
    public void setXY(float x, float y)
    {
        setXY(new Vec2Float(x, y));
    }
}
