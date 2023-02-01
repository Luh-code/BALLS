import engine.data.Ecs;
import engine.data.Ecs.*;

import static utils.Logger.*;

public class Test {
	int val = 69;
	public static void main(String[] args) {
		logInfo("-------< BALLS >-------");
		Ecs ecs = new Ecs();

		ecs.registerComponent(Test.class);

		Entity e1 = ecs.createEntity();
		Entity e2 = ecs.createEntity();

		ecs.addComponent(e1, new Test());

		ecs.removeComponent(e1, Test.class);

    logInfo("-----------------------");
	}
}
