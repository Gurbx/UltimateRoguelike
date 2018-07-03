package gurbx.ultimateroguelike.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import gurbx.ultimateroguelike.components.BodyComponent;
import gurbx.ultimateroguelike.components.DamageComponent;
import gurbx.ultimateroguelike.components.LightComponent;
import gurbx.ultimateroguelike.components.MovementComponent;
import gurbx.ultimateroguelike.components.ProjectileComponent;
import gurbx.ultimateroguelike.components.TextureComponent;
import gurbx.ultimateroguelike.components.TransformComponent;

public class ProjectileFactory {
	
	public static Entity createProjectile(float x, float y, Vector2 direction, TextureAtlas atlas, World world, RayHandler rayHandler) {
		Entity entity = new Entity();
		
		TransformComponent transComp = new TransformComponent();
		transComp.position.x = x;
		transComp.position.y = y;
		transComp.scale.x = 0.5f;
		transComp.scale.y = 0.5f;
		entity.add(transComp);
		
		ProjectileComponent projectileComponent = new ProjectileComponent();
		projectileComponent.maxSpeed = 100;
		projectileComponent.minSPeed = 50;
		projectileComponent.acceleration = 50;
//		projectileComponent.deceleration = 400;
		projectileComponent.currentSpeed = 50;
		projectileComponent.direction = direction;
		entity.add(projectileComponent);
		
		TextureComponent textureComponent = new TextureComponent();
		textureComponent.region = atlas.findRegion("projectile1");
		entity.add(textureComponent);
		
		BodyComponent bodyComp = new BodyComponent();
		bodyComp.body =	BodyBuilder.createDynamicBody(transComp.position, 
				textureComponent.region.getRegionWidth()*0.5f, 
				textureComponent.region.getRegionHeight()*0.5f
				, world, entity);
		bodyComp.body.setBullet(true);
		entity.add(bodyComp);
		
		LightComponent lightComponent = new LightComponent();
		lightComponent.light = new PointLight(rayHandler, 16);
		lightComponent.light.attachToBody(bodyComp.body);
		lightComponent.light.setColor(1f, 0f, 0f, 1f);
		lightComponent.light.setDistance(2f);
		entity.add(lightComponent);
		
		DamageComponent damageComponent = new DamageComponent();
		damageComponent.damage = 2;
		damageComponent.pushBack = 2500f;
		entity.add(damageComponent);
		
		return entity;
	}

}
