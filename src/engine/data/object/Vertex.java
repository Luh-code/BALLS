package engine.data.object;

import engine.data.linear.Vec3Float;

public class Vertex {
	public Vec3Float position;

	public Vertex()
	{
		position = new Vec3Float(0, 0, 0);
	}
	public Vertex(float x, float y, float z)
	{
		position = new Vec3Float(x, y, z);
	}
}
