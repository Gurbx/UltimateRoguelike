package gurbx.ultimateroguelike.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import gurbx.ultimateroguelike.factories.BodyBuilder;
import gurbx.ultimateroguelike.utils.Constants;

public class Tile {
	private float x, y;
	private int coordX, coordY;
	private Body body;
	private TextureRegion texture;
	private boolean empty;
	private int walkCost = 10;
	
	public Tile(int coordX, int coordY, TextureRegion texture, boolean empty, boolean wall, World world) {
		this.empty = empty;
		
		this.coordX = coordX;
		this.coordY = coordY;
		
		x = coordX * Constants.TILE_SIZE;
		y = coordY * Constants.TILE_SIZE;
		
		this.texture = texture;
		
		if (!empty && wall) {
			body = BodyBuilder.createTileBody(x, y, Constants.TILE_SIZE, Constants.TILE_SIZE, world);
		}
	}
	
	public void render(SpriteBatch batch) {
		if (empty) return;
		batch.draw(texture, 
				x - Constants.TILE_SIZE*0.5f, 
				y - Constants.TILE_SIZE*0.5f);
	}
	
	public int getCoordX() { return coordX; }
	public int getCoordY() { return coordY; }
	public int getWalkCost() { return walkCost; }
	public boolean isWalkable() {
		return body == null;
	}
}
