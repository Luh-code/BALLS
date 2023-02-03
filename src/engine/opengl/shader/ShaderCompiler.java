package engine.opengl.shader;

import engine.data.components.Material;
import engine.data.object.MaterialData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.lwjgl.opengl.GL20C.*;
import static utils.Logger.logError;

public class ShaderCompiler {
	public static Shader compileShader(int shaderType, String shaderCode) throws Exception {
		int shaderId = glCreateShader(shaderType);
		if (shaderId == 0) {
			throw new Exception("Error creating shader. Type: " + shaderType);
		}

		glShaderSource(shaderId, shaderCode);
		glCompileShader(shaderId);

		if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
			throw new Exception("Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024));
		}
		return new Shader(shaderId);
	}

	public static Shader compileVertexShader(String code)
	{
		try {
			return compileShader(GL_VERTEX_SHADER, code);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Shader compileFragmentShader(String code)
	{
		try {
			return compileShader(GL_FRAGMENT_SHADER, code);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String readShader(String path)
	{
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		StringBuilder sb = new StringBuilder();
		br.lines().forEach(e -> sb.append(e+"\n"));
		sb.append("\0");
		return sb.toString();
	}

	public static ShaderProgram createShaderProgram(Shader vertex, Shader fragment)
	{
		int temp = glCreateProgram();
		glAttachShader(temp, vertex.getHandle());
		glAttachShader(temp, fragment.getHandle());
		glLinkProgram(temp);

		int success = 0;
		glGetProgramiv(temp, GL_LINK_STATUS, new int[]{success});
		if (success != 0)
		{
			logError("An error occurred whilst creating shader program: \n\t" + glGetProgramInfoLog(temp, 512));
			assert(false);
		}

		return new ShaderProgram(temp);
	}
}
