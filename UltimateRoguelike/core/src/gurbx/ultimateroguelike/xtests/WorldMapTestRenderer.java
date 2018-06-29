package gurbx.ultimateroguelike.xtests;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.world.World;
import gurbx.ultimateroguelike.world.generator.WorldGenerator;
import gurbx.ultimateroguelike.world.utils.WorldConstants;

/**
 * Test program for rendering the generated world as an easy to see map
 * 
 * @author Philip
 *
 */

public class WorldMapTestRenderer extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	Viewport viewport;
	
	//	
	private Texture tex;
	private Sprite sprite;
	private final int SIZE = 6;
	
	String[][] tiles;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new StretchViewport(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, camera);
		viewport.apply();
		camera.position.set(Constants.VIRTUAL_WIDTH/2, Constants.VIRTUAL_HEIGHT/2, 0);
		
		World world = WorldGenerator.generateWorld();
		this.tiles = world.tiles;
		
		Pixmap pixmap = new Pixmap(SIZE, SIZE, Format.RGBA8888 );
		pixmap.setColor(1, 1, 1, 1);
		pixmap.fillRectangle(0, 0, SIZE, SIZE);
		tex = new Texture(pixmap);
		pixmap.dispose();
		
		sprite = new Sprite(tex);
	}
	
	private void update(float deltaTime) {
		if (Gdx.input.isKeyJustPressed(Keys.A)) {
			this.tiles = WorldGenerator.generateWorld().tiles;
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.W)) {
			WorldGenerator.removeDeadEnds(tiles, 99);
		}
	}
	
	@Override
	public void render() {
		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		for (int i = 0; i < tiles[0].length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				if (tiles[i][j].equals(WorldConstants.EMPTY) == false) {
					if (tiles[i][j].equals(WorldConstants.GROUND)) sprite.setColor(Color.LIGHT_GRAY);
					else if (tiles[i][j].equals(WorldConstants.ROOM)) sprite.setColor(Color.ORANGE);
					else if (tiles[i][j].equals(WorldConstants.DOOR)) sprite.setColor(Color.GREEN);
					sprite.setPosition(i*SIZE, j*SIZE);
					sprite.draw(batch);
				}
			}
		}
		batch.end();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		tex.dispose();
	}

}
