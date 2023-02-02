package engine.data.components;

import engine.data.object.Vertex;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static utils.Logger.logError;

public class MutableMesh implements IMesh{
    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Integer> indices = new ArrayList<>();

    @Override
    public Vertex[] getVertices() {
        return vertices.toArray(Vertex[]::new);
    }

    @Override
    public void setVertices(Vertex[] val, boolean cpy) {
        vertices = new ArrayList<>(Arrays.asList((cpy ? val.clone() : val)));
    }

    @Override
    public int[] getIndices() {
        return Arrays.stream(indices.toArray(Integer[]::new)).mapToInt(Integer::intValue).toArray();
    }

    @Override
    public void setIndices(int[] val, boolean cpy) {
        indices = new ArrayList<>(Arrays.asList(Arrays.stream((cpy ? val.clone() : val)).boxed().toArray(Integer[]::new)));
    }

    @Override
    public Vertex getVertex(int idx) {
        if (idx >= vertices.size())
        {
            logError("Vertex trying to be accessed is out of range");
            assert(false);
        }
        return vertices.get(idx);
    }

    @Override
    public int getIndex(int idx) {
        if (idx >= indices.size())
        {
            logError("Index trying to be accessed is out of range");
            assert(false);
        }
        return indices.get(idx);
    }
}
