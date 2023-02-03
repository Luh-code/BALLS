package engine.opengl.shader;

public class Shader {
	private int handle = 0;

	public Shader(int handle)
	{
		this.handle = handle;
	}

	public int getHandle() {
		return handle;
	}

	public void setHandle(int handle) {
		this.handle = handle;
	}
}
