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
import gurbx.ultimateroguelike.components.ProjectileComponent;
import gurbx.ultimateroguelike.utils.CollisionListener;
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.utils.particles.ParticleEffects;
import gurbx.ultimateroguelike.utils.particles.ParticleFactory;
import gurbx.ultimateroguelike.utils.sound.SoundHandler;

public class DamageSystem extends EntitySystem implements CollisionListener {
	private ParticleFactory particleFactory;

	public DamageSystem(ParticleFactory particleFactory) {
		this.particleFactory = particleFactory;
	}
	
	@Override
	public void onBeginContact(Fixture fixtureA, Fixture fixtureB) {
		Entity entityA = (Entity) fixtureA.getUserData();
		Entity entityB = (Entity) fixtureB.getUserData();
		
		if (entityA != null) destroyBullet(entityA, fixtureA.getBody()); // if bullets, destroy them
		if (entityB != null) destroyBullet(entityB, fixtureB.getBody());
		
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
		//BELLOW IN THE DAMAGED AND KILLED PLACES
		
		//DAMAGE
		life.health -= damage.damage;
		
		//DEATH (if target dies)
		if (life.health <= 0) {			
			entityKilled(entityA, life, bodyA);
		} else {
			entityDamaged(entityA, life, bodyA);
		}
	}	
	

	private void entityKilled(Entity entity, LifeComponent life, Body body) {
		CameraSystem.shakeScreen(8, 15, true); // Bigger shake
		if (life.deathSound != null) SoundHandler.playSound(life.deathSound);
		
		//PARTICLE EFFECT
		if (life.deathEffect != null) {
			Entity particleE = particleFactory.makeParticleEffect(
					life.deathEffect.ID, 
					body.getPosition().x * Constants.PPM,
					body.getPosition().y * Constants.PPM);
			getEngine().addEntity(particleE);
		}
		
		DeathComponent deathComponent = new DeathComponent();
		deathComponent.body = body;
		entity.add(deathComponent);
	}

	private void entityDamaged(Entity entity, LifeComponent life, Body body) {
		CameraSystem.shakeScreen(4, 10, false); // smaller shake
		if (life.hitSound != null) SoundHandler.playSound(life.hitSound);
		
		//PARTICLE EFFECT
		if (life.hitEffect != null) {
			Entity particleE = particleFactory.makeParticleEffect(
					life.hitEffect.ID, 
					body.getPosition().x * Constants.PPM,
					body.getPosition().y * Constants.PPM);
			getEngine().addEntity(particleE);
		}
		
	}

	private void destroyBullet(Entity entity, Body body) {
		ProjectileComponent projectile = entity.getComponent(ProjectileComponent.class);
		if (projectile == null) return;
		
		DeathComponent deathComponent = new DeathComponent();
		deathComponent.body = body;
		entity.add(deathComponent);
		
		CameraSystem.shakeScreen(3, 3, false);
	}
}
