package gurbx.ultimateroguelike.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class World {
	public String[][] tiles;
	public Tile[][] tileMap;
	
	//For rendering
	private Vector2 renderPosition;
	private final int RENDER_MAX_WIDTH = 5, RENDER_MAX_HEIGHT = 5;
	private int renderX, renderY, renderWidth, renderHeight;
	
	public void setRenderPosition(Vector2 renderPosition) {
		this.renderPosition = renderPosition;
	}
	
	public void update(float delta) {
		
	}
	
	public void render(SpriteBatch batch) {
		for (int y = renderY; y < renderY+renderHeight ; y++) {
			for (int x = renderX; x < renderX+renderWidth; x++) {
				tileMap[x][y].render(batch);
			}
		}
	}
}
