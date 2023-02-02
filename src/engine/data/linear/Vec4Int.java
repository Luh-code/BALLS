package engine.data.linear;

public class Vec4Int extends Vec3Int{
    public Vec4Int()
    {
        super(4);
    }
    protected Vec4Int(int size)
    {
        super(size);
    }
    public Vec4Int(Vec4Int val)
    {
        setData(val.getData().clone());
    }
    public Vec4Int(int x, int y, int z, int w)
    {
        setData(new Integer[] {x, y, z, w});
    }

    public int getW()
    {
        return get(3);
    }
    public Vec2Int getXW()
    {
        return new Vec2Int(get(0), get(3));
    }
    public Vec2Int getYW()
    {
        return new Vec2Int(get(1), get(3));
    }
    public Vec2Int getZW()
    {
        return new Vec2Int(get(2), get(3));
    }
    public Vec3Int getXZW()
    {
        return new Vec3Int(get(0), get(2), get(3));
    }
    public Vec3Int getYZW()
    {
        return new Vec3Int(get(1), get(2), get(3));
    }
    public Vec4Int getXYZW()
    {
        return this;
    }

    public void setXW(Vec2Int val)
    {
        set(0, val.getX());
        set(3, val.getY());
    }
    public void setXW(int x, int w)
    {
        setXW(new Vec2Int(x, w));
    }
    public void setYW(Vec2Int val)
    {
        set(1, val.getX());
        set(3, val.getY());
    }
    public void setYW(int y, int w)
    {
        setYW(new Vec2Int(y, w));
    }
    public void setZW(Vec2Int val)
    {
        set(2, val.getX());
        set(3, val.getY());
    }
    public void setZW(int z, int w)
    {
        setZW(new Vec2Int(z, w));
    }
    public void setXYW(Vec3Int val)
    {
        set(0, val.getX());
        set(1, val.getY());
        set(3, val.getZ());
    }
    public void setXYW(int x, int y, int w)
    {
        setXYW(new Vec3Int(x, y, w));
    }
    public void setXZW(Vec3Int val)
    {
        set(0, val.getX());
        set(2, val.getY());
        set(3, val.getZ());
    }
    public void setXZW(int x, int z, int w)
    {
        setXZW(new Vec3Int(x, z, w));
    }
    public void setYZW(Vec3Int val)
    {
        set(1, val.getX());
        set(2, val.getY());
        set(3, val.getZ());
    }
    public void setYZW(int y, int z, int w)
    {
        setYZW(new Vec3Int(y, z, w));
    }
    public void setXYZW(Vec4Int val)
    {
        setData(val.getData().clone());
    }
    public void setXYZW(int x, int y, int z, int w)
    {
        setXYZW(new Vec4Int(x, y, z, w));
    }

    @Override
    public String toString() {
        return String.format("%s\b| w:%d]", super.toString(), getW());
    }
}
