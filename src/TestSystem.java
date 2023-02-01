import engine.data.Entity;
import engine.data.System;

import static utils.Logger.logDebug;

public class TestSystem extends System {
	public TestSystem() {
		super();
	}

	@Override
	public void entityRegistered(Entity e) {
		logDebug("Entity registered in TestSystem: " + e.getId());
	}

	@Override
	public void entityErased(Entity e)
	{
		logDebug("Entity erased from TestSystem: " + e.getId());
	}
}
