package engine.data.linear;

public class Vec3Float extends Vec2Float{
    public Vec3Float() {
        super(3);
    }
    protected Vec3Float(int size)
    {
        super(size);
    }
    public Vec3Float(float x, float y, float z)
    {
        super(3);
        setData(new Float[] {x, y, z});
    }
    public Vec3Float(Vec3Float val)
    {
        setData(val.getData().clone());
    }

    public float getZ()
    {
        return get(2);
    }
    public Vec2Float getXZ()
    {
        return new Vec2Float(getX(), getZ());
    }
    public Vec2Float getYZ()
    {
        return new Vec2Float(getY(), getZ());
    }
    public Vec3Float getXYZ()
    {
        return new Vec3Float(this);
    }

    public void setZ(float val)
    {
        set(2, val);
    }
    public void setXZ(Vec2Float val)
    {
        set(0, val.getX());
        set(2, val.getY());
    }
    public void setXZ(float x, float z)
    {
        setXZ(new Vec2Float(x, z));
    }
    public void setYZ(Vec2Float val)
    {
        set(1, val.getX());
        set(2, val.getY());
    }
    public void setYZ(float y, float z)
    {
        setYZ(new Vec2Float(y, z));
    }
    public void setXYZ(Vec3Float val)
    {
        this.setData(val.getData().clone());
    }
    public void setXYZ(float x, float y, float z)
    {
        setXYZ(new Vec3Float(x, y, z));
    }
}
