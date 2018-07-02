package gurbx.ultimateroguelike.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;

import gurbx.ultimateroguelike.components.BodyComponent;
import gurbx.ultimateroguelike.components.LifeComponent;
import gurbx.ultimateroguelike.components.TextureComponent;
import gurbx.ultimateroguelike.components.TransformComponent;
import gurbx.ultimateroguelike.utils.Constants;

public class PropFactory {
	
	public static Entity createDestructible(float x, float y, TextureAtlas atlas, World world) {
		Entity e = new Entity();
		
		TransformComponent tfc = new TransformComponent();
		tfc.position.set(x, y);
		e.add(tfc);
		
		TextureComponent tc = new TextureComponent();
		tc.region = atlas.findRegion("crate");
		e.add(tc);
		
		LifeComponent lifeComponent = new LifeComponent();
		lifeComponent.health = 10;
		e.add(lifeComponent);
		
		BodyComponent bodyComponent = new BodyComponent();
		bodyComponent.body = BodyBuilder.createDynamicBody(tfc.position, tc.region.getRegionWidth(), tc.region.getRegionHeight(),
				world, e);
		bodyComponent.body.setLinearDamping(5f);
		e.add(bodyComponent);
		
		return e;
	}

}
