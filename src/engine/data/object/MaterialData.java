package engine.data.object;

import engine.opengl.shader.Shader;

public class MaterialData {
	private Shader vertexShader;
	private Shader fragmentShader;

	public MaterialData(Shader vertexShader, Shader fragmentShader)
	{
		this.vertexShader = vertexShader;
		this.fragmentShader = fragmentShader;
	}

	public Shader getVertexShader() {
		return vertexShader;
	}

	public void setVertexShader(Shader vertexShader) {
		this.vertexShader = vertexShader;
	}

	public Shader getFragmentShader() {
		return fragmentShader;
	}

	public void setFragmentShader(Shader fragmentShader) {
		this.fragmentShader = fragmentShader;
	}
}
