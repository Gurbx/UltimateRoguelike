package gurbx.ultimateroguelike.systems;

import java.util.Iterator;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Disposable;

import gurbx.ultimateroguelike.components.AnimationComponent;
import gurbx.ultimateroguelike.components.BodyComponent;
import gurbx.ultimateroguelike.components.DamageComponent;
import gurbx.ultimateroguelike.components.DeathComponent;
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
		
		damageEntity(entityA, entityB, fixtureA.getBody(), fixtureB.getBody());
		damageEntity(entityB, entityA, fixtureB.getBody(), fixtureA.getBody());
	}

	private void damageEntity(Entity entityA, Entity entityB, Body bodyA, Body bodyB) {
		LifeComponent life = entityA.getComponent(LifeComponent.class);
		DamageComponent damage = entityB.getComponent(DamageComponent.class);
		
		if (life == null || damage == null) return;
		
		//APPLY PUSHBACK
		Vector2 force = bodyA.getPosition().sub(bodyB.getPosition());
		force.nor().scl(damage.pushBack);
		bodyA.applyForce(force, bodyA.getWorldCenter(), true);
		
		//TODO -- PLAY SOUND AND PARTICLE EFFECTS AND STUFF HERE -- !!
		
		//DAMAGE
		life.health -= damage.damage;
		CameraSystem.shakeScreen(10, 10, false);
		
		//DEATH
		if (life.health <= 0) {
			DeathComponent deathComponent = new DeathComponent();
			deathComponent.body = bodyA;
			entityA.add(deathComponent);
		}
	}	
}
