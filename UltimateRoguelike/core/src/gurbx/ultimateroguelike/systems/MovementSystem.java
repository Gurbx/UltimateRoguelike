package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import gurbx.ultimateroguelike.components.BodyComponent;
import gurbx.ultimateroguelike.components.MovementComponent;
import gurbx.ultimateroguelike.components.TransformComponent;

public class MovementSystem extends IteratingSystem {
	Vector2 force;
	
    private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);
    private ComponentMapper<MovementComponent> mm = ComponentMapper.getFor(MovementComponent.class);
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    

	public MovementSystem() {
		super(Family.all(BodyComponent.class, MovementComponent.class, TransformComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		BodyComponent bodyComp = bm.get(entity);
		MovementComponent moveComp = mm.get(entity);
		TransformComponent transConp = tm.get(entity);		
		
		if (moveComp.direction == null) return;
		
		force = moveComp.direction;
		force.x *= moveComp.speed;
		force.y *= moveComp.speed;
		
		bodyComp.body.applyForce(force, transConp.position, true);
	}
}
