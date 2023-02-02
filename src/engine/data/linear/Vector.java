package engine.data.linear;

import java.lang.reflect.Array;
import java.util.Arrays;

import static utils.Logger.*;

public class Vector<T> implements Comparable<Vector<T>>, Cloneable {
	protected T[] data;

	public Vector(int size, Class<? extends T> c) {
		data = (T[]) Array.newInstance(c, size);
	}

	protected Vector() {

	}

	public T get(int idx) {
		if (idx >= data.length) {
			logError("Cannot access data beyond array limits: " + idx + " from " + data.length);
			assert (false);
		}
		return data[idx];
	}

	public void set(int idx, T val) {
		if (idx >= data.length) {
			logError("Cannot access data beyond array limits: " + idx + " from " + data.length);
			assert (false);
		}
		data[idx] = val;
	}

	public T[] getData() {
		return data;
	}

	public void setData(T[] val) {
        /*if (val.length != data.length)
        {
            logError("Unable to set value due to array size difference");
            return;
        }*/
		data = val;
	}

	@Override
	public int compareTo(Vector<T> o) {
        /*T res;
        if (data.length - o.getData().length != 0) return data.length - o.getData().length;
        for(int i = 0; i < data.length; i++)
        {
            res = data[i]-o.getData()[i];
        }*/
		return data.length - o.getData().length; // temporary
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (T e : data) {
			sb.append(e);
			sb.append(" | ");
		}
		sb.append("\b\b]");
		return sb.toString();
	}
}
