package engine.data.components;

import engine.data.object.Vertex;

public interface IMesh {
    public Vertex[] getVertices();
    public void setVertices(Vertex[] val, boolean cpy);
    public int[] getIndices();
    public void setIndices(int[] val, boolean cpy);

    public Vertex getVertex(int idx);
    public int getIndex(int idx);
}
