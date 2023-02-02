import engine.data.ecs.Ecs;
import engine.data.ecs.Entity;
import engine.data.ecs.Signature;
import engine.data.linear.Vec3Float;
import engine.data.linear.Vec3Int;
import engine.data.linear.VecFloat;
import engine.opengl.Application;

import static utils.Logger.*;

public class Test {

	int val = 69;

	public static void main(String[] args) {
		logInfo("-------< BALLS >-------");
		Ecs ecs = new Ecs();

		//Application a = new Application();
		//a.run();

		Vec3Float tv = new Vec3Float(1.0f, 2.0f, 3.0f);
		tv.add(new Vec3Float(4.0f, 0.0f, 0.0f));
		logDebug(String.format("Wert von TestVector: %f, %f, %f", tv.getX(), tv.getY(), tv.getZ()));
		Vec3Float other = new Vec3Float(1.0f, 0.0f, 2.0f);
		tv = (Vec3Float) tv.cross(other);
		logDebug(String.format("Cross product von TestVector zu [%f, %f, %f]: %f, %f, %f", other.getX(), other.getY(), other.getZ(), tv.getX(), tv.getY(), tv.getZ()));

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
