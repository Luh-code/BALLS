package engine.data.linear;

public class Vec3Int extends Vec2Int{
    public Vec3Int() {
        super(3);
    }
    protected Vec3Int(int size)
    {
        super(size);
    }
    public Vec3Int(int x, int y, int z)
    {
        super(3);
        setData(new Integer[] {x, y, z});
    }
    public Vec3Int(Vec3Int val)
    {
        setData(val.getData().clone());
    }

    public int getZ()
    {
        return get(2);
    }
    public Vec2Int getXZ()
    {
        return new Vec2Int(getX(), getZ());
    }
    public Vec2Int getYZ()
    {
        return new Vec2Int(getY(), getZ());
    }
    public Vec3Int getXYZ()
    {
        return new Vec3Int(this);
    }

    public void setZ(int val)
    {
        set(2, val);
    }
    public void setXZ(Vec2Int val)
    {
        set(0, val.getX());
        set(2, val.getY());
    }
    public void setXZ(int x, int z)
    {
        setXZ(new Vec2Int(x, z));
    }
    public void setYZ(Vec2Int val)
    {
        set(1, val.getX());
        set(2, val.getY());
    }
    public void setYZ(int y, int z)
    {
        setYZ(new Vec2Int(y, z));
    }
    public void setXYZ(Vec3Int val)
    {
        this.setData(val.getData().clone());
    }
    public void setXYZ(int x, int y, int z)
    {
        setXYZ(new Vec3Int(x, y, z));
    }

    @Override
    public String toString() {
        return String.format("%s\b| z:%d ]", super.toString(), getZ());
    }
}
