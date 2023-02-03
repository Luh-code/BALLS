package engine.opengl.shader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.lwjgl.opengl.GL20C.*;
import static utils.Logger.logError;

public class ShaderCompiler {
	private static Shader compileShader(int shaderType, String code)
	{
		int temp = glCreateShader(shaderType);
		glShaderSource(temp, code);
		glCompileShader(temp);

		String log;
		int success = 0;
		glGetShaderiv(temp, GL_COMPILE_STATUS, new int[]{success});
		if (success != 0)
		{
			logError(glGetShaderInfoLog(temp, 512));
			assert(false);
		}
		return new Shader(temp);
	}

	public static Shader compileVertexShader(String code)
	{
		return compileShader(GL_VERTEX_SHADER, code);
	}

	public static Shader compileFragmentShader(String code)
	{
		return compileShader(GL_VERTEX_SHADER, code);
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
		return sb.toString();
	}
}
