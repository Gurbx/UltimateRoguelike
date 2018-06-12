package gurbx.ultimateroguelike.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.factories.PlayerFactory;

public class PlayScreen extends GameScreen {
	private TextureAtlas atlas;
	
	public PlayScreen(Application app) {
		super(app);
	}
	
	@Override
	public void show() {
		super.show();
		atlas =  app.assets.get("img_packed/generalPack.atlas", TextureAtlas.class);
		
		Entity player = PlayerFactory.createPlayer(new Vector2(100,100), atlas);
		engine.addEntity(player);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render(delta);
//		app.batch.begin();
//		app.batch.end();
	}
	
}