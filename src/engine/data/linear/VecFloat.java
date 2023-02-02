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
    public void mul(float val)
    {
        for(int i = 0; i < getData().length; i++)
        {
            set(i, get(i)*val);
        }
    }
    public void div(VecFloat val)
    {
        for(int i = 0; i < Math.min(val.getData().length, getData().length); i++)
        {
            set(i, get(i)/val.get(i));
        }
    }
    public void div(float val)
    {
        for(int i = 0; i < getData().length; i++)
        {
            set(i, get(i)/val);
        }
    }

    public <T extends VecFloat> VecFloat cross(T val)
    {
        int mSize = Math.min(val.getData().length, getData().length);
        T temp = null;
        try {
            temp = (T) val.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        temp.setData(getData().clone());
        for(int i = 0; i < mSize; i++)
        {
            int l = (i+1)%mSize;
            int r = (i+2)%mSize;
            temp.set(i,
                    (get(l)*val.get(r))-(get(r)*val.get(l))
            );
        }
        return temp;
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
