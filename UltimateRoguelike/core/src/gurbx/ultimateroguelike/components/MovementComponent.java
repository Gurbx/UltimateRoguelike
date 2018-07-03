package gurbx.ultimateroguelike.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;

public class MovementComponent implements Component {
	public static final ComponentMapper<MovementComponent> MAPPER = ComponentMapper.getFor(MovementComponent.class);
	public Vector2 velocity = new Vector2(0,0);
	public float speed;
}
