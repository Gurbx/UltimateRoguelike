package gurbx.ultimateroguelike.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import gurbx.ultimateroguelike.components.TextureComponent;
import gurbx.ultimateroguelike.components.TransformComponent;
import gurbx.ultimateroguelike.utils.Constants;

public class PropFactory {
	
	public static Entity createDestructible(float x, float y, TextureAtlas atlas) {
		Entity e = new Entity();
		
		TransformComponent tfc = new TransformComponent();
		tfc.position.set(x/Constants.PPM, y/Constants.PPM);
		e.add(tfc);
		
		TextureComponent tc = new TextureComponent();
		tc.region = atlas.findRegion("chest3");
		e.add(tc);
		
		return e;
	}

}
