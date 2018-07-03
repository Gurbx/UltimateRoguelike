package gurbx.ultimateroguelike.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import gurbx.ultimateroguelike.components.AnimationComponent;
import gurbx.ultimateroguelike.components.BodyComponent;
import gurbx.ultimateroguelike.components.DamageComponent;
import gurbx.ultimateroguelike.components.LightComponent;
import gurbx.ultimateroguelike.components.MovementComponent;
import gurbx.ultimateroguelike.components.PlayerComponent;
import gurbx.ultimateroguelike.components.StateComponent;
import gurbx.ultimateroguelike.components.TextureComponent;
import gurbx.ultimateroguelike.components.TransformComponent;
import gurbx.ultimateroguelike.systems.CameraSystem;
import gurbx.ultimateroguelike.utils.Constants;

public class PlayerFactory {
	
	public static Entity createPlayer(float x, float y, TextureAtlas atlas, World world, RayHandler rayHandler) {
		Entity entity = new Entity();
		
		TransformComponent transform = new TransformComponent();
		transform.position.set(x, y);
		//SET CAMERA PROJECTION
		CameraSystem.setCameraTarget(transform.position);
		entity.add(transform);
		
		StateComponent stateComp = new StateComponent();
		entity.add(stateComp);
		
		AnimationComponent ac = new AnimationComponent();
		ac.animations.put(StateComponent.IDLE, AnimationBuilder.createAnimation(atlas, "blob_idle", 5, 1/6f));
		ac.animations.put(StateComponent.RUN, AnimationBuilder.createAnimation(atlas, "blob_idle", 5, 1/12f));
		entity.add(ac);
		
		TextureComponent texture = new TextureComponent();
		entity.add(texture);
		
		DamageComponent damageComponent = new DamageComponent();
		damageComponent.damage = 1;
		damageComponent.pushBack = 2500f;
		entity.add(damageComponent);
		
		BodyComponent bodyComponent = new BodyComponent();
		bodyComponent.body = BodyBuilder.createDynamicBody(transform.position, 32, 32, world, entity);
		bodyComponent.body.setLinearDamping(15);
		entity.add(bodyComponent);
		
		LightComponent lightComponent = new LightComponent();
		lightComponent.light = new PointLight(rayHandler, 128);
		lightComponent.light.attachToBody(bodyComponent.body);
		lightComponent.light.setColor(1f, 0.6f, 0.6f, 0.8f);
		lightComponent.light.setDistance(14f);
		Filter filter = new Filter();
		filter.maskBits = Constants.WORLD;
		lightComponent.light.setContactFilter(filter);
		entity.add(lightComponent);
		
		MovementComponent mc = new MovementComponent();
		mc.speed = 100;
		entity.add(mc);
		
		PlayerComponent pc = new PlayerComponent();
		entity.add(pc);
		
		return entity;
	}

}
