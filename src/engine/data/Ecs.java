package engine.data;

import java.lang.reflect.Constructor;
//import java.sql.Array;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.lang.reflect.Array;

public class Ecs
{
    public static final int MAX_ENTITES = 1000;
    public static final int MAX_COMPONENTS = 1000;

    public class Entity
    {
        private int id;

        public int getId()
        {
            return id;
        }
    }

    public interface IComponentArray {}
    public class ComponentArray<T> implements IComponentArray
    {
        private T[] array;

        public ComponentArray(Class<? extends T> impl)
        {
            array = (T[]) Array.newInstance(impl, MAX_COMPONENTS);
        }
    }

    public class ComponentManager
    {
        private Map<String, IComponentArray> arrays = new HashMap<>();

        public <T> void registerComponent()
        {
            arrays.put(T.class.getSimpleName(), new ComponentArray<T>(T));
        }
    }

    public class EntityManager
    {
        private Set<Entity> unusedEntites = new HashSet<>(MAX_ENTITES);

        public EntityManager()
        {

        }
    }
}
