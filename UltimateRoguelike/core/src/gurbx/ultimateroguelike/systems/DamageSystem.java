package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

import gurbx.ultimateroguelike.components.AnimationComponent;
import gurbx.ultimateroguelike.components.BodyComponent;
import gurbx.ultimateroguelike.components.DamageComponent;
import gurbx.ultimateroguelike.components.LifeComponent;
import gurbx.ultimateroguelike.utils.CollisionListener;

public class DamageSystem extends EntitySystem implements CollisionListener {
//	private ImmutableArray<Entity> entities;

	private LifeComponent lifeComponent;

	@Override
	public void onBeginContact(Fixture fixtureA, Fixture fixtureB) {
		
		Entity entityA = (Entity) fixtureA.getUserData();
		Entity entityB = (Entity) fixtureB.getUserData();
		
		if (entityA == null || entityB == null) return;
		
		damageEntity(entityA, entityB);
		damageEntity(entityB, entityA);
	}

	private void damageEntity(Entity entityA, Entity entityB) {
		LifeComponent life = entityA.getComponent(LifeComponent.class);
		DamageComponent damage = entityB.getComponent(DamageComponent.class);
		
		if (life == null || damage == null) return;
		
		//PLAY SOUND AND PARTICLE EFFECTS AND STUFF HERE
		life.health -= damage.damage;
		System.out.println(life.health);
	}
	
}
