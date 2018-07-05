package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import gurbx.ultimateroguelike.ai.SteeringAgentComponent;
import gurbx.ultimateroguelike.components.TextureComponent;
import gurbx.ultimateroguelike.components.TransformComponent;

public class SteeringSystem extends IteratingSystem {

	public SteeringSystem() {
		super(Family.all(SteeringAgentComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		SteeringAgentComponent steering = SteeringAgentComponent.MAPPER.get(entity);
		
		steering.update(deltaTime);
	}

}
