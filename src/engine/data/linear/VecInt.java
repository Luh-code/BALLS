package engine.data.linear;

public class VecInt extends Vector<Integer>{
    public VecInt(int size) {
        super(size, Integer.class);
    }
    protected VecInt()
    {

    }

    public void add(VecInt val)
    {
        for(int i = 0; i < Math.min(val.getData().length, getData().length); i++)
        {
            set(i, get(i)+val.get(i));
        }
    }
    public void sub(VecInt val)
    {
        for(int i = 0; i < Math.min(val.getData().length, getData().length); i++)
        {
            set(i, get(i)-val.get(i));
        }
    }
    public void mul(VecInt val)
    {
        for(int i = 0; i < Math.min(val.getData().length, getData().length); i++)
        {
            set(i, get(i)*val.get(i));
        }
    }
    public void div(VecInt val)
    {
        for(int i = 0; i < Math.min(val.getData().length, getData().length); i++)
        {
            set(i, get(i)/val.get(i));
        }
    }

    @Override
    public int compareTo(Vector<Integer> o) {
        int res;
        if (data.length - o.getData().length != 0) return data.length - o.getData().length;
        for(int i = 0; i < data.length; i++)
        {
            res = data[i]-o.getData()[i];
            if (res != 0) return res;
        }
        return 0;
    }
}
