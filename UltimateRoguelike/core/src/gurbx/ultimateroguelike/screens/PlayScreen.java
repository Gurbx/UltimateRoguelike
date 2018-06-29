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
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.world.Dungeon;
import gurbx.ultimateroguelike.world.generator.DungeonGenerator;

public class PlayScreen extends GameScreen {
	
	
	public PlayScreen(Application app) {
		super(app);
	}
	
	@Override
	public void show() {
		super.show();
		
		Entity player = PlayerFactory.createPlayer(new Vector2(10*Constants.TILE_SIZE, 20*Constants.TILE_SIZE), enemyAtlas, world);
		engine.addEntity(player);
	}
	
	@Override
	protected void update(float delta) {
		super.update(delta);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render(delta);
	}
	
	@Override
	public void dispose() {
		atlas.dispose();
		enemyAtlas.dispose();
		dungeonAtlas.dispose();
		super.dispose();
	}
}