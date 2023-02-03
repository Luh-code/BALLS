package engine.data.object;

import engine.opengl.shader.Shader;
import engine.opengl.shader.ShaderCompiler;
import engine.opengl.shader.ShaderProgram;

import static org.lwjgl.opengl.GL20C.glDeleteShader;

public class MaterialData {
	private Shader vertexShader;
	private Shader fragmentShader;

	private ShaderProgram shaderProgram;

	public MaterialData(Shader vertexShader, Shader fragmentShader, ShaderProgram shaderProgram)
	{
		this.vertexShader = vertexShader;
		this.fragmentShader = fragmentShader;
		this.shaderProgram = shaderProgram;
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

	public ShaderProgram getShaderProgram() {
		return shaderProgram;
	}

	public void setShaderProgram(ShaderProgram shaderProgram) {
		this.shaderProgram = shaderProgram;
	}
}
