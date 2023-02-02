package engine.data.components;

import engine.data.object.Vertex;

import static utils.Logger.logError;

public class Mesh implements IMesh {
    private Vertex[] vertices;
    private int[] indices;

    @Override
    public Vertex[] getVertices() {
        return vertices;
    }

    @Override
    public void setVertices(Vertex[] val, boolean cpy) {
        this.vertices = ( cpy ? val.clone() : val);
    }

    @Override
    public int[] getIndices() {
        return indices;
    }

    @Override
    public void setIndices(int[] val, boolean cpy) {
        this.indices = (cpy ? val.clone() : val);
    }

    @Override
    public Vertex getVertex(int idx) {
        if (idx >= vertices.length)
        {
            logError("Vertex trying to be accessed is out of range");
            assert(false);
        }
        return vertices[idx];
    }

    @Override
    public int getIndex(int idx) {
        if (idx >= indices.length)
        {
            logError("Index trying to be accessed is out of range");
            assert(false);
        }
        return indices[idx];
    }
}
