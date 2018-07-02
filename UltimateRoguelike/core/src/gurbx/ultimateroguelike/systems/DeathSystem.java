package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import gurbx.ultimateroguelike.components.DeathComponent;
import gurbx.ultimateroguelike.components.MovementComponent;

public class DeathSystem extends EntitySystem {
	private ImmutableArray<Entity> entities;
	private World world;
	
	public DeathSystem(World world) {
		this.world = world;
	}

	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(DeathComponent.class).get());
	}
	
	@Override
	public void update(float deltaTime) {
		for (int i = 0; i < entities.size(); i++) {
			
			DeathComponent deathComp = DeathComponent.MAPPER.get(entities.get(i));
			world.destroyBody(deathComp.body);
			
			//Dispose all disposable components
			for (Component comp : entities.get(i).getComponents()) {
				if (comp instanceof Disposable) ((Disposable) comp).dispose();
			}
			
			getEngine().removeEntity(entities.get(i));
		}
	}
}
