package gurbx.ultimateroguelike.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TransformComponent implements Component, Poolable {
	public static final ComponentMapper<TransformComponent> MAPPER = ComponentMapper.getFor(TransformComponent.class);
	public final Vector2 position = new Vector2();
	public final Vector2 scale = new Vector2(1.0f, 1.0f);
	public float rotation = 0.0f;
	
	@Override
	public void reset() {
		position.set(0.0f, 0.0f);
		scale.set(1.0f, 1.0f);
		rotation = 0.0f;	
	}
}
