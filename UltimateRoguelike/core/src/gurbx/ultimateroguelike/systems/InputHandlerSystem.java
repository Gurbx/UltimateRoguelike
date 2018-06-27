package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;

import gurbx.ultimateroguelike.components.BodyComponent;
import gurbx.ultimateroguelike.components.TextureComponent;
import gurbx.ultimateroguelike.components.TransformComponent;
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.utils.InputHandlerInterface;

public class InputHandlerSystem extends EntitySystem implements InputHandlerInterface {
	private ImmutableArray<Entity> entities;
	
	private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);
	
	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(TransformComponent.class, BodyComponent.class).get());
	}

	@Override
	public void walk(Vector2 direction) {
		
		for (int i = 0; i < entities.size(); ++i) {
			Entity e = entities.get(i);
			BodyComponent body = bm.get(e);
			body.body.applyForce(direction, direction, true);
		}
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}

}
