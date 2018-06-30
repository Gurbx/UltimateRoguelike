package gurbx.ultimateroguelike.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Disposable;

import box2dLight.Light;

public class LightComponent implements Component, Disposable {
	public static final ComponentMapper<LightComponent> MAPPER = ComponentMapper.getFor(LightComponent.class);
	public Light light;
	
	@Override
	public void dispose() {
		light.dispose();
	}
	
}
