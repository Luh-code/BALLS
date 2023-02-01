package engine.data;

//import java.sql.Array;
import java.util.*;
  import java.lang.reflect.Array;

import static utils.Logger.*;


public class Ecs {
	public static final int MAX_ENTITES = 1000;
	public static final int MAX_COMPONENTS = 1000;

	public class Entity implements Comparable<Entity>
	{
		//----- Members -----

		private final int id;

		//----- Methods -----

		public int getId() {
			return id;
		}

		public Entity(int e) {
			this.id = e;
		}

    @Override
    public int compareTo(Entity o) {
      return this.id-o.getId();
    }
  }

	public class Signature implements Cloneable
	{
		//----- Members -----

		private BitSet bits = new BitSet();

		//----- Methods -----

		public Signature()
		{

		}

		public Signature(Signature s)
		{
			this.bits = s.getBitSet();
		}

		public BitSet getBitSet()
		{
			return bits;
		}

		public void setBitSet(BitSet b)
		{
			bits = (BitSet) b.clone();
		}

		public void setBit(int b, boolean v)
		{
			bits.set(b, v);
		}

		public void flipBit(int b)
		{
			bits.set(b, !bits.get(b));
		}

		public void clear()
		{
			bits.clear();
		}

		@Override
		public Object clone()
		{
			return new Signature(this);
		}
	}

	public interface IComponentArray {
		public void entityDestroyed(Entity e);
	}

	public class ComponentArray<T> implements IComponentArray {

		//----- Members -----

		private T[] array;
		private Map<Entity, Integer> entityToIndexMap = new HashMap<>(MAX_ENTITES);
		private Map<Integer, Entity> indexToEntityMap = new HashMap<>(MAX_ENTITES);
		int size = 0;

		//----- Methods -----

		public ComponentArray(Class<? extends T> impl) {
			array = (T[]) Array.newInstance(impl, MAX_COMPONENTS);
		}

		public void insertData(Entity e, Object component)
		{
			if (entityToIndexMap.containsKey(e))
			{
				logError("Tried adding same component to entity multiple times");
				return;
			}

			int newIndex = size;
			entityToIndexMap.put(e, newIndex);
			indexToEntityMap.put(newIndex, e);
			array[newIndex] = (T) component;
			++size;
		}

		public void removeData(Entity e)
		{
			if (!entityToIndexMap.containsKey(e))
			{
				logError("Tried removing component from non-existent entity");
				return;
			}

			int removedIdx = entityToIndexMap.get(e);
			int lastIdx = size-1;
			array[removedIdx] = array[lastIdx];
			array[removedIdx] = null;

			Entity removed = indexToEntityMap.get(lastIdx);
			entityToIndexMap.put(removed, removedIdx);
			indexToEntityMap.put(removedIdx, removed);

			entityToIndexMap.remove(e);
			indexToEntityMap.remove(lastIdx);

			--size;
		}

		public T getData(Entity e)
		{
			if (!entityToIndexMap.containsKey(e))
			{
				logError("Tried getting data from unregistered entity");
				assert(false);
			}

			return array[entityToIndexMap.get(e.getId())];
		}

		@Override
		public void entityDestroyed(Entity e)
		{
			if (entityToIndexMap.containsKey(e))
			{
				removeData(e);
			}
		}
	}

	public class ComponentManager {

		//----- Members -----

		private Map<String, Integer> componentTypes = new HashMap<>(MAX_ENTITES);
		private Map<String, IComponentArray> componentArrays = new HashMap<>(MAX_ENTITES);

		private int nextComponentType = 0;

		//----- Methods -----

		private <T> ComponentArray<T> getComponentArray(Class<? extends T> c)
		{
			String typeName = c.getSimpleName();

			if (!componentTypes.containsKey(typeName))
			{
				logError("Tried to retrieve unregistered component array");
				assert(false);
			}

			return (ComponentArray<T>) componentArrays.get(typeName);
		}

		public <T> void registerComponent(Class<? extends T> c)
		{
			String typeName = c.getSimpleName();

			if (componentTypes.containsKey(typeName))
			{
				logError("Tried to register component type multiple times");
				return;
			}

			componentTypes.put(typeName, nextComponentType);
			componentArrays.put(c.getSimpleName(), new ComponentArray<T>(c));

			nextComponentType++;
		}

		public <T> int getComponentType(Class<? extends T> c)
		{
			String typeName = c.getSimpleName();

			if (!componentTypes.containsKey(typeName))
			{
				logError("Tried to retrieve type of unregistered component type");
				assert(false);
			}

			return componentTypes.get(typeName);
		}

		public <T> void addComponent(Entity e, T c)
		{
			getComponentArray(c.getClass()).insertData(e, c);
		}

		public <T> void removeComponent(Class<? extends T> c, Entity e)
		{
			getComponentArray(c).removeData(e);
		}

		public <T> T getComponent(Class<? extends T> c, Entity e)
		{
			return getComponentArray(c).getData(e);
		}

		public void entityDestroyed(Entity e)
		{
			for (int i = 0; i < componentArrays.size(); i++) {
				componentArrays.get(i).entityDestroyed(e);
			}
		}
	}

	public class EntityManager {
		//---- Members -----

		private Queue<Entity> availableEntities = new LinkedList<>();
		private Signature[] signatures = new Signature[MAX_ENTITES];
		private int livingEntityCount = 0;

		//----- Methods -----

		public EntityManager() {
			for (int i = 0; i < MAX_ENTITES; i++) {
        availableEntities.offer(new Entity(i));
				signatures[i] = new Signature();
			}
		}

		public Entity createEntity() {
			if (livingEntityCount >= MAX_ENTITES) {
        logError("Tried to create new Entity, when entity limit is reached!");
				assert (false);
			}
			Entity e = availableEntities.poll();
      ++livingEntityCount;
      return e;
		}

    public void destroyEntity(Entity e)
    {
      if (e.getId() >= MAX_ENTITES)
      {
        logError("Tried to delete out-of-range entity: " + e.getId());
        return;
      }

			signatures[e.getId()].clear();

			availableEntities.offer(e);
			--livingEntityCount;
    }

		public void setSignature(Entity e, Signature b)
		{
			if (e.getId() >= MAX_ENTITES)
			{
				logError("Tried to delete out-of-range entity: " + e.getId());
				return;
			}

			signatures[e.getId()] = (Signature) b.clone();
		}

		public Signature getSignature(Entity e)
		{
			if (e.getId() >= MAX_ENTITES)
			{
				logError("Tried to delete out-of-range entity: " + e.getId());
				assert(false);
			}

			return signatures[e.getId()];
		}
	}

	//----- Members -----

	private ComponentManager componentManager;
	private EntityManager entityManager;

	//----- Methods -----

	public Ecs() {
		componentManager = new ComponentManager();
		entityManager = new EntityManager();
		logInfo("ECS initialized");
	}

	//--- Entity Manager ---

	public Entity createEntity()
	{
		return entityManager.createEntity();
	}
	public void destroyEntity(Entity e)
	{
		entityManager.destroyEntity(e);
	}
	public void setSignature(Entity e, Signature s)
	{
		entityManager.setSignature(e, s);
	}
	public Signature getSignature(Entity e)
	{
		return entityManager.getSignature(e);
	}

	//--- Component Manager ---
	public <T> void registerComponent(Class<? extends T> c)
	{
		componentManager.registerComponent(c);
	}

	public <T> int getComponentType(Class<? extends T> c)
	{
		return componentManager.getComponentType(c);
	}

	public <T> void addComponent(Entity e, T c)
	{
		componentManager.addComponent(e, c);

		Signature sig = entityManager.getSignature(e);
		sig.setBit(componentManager.getComponentType(c.getClass()), true);
		entityManager.setSignature(e, sig);

		//systemManager.entitySignatureChanged(e, sig);
	}

	public <T> void removeComponent(Entity e, Class<? extends T> c)
	{
		componentManager.removeComponent(c, e);

		Signature sig = entityManager.getSignature(e);
		sig.setBit(componentManager.getComponentType(c), false);
		entityManager.setSignature(e, sig);

		//systemManager.entitySignatureChanged(e, sig);
	}

	public <T> T getComponent(Class<? extends T> c, Entity e)
	{
		return componentManager.getComponent(c, e);
	}

	public void entityDestroyed(Entity e)
	{
		componentManager.entityDestroyed(e);
	}

}
