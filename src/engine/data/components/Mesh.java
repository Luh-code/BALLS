package engine.data.components;

import engine.data.object.IMeshData;

public class Mesh {
	private IMeshData md;

	public Mesh (IMeshData md)
	{
		this.md = md;
	}

	public IMeshData getMd() {
		return md;
	}

	public void setMd(IMeshData md) {
		this.md = md;
	}
}
