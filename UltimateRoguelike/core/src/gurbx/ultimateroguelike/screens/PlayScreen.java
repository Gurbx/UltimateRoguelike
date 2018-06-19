package gurbx.ultimateroguelike.screens;

import java.awt.RenderingHints.Key;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.factories.PlayerFactory;
import gurbx.ultimateroguelike.world.World;
import gurbx.ultimateroguelike.world.WorldGenerator;
import gurbx.ultimateroguelike.world.generator.LevelMapRendererTest;

public class PlayScreen extends GameScreen {
	private TextureAtlas atlas;
	
	LevelMapRendererTest renderTest;
	
	public PlayScreen(Application app) {
		super(app);
	}
	
	@Override
	public void show() {
		super.show();
		atlas =  app.assets.get("img_packed/generalPack.atlas", TextureAtlas.class);
		
//		Entity player = PlayerFactory.createPlayer(new Vector2(100,100), atlas);
//		engine.addEntity(player);
		
		//Test
		World world = WorldGenerator.generateWorld();
		renderTest = new LevelMapRendererTest(world.tiles);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render(delta);
		if (Gdx.input.isKeyJustPressed(Keys.A)) {
			renderTest.setTiles(WorldGenerator.generateWorld().tiles);
		}
		app.batch.begin();
		renderTest.render(app.batch);
		app.batch.end();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		renderTest.dispose();
	}
}