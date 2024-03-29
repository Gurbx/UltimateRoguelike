package gurbx.ultimateroguelike.screens;

import java.awt.RenderingHints.Key;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.components.BodyComponent;
import gurbx.ultimateroguelike.components.ParticleEffectComponent;
import gurbx.ultimateroguelike.components.TransformComponent;
import gurbx.ultimateroguelike.factories.EnemyLoader;
import gurbx.ultimateroguelike.factories.PlayerFactory;
import gurbx.ultimateroguelike.factories.ProjectileFactory;
import gurbx.ultimateroguelike.factories.PropFactory;
import gurbx.ultimateroguelike.systems.CameraSystem;
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.utils.particles.ParticleEffects;
import gurbx.ultimateroguelike.utils.particles.ParticleFactory;
import gurbx.ultimateroguelike.utils.sound.SoundHandler;
import gurbx.ultimateroguelike.world.Dungeon;
import gurbx.ultimateroguelike.world.generator.DungeonGenerator;

public class PlayScreen extends GameScreen {
	
	private Body palyerBody;
	
	Entity box;
	
	public PlayScreen(Application app) {
		super(app);
	}
	
	@Override
	public void show() {
		super.show();
		
		SoundHandler.playMusic();
		
		Entity player = PlayerFactory.createPlayer(20*Constants.TILE_SIZE, 20*Constants.TILE_SIZE, playerAtlas, world, rayHandler);
		engine.addEntity(player);
		
		palyerBody = player.getComponent(BodyComponent.class).body;
		
		EnemyLoader enemyLoader = new EnemyLoader(enemyAtlas, world, rayHandler);
		
		engine.addEntity(enemyLoader.loadEnemy("data/enemies/Blob.xml",
				20*Constants.TILE_SIZE, 20*Constants.TILE_SIZE - 10, player.getComponent(BodyComponent.class).body));
		engine.addEntity(enemyLoader.loadEnemy("data/enemies/Blob.xml",
				20*Constants.TILE_SIZE, 20*Constants.TILE_SIZE - 10, player.getComponent(BodyComponent.class).body));
		engine.addEntity(enemyLoader.loadEnemy("data/enemies/Blob.xml",
				20*Constants.TILE_SIZE, 20*Constants.TILE_SIZE - 10, player.getComponent(BodyComponent.class).body));
		engine.addEntity(enemyLoader.loadEnemy("data/enemies/Blob.xml",
				20*Constants.TILE_SIZE, 20*Constants.TILE_SIZE - 10, player.getComponent(BodyComponent.class).body));
		
		for (int i = 0; i < 1; i++) {
			box = PropFactory.createDestructible(20*Constants.TILE_SIZE, 20*Constants.TILE_SIZE, dungeonAtlas, world, rayHandler);
			engine.addEntity(box);
		}
	}
	
	@Override
	protected void update(float delta) {
		super.update(delta);
		
		//DEBUG
		if (Gdx.input.isKeyJustPressed(Keys.R)) {
//			Entity e = particleFactory.makeParticleEffect(ParticleEffects.HIT.ID, palyerBody);
//			engine.addEntity(e);
			
			CameraSystem.setCameraTarget(box.getComponent(BodyComponent.class).body.getPosition());
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.T)) {
			CameraSystem.setCameraTarget(palyerBody.getPosition());
		}
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render(delta);
	}
	
	@Override
	public void dispose() {
		if (playerAtlas != null) playerAtlas.dispose();
		if (enemyAtlas != null) enemyAtlas.dispose();
		if (dungeonAtlas != null) dungeonAtlas.dispose();
		super.dispose();
	}
}