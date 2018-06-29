package gurbx.ultimateroguelike.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import gurbx.ultimateroguelike.utils.Constants;

public class Tile {
	private float x, y;
	private int coordX, coordY;
	private Body body;
	private TextureRegion texture;
	private boolean empty;
	
	public Tile(int coordX, int coordY, TextureRegion texture, boolean empty) {
		this.empty = empty;
		
		this.coordX = coordX;
		this.coordY = coordY;
		
		x = coordX * Constants.TILE_SIZE;
		y = coordY * Constants.TILE_SIZE;
		
		this.texture = texture;
	}
	
	public void render(SpriteBatch batch) {
		if (empty) return;
		batch.draw(texture, 
				x - Constants.TILE_SIZE*0.5f, 
				y - Constants.TILE_SIZE*0.5f);
	}
}
