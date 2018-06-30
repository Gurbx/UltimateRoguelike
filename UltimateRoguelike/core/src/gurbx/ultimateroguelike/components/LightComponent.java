package gurbx.ultimateroguelike.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

import box2dLight.Light;

public class LightComponent implements Component {
	public static final ComponentMapper<LightComponent> MAPPER = ComponentMapper.getFor(LightComponent.class);
	public Light light;
	
}
