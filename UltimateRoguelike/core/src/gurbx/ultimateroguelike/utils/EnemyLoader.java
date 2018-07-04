package gurbx.ultimateroguelike.utils;

import java.io.IOException;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import gurbx.ultimateroguelike.components.AnimationComponent;
import gurbx.ultimateroguelike.components.BodyComponent;
import gurbx.ultimateroguelike.components.LifeComponent;
import gurbx.ultimateroguelike.components.LightComponent;
import gurbx.ultimateroguelike.components.StateComponent;
import gurbx.ultimateroguelike.components.TextureComponent;
import gurbx.ultimateroguelike.components.TransformComponent;
import gurbx.ultimateroguelike.factories.AnimationBuilder;
import gurbx.ultimateroguelike.factories.BodyBuilder;

public class EnemyLoader {
	private XmlReader reader;
	private TextureAtlas atlas;
	private World world;
	private RayHandler rayHandler;
	
	public EnemyLoader(TextureAtlas atlas, World world, RayHandler rayHandler) {
		this.atlas = atlas;
		this.world = world;
		this.rayHandler = rayHandler;
		reader = new XmlReader();
	}
	
	//LOAD ENEMY
	public Entity loadEnemy(String path, float x, float y) {
		Entity entity = new Entity();
		
		Element root;
		
		try {
			root = reader.parse(Gdx.files.internal(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		//CREATE BASIC COMPONENTS
		TransformComponent transformComponent = new TransformComponent();
		transformComponent.position.set(x, y);
		transformComponent.offsetX = root.getFloat("offsetX");
		transformComponent.offsetY = root.getFloat("offsetY");
		entity.add(transformComponent);
		
		TextureComponent textureComponent = new TextureComponent();
		entity.add(textureComponent);
		
		BodyComponent bodyComponent = new BodyComponent();
		bodyComponent.body = BodyBuilder.createDynamicBody(transformComponent.position, 
				root.getFloat("width"), root.getFloat("height"), world, entity);
		bodyComponent.body.setLinearDamping(15);
		entity.add(bodyComponent);
		
		LifeComponent lifeComponent = new LifeComponent();
		lifeComponent.health = root.getInt("health");
		entity.add(lifeComponent);
		
		//ANIMATION AND STATES
		Element animations = root.getChildByName("States");
		if (animations != null) {
			AnimationComponent ac = createAnimations(animations);
			StateComponent sc = new StateComponent();
			entity.add(ac);
			entity.add(sc);
		}
		
		//LIGHT
		Element light = root.getChildByName("Light");
		if (light != null) {
			LightComponent lc = new LightComponent();
			lc.light = new PointLight(rayHandler, light.getInt("rays"));
			lc.light.setDistance(light.getFloat("range"));
			Element color = light.getChildByName("color");
			lc.light.setColor(color.getFloat("r"), color.getFloat("g"), color.getFloat("b"), color.getFloat("a"));
			lc.light.attachToBody(bodyComponent.body);
			//filter
			Filter filter = new Filter();
			filter.maskBits = Constants.WORLD;
			lc.light.setContactFilter(filter);
			entity.add(lc);
		}
		
		return entity;
	}
	
	//CREATE ANIMATIONS
	private AnimationComponent createAnimations(Element animations) {
		AnimationComponent ac = new AnimationComponent();
		for (int i = 0; i < animations.getChildCount(); i++) {
			ac.animations.put(animations.getChild(i).get("state"), 
					AnimationBuilder.createAnimation(atlas, 
							animations.getChild(i).get("path"), 
							animations.getChild(i).getInt("frames"), 
							animations.getChild(i).getFloat("fps")));
		}
		return ac;
	}

}