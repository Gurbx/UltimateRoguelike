package gurbx.ultimateroguelike.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class SizeComponent implements Component, Poolable {
	public float width;
	public float height;

	@Override
	public void reset() {
		width = 0.0f;
		height = 0.0f;
	}
}
