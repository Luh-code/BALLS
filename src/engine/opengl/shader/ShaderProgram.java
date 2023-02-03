package engine.opengl.shader;

public class ShaderProgram {
	private int handle;

	public ShaderProgram(int handle)
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
