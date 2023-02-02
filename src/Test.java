import engine.data.ecs.Ecs;
import engine.data.ecs.Entity;
import engine.data.ecs.Signature;
import engine.data.linear.Vec3Int;
import engine.opengl.Application;

import static utils.Logger.*;

public class Test {

	int val = 69;

	public static void main(String[] args) {
		logInfo("-------< BALLS >-------");
		Ecs ecs = new Ecs();

		//Application a = new Application();
		//a.run();

		Vec3Int tv = new Vec3Int(1, 2, 3);
		tv.add(new Vec3Int(4, 0, 0));
		logDebug(String.format("Wert von TestVector: %d, %d, %d", tv.getX(), tv.getY(), tv.getZ()));

		ecs.registerResourceType(Integer.class);
		ecs.setResource("TestRes", 27);
		ecs.setResource("TestRes2", 187);

		ecs.deleteResource("TestRes", Integer.class);


		ecs.registerComponent(Test.class);

		TestSystem testSystem = ecs.registerSystem(TestSystem.class);
		Signature sig = new Signature();
		sig.setBit(ecs.getComponentType(Test.class), true);
		ecs.setSystemSignature(sig, TestSystem.class);

		Entity e1 = ecs.createEntity();
		Entity e2 = ecs.createEntity();

		ecs.addComponent(e1, new Test());

		ecs.removeComponent(e1, Test.class);

    logInfo("-----------------------");
	}
}
