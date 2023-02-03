import engine.data.components.Material;
import engine.data.components.Mesh;
import engine.data.ecs.Ecs;
import engine.data.ecs.Entity;
import engine.data.object.MaterialData;
import engine.data.object.MeshData;
import engine.data.object.Vertex;
import engine.opengl.Application;
import engine.opengl.Renderer;
import engine.opengl.shader.Shader;
import engine.opengl.shader.ShaderCompiler;
import engine.opengl.shader.ShaderProgram;

import static utils.Logger.*;

public class Test {

	public static void main(String[] args) {
		logInfo("-------< BALLS >-------");

		//--- General Setup ---

		Application a = new Application();
		Ecs ecs = a.getEcs();

		//--- Register Resources ---

		ecs.registerResourceType(MeshData.class);
		ecs.registerResourceType(Shader.class);
		ecs.registerResourceType(MaterialData.class);
		ecs.registerResourceType(ShaderProgram.class);

		//--- Register Components ---

		ecs.registerComponent(Mesh.class);
		ecs.registerComponent(Material.class);

		//--- Register Systems ---

		a.setRenderer(Renderer.registerSystem(ecs));
		Renderer renderer = a.getRenderer();
		renderer.init(ecs);

		//--- Create Resources ---

		MeshData tri = new MeshData();
		tri.setVertices(new Vertex[] {
			new Vertex(-0.5f, -0.5f, 0.0f),
			new Vertex(0.5f, -0.5f, 0.0f),
			new Vertex(0.5f, 0.5f, 0.0f),
		}, false);
		tri.setIndices(new int[] {0, 1, 2}, false);
		ecs.setResource("Triangle",  tri);

		ecs.setResource("BasicVertexShader", ShaderCompiler.compileVertexShader(ShaderCompiler.readShader("src/shaders/basic.vert")));
		ecs.setResource("BasicFragmentShader", ShaderCompiler.compileFragmentShader(ShaderCompiler.readShader("src/shaders/basic.frag")));

		ecs.setResource("BasicShaderProgram", ShaderCompiler.createShaderProgram(
			ecs.getResource("BasicVertexShader", Shader.class),
			ecs.getResource("BasicFragmentShader", Shader.class)
		));

		ecs.setResource("BasicMaterial", new MaterialData(
			ecs.getResource("BasicVertexShader", Shader.class),
			ecs.getResource("BasicFragmentShader", Shader.class),
			ecs.getResource("BasicShaderProgram", ShaderProgram.class)
		));

		//--- Create Entities ---

		Entity e1 = ecs.createEntity();
		Entity e2 = ecs.createEntity();

		//--- Create Components ---

		ecs.addComponent(e1, new Mesh(ecs.getResource("Triangle", MeshData.class)));
		ecs.addComponent(e1, new Material(ecs.getResource("BasicMaterial", MaterialData.class)));

		//--- Run App ---

		a.run();

		//--- Remove Components ---

		ecs.removeComponent(e1, Mesh.class);
		ecs.removeComponent(e1, Material.class);

		//--- Remove Entities ---

		ecs.destroyEntity(e1);
		ecs.destroyEntity(e2);

		//--- Remove Resources ---
		ecs.deleteAllResources();

		logInfo("-----------------------");
	}
}
