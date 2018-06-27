package gurbx.ultimateroguelike.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import gurbx.ultimateroguelike.components.BodyComponent;
import gurbx.ultimateroguelike.components.TextureComponent;
import gurbx.ultimateroguelike.components.TransformComponent;

public class PlayerFactory {
	
	public static Entity createPlayer(Vector2 position, TextureAtlas atlas, World world) {
		Entity entity = new Entity();
		
		TransformComponent transform = new TransformComponent();
		transform.position.set(position);
		entity.add(transform);
		
		TextureComponent texture = new TextureComponent();
		texture.region = atlas.findRegion("wall");
		entity.add(texture);
		
		BodyComponent bodyComponent = new BodyComponent();
		bodyComponent.body = BodyBuilder.createDynamicBody(transform.position, 32, 32, world);
		entity.add(bodyComponent);
		
		return entity;
	}

}
