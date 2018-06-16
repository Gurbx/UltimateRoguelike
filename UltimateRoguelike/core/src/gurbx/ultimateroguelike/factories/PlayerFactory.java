package gurbx.ultimateroguelike.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import gurbx.ultimateroguelike.components.TextureComponent;
import gurbx.ultimateroguelike.components.TransformComponent;

public class PlayerFactory {
	
	public static Entity createPlayer(Vector2 position, TextureAtlas atlas) {
		Entity entity = new Entity();
		
		TransformComponent transform = new TransformComponent();
		transform.position.set(position);
		
		TextureComponent texture = new TextureComponent();
		texture.region = atlas.findRegion("wall");
		
		
		entity.add(transform);
		entity.add(texture);
		return entity;
	}

}
