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
import static org.lwjgl.opengl.GL20C.*;
import static org.lwjgl.opengl.GL30C.*;
import static utils.Logger.logDebug;

public class Renderer extends System {

	private Map<Entity, Integer> vbos = new HashMap<>();
	private Map<Entity, Integer> vaos = new HashMap<>();

	private Ecs ecs;

	public void init(Ecs ecs)
	{
		this.ecs = ecs;
	}

	@Override
	public void entityRegistered(Entity e)
	{
		logDebug("Entity '" + e + "' added to renderer");

		Mesh mesh = ecs.getComponent(Mesh.class, e);
		Material mat = ecs.getComponent(Material.class, e);

		int vbo = 0;
		glGenBuffers(new int[] {vbo});
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		MeshData md = (MeshData) ecs.getComponent(Mesh.class, e).getMd();
		glBufferData(GL_ARRAY_BUFFER, md.floats(), GL_STATIC_DRAW);
		vbos.put(e, vbo);

		int vao = 0;
		glGenVertexArrays(new int[] {vao});
		glBindVertexArray(vao);
		// copy verts to buffer
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, mesh.getMd().floats(), GL_STATIC_DRAW);
		// set vert attributes
		int vertexAmt = 3;
		//vertexAmt = mesh.getMd().getIndices().length;
		glVertexAttribPointer(0, vertexAmt, GL_FLOAT, false, 3*4, 0);
		glEnableVertexAttribArray(0);

		vaos.put(e, vao);
	}

	@Override
	public void entityErased(Entity e)
	{
		glDeleteBuffers(vbos.get(e));
	}

	public void render()
	{
		for (Object o : entities)
		{
			Entity e = (Entity) o;
			Mesh mesh = ecs.getComponent(Mesh.class, e);
			Material mat = ecs.getComponent(Material.class, e);

			// set correct program to use
			glUseProgram(mat.getData().getShaderProgram().getHandle());
			glBindVertexArray(vaos.get(e));
			// draw
			int vertexAmt = 3;
			//vertexAmt = mesh.getMd().getIndices().length;
			glDrawArrays(GL_TRIANGLES, 0, vertexAmt);
		}
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
