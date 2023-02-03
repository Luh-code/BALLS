package engine.opengl;

import engine.data.components.Material;
import engine.data.components.Mesh;
import engine.data.ecs.Ecs;
import engine.data.ecs.Entity;
import engine.data.ecs.Signature;
import engine.data.ecs.System;
import engine.data.object.MeshData;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL15C.*;

public class Renderer extends System {

	private Map<Entity, Integer> vbos = new HashMap<>();

	private Ecs ecs;

	public void init(Ecs ecs)
	{
		this.ecs = ecs;
	}

	@Override
	public void entityRegistered(Entity e)
	{
		int vbo = 0;
		glGenBuffers(new int[] {vbo});
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		MeshData md = (MeshData) ecs.getComponent(Mesh.class, e).getMd();
		glBufferData(GL_ARRAY_BUFFER, md.floats(), GL_STATIC_DRAW);
		vbos.put(e, vbo);
	}

	@Override
	public void entityErased(Entity e)
	{
		glDeleteBuffers(vbos.get(e));
	}

	public static Renderer registerSystem(Ecs ecs)
	{
		Renderer r = ecs.registerSystem(Renderer.class);
		Signature sig = new Signature();
		sig.setBit(ecs.getComponentType(Mesh.class), true);
		sig.setBit(ecs.getComponentType(Material.class), true);
		ecs.setSystemSignature(sig, Renderer.class);
		return r;
	}
}
