package engine.data.components;

import engine.data.object.MaterialData;

public class Material {
	private MaterialData data;

	public Material(MaterialData data)
	{
		this.data = data;
	}

	public MaterialData getData() {
		return data;
	}

	public void setData(MaterialData data) {
		this.data = data;
	}
}
