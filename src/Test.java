import engine.data.Ecs;
import engine.data.Entity;
import engine.data.Signature;

import static utils.Logger.*;

public class Test {

	int val = 69;

	public static void main(String[] args) {
		logInfo("-------< BALLS >-------");
		Ecs ecs = new Ecs();

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
