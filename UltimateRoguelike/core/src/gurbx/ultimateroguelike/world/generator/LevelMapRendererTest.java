package gurbx.ultimateroguelike.world.generator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gurbx.ultimateroguelike.world.WorldConstants;

public class LevelMapRendererTest {
	private Texture tex;
	private Sprite sprite;
	private final int SIZE = 6;
	
	String[][] tiles;
	
	public LevelMapRendererTest(String[][] tiles) {
		this.tiles = tiles;
		
		Pixmap pixmap = new Pixmap(SIZE, SIZE, Format.RGBA8888 );
		pixmap.setColor(1, 1, 1, 1);
		pixmap.fillRectangle(0, 0, SIZE, SIZE);
		tex = new Texture(pixmap);
		pixmap.dispose();
		
		sprite = new Sprite(tex);
	}
	
	public void setTiles(String[][] tiles) {
		this.tiles = tiles;
	}
	
	public void render(SpriteBatch batch) {		
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
	}
	
	public void dispose() {
		tex.dispose();
	}
}
