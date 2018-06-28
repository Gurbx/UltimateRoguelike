package gurbx.ultimateroguelike.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Pool.Poolable;

public class AnimationComponent implements Component, Poolable {
	public static final ComponentMapper<AnimationComponent> MAPPER = ComponentMapper.getFor(AnimationComponent.class);
	public ArrayMap<String, Animation> animations = new ArrayMap<String, Animation>();

	@Override
	public void reset() {
		animations.clear(); 
	}
}
