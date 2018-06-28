package gurbx.ultimateroguelike.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;

public class BodyComponent implements Component, Poolable {
	public static final ComponentMapper<BodyComponent> MAPPER = ComponentMapper.getFor(BodyComponent.class);
	public Body body;

	@Override
	public void reset() {
		body = null;
	}

}
