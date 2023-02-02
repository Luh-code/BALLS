import engine.data.components.Mesh;
import engine.data.ecs.Ecs;
import engine.data.ecs.Entity;
import engine.data.ecs.Signature;
import engine.data.linear.Vec3Float;
import engine.data.linear.Vec3Int;
import engine.data.linear.VecFloat;
import engine.data.object.IMeshData;
import engine.data.object.MeshData;
import engine.data.object.Vertex;
import engine.opengl.Application;
import engine.opengl.Renderer;

import static utils.Logger.*;

public class Test {

	public static void main(String[] args) {
		logInfo("-------< BALLS >-------");

		//--- General Setup ---

		Application a = new Application();
		Ecs ecs = a.getEcs();

		//--- Register Resources ---

		ecs.registerResourceType(MeshData.class);

		//--- Register Components ---

		ecs.registerComponent(Mesh.class);

		//--- Register Systems ---

		Renderer renderer = Renderer.registerSystem(ecs);
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

		//--- Create Entities ---

		Entity e1 = ecs.createEntity();
		Entity e2 = ecs.createEntity();

		//--- Create Components ---

		ecs.addComponent(e1, new Mesh(ecs.getResource("Triangle", MeshData.class)));

		//--- Run App ---

		a.run();

		//--- Remove Components ---

		ecs.removeComponent(e1, Mesh.class);

		//--- Remove Resources ---
		ecs.deleteAllResources();

		logInfo("-----------------------");
	}
}
