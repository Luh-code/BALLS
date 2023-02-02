package engine.data.object;

import static utils.Logger.logError;

public class MeshData implements IMeshData {
	Vertex[] vertices;
	int[] indices;

	@Override
	public Vertex[] getVertices() {
		return vertices;
	}

	@Override
	public void setVertices(Vertex[] val, boolean cpy) {
		vertices = (cpy ? val.clone() : val);
	}

	@Override
	public int[] getIndices() {
		return indices;
	}

	@Override
	public void setIndices(int[] val, boolean cpy) {
		indices = (cpy ? val.clone() : val);
	}

	@Override
	public Vertex getVertex(int idx) {
		if (idx >= vertices.length) {
			logError("Vertex trying to be accessed is out of range");
			assert (false);
		}
		return vertices[idx];
	}

	@Override
	public int getIndex(int idx) {
		if (idx >= indices.length) {
			logError("Index trying to be accessed is out of range");
			assert (false);
		}
		return indices[idx];
	}

	@Override
	public float[] floats() {
		float[] temp = new float[vertices.length*3];
		for (int i = 0; i < vertices.length; i++)
		{
			Vertex v = vertices[i];
			int ci = i*3;
			temp[ci] = v.position.getX();
			temp[ci+1] = v.position.getY();
			temp[ci+2] = v.position.getZ();
		}
		return temp;
	}
}
