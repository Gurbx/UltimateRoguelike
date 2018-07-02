package gurbx.ultimateroguelike.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class DamageComponent implements Component {
	public static final ComponentMapper<DamageComponent> MAPPER = ComponentMapper.getFor(DamageComponent.class);
	public int damage = 1;
	public float pushBack = 0;
}
