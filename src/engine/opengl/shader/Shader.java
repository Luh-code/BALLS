package engine.opengl.shader;

import static org.lwjgl.opengl.GL20C.glDeleteShader;

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

	@Override
	protected void finalize()
	{
		glDeleteShader(handle);
	}
}
