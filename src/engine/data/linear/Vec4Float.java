package engine.data.linear;

public class Vec4Float extends Vec3Float{
    public Vec4Float()
    {
        super(4);
    }
    protected Vec4Float(int size)
    {
        super(size);
    }
    public Vec4Float(Vec4Float val)
    {
        setData(val.getData().clone());
    }
    public Vec4Float(float x, float y, float z, float w)
    {
        setData(new Float[] {x, y, z, w});
    }

    public float getW()
    {
        return get(3);
    }
    public Vec2Float getXW()
    {
        return new Vec2Float(get(0), get(3));
    }
    public Vec2Float getYW()
    {
        return new Vec2Float(get(1), get(3));
    }
    public Vec2Float getZW()
    {
        return new Vec2Float(get(2), get(3));
    }
    public Vec3Float getXZW()
    {
        return new Vec3Float(get(0), get(2), get(3));
    }
    public Vec3Float getYZW()
    {
        return new Vec3Float(get(1), get(2), get(3));
    }
    public Vec4Float getXYZW()
    {
        return this;
    }

    public void setXW(Vec2Float val)
    {
        set(0, val.getX());
        set(3, val.getY());
    }
    public void setXW(int x, int w)
    {
        setXW(new Vec2Float(x, w));
    }
    public void setYW(Vec2Float val)
    {
        set(1, val.getX());
        set(3, val.getY());
    }
    public void setYW(int y, int w)
    {
        setYW(new Vec2Float(y, w));
    }
    public void setZW(Vec2Float val)
    {
        set(2, val.getX());
        set(3, val.getY());
    }
    public void setZW(int z, int w)
    {
        setZW(new Vec2Float(z, w));
    }
    public void setXYW(Vec3Float val)
    {
        set(0, val.getX());
        set(1, val.getY());
        set(3, val.getZ());
    }
    public void setXYW(int x, int y, int w)
    {
        setXYW(new Vec3Float(x, y, w));
    }
    public void setXZW(Vec3Float val)
    {
        set(0, val.getX());
        set(2, val.getY());
        set(3, val.getZ());
    }
    public void setXZW(int x, int z, int w)
    {
        setXZW(new Vec3Float(x, z, w));
    }
    public void setYZW(Vec3Float val)
    {
        set(1, val.getX());
        set(2, val.getY());
        set(3, val.getZ());
    }
    public void setYZW(int y, int z, int w)
    {
        setYZW(new Vec3Float(y, z, w));
    }
    public void setXYZW(Vec4Float val)
    {
        setData(val.getData().clone());
    }
    public void setXYZW(int x, int y, int z, int w)
    {
        setXYZW(new Vec4Float(x, y, z, w));
    }

    @Override
    public String toString() {
        return String.format("%s\b| w:%.3f ]", super.toString(), getW());
    }
}
