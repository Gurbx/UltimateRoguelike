package gurbx.ultimateroguelike.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;

public class ProjectileComponent implements Component {
	public static final ComponentMapper<ProjectileComponent> MAPPER = ComponentMapper.getFor(ProjectileComponent.class);
	public Vector2 direction = new Vector2(0,0);
	public float maxSpeed;
	public float minSPeed = 0;
	public float acceleration;
	public float deceleration;
	
	public float currentSpeed = 0;
}
