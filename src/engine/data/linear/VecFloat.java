package engine.data.linear;

public class VecFloat extends Vector<Float>{
    public VecFloat(int size) {
        super(size, Float.class);
    }
    protected VecFloat()
    {

    }

    public void add(VecFloat val)
    {
        for(int i = 0; i < Math.min(val.getData().length, getData().length); i++)
        {
            set(i, get(i)+val.get(i));
        }
    }
    public void sub(VecFloat val)
    {
        for(int i = 0; i < Math.min(val.getData().length, getData().length); i++)
        {
            set(i, get(i)-val.get(i));
        }
    }
    public void mul(VecFloat val)
    {
        for(int i = 0; i < Math.min(val.getData().length, getData().length); i++)
        {
            set(i, get(i)*val.get(i));
        }
    }
    public void div(VecFloat val)
    {
        for(int i = 0; i < Math.min(val.getData().length, getData().length); i++)
        {
            set(i, get(i)/val.get(i));
        }
    }

    @Override
    public int compareTo(Vector<Float> o) {
        float res;
        if (data.length - o.getData().length != 0) return data.length - o.getData().length;
        for(int i = 0; i < data.length; i++)
        {
            res = data[i]-o.getData()[i];
            if (res < 0.0f) return (int)Math.floor(res);
            else if (res > 0.0f) return (int)Math.ceil(res);
        }
        return 0;
    }
}
