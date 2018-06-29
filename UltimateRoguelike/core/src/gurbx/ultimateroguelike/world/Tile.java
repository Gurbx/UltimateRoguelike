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
	
	public Tile(int coordX, int coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
	}
	
	public void update(float delta) {
		
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(texture, 
				x * Constants.PPM - Constants.TILE_SIZE*0.5f, 
				y * Constants.PPM - Constants.TILE_SIZE*0.5f);
	}
}
