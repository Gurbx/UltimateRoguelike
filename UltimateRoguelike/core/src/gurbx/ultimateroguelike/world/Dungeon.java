package gurbx.ultimateroguelike.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Dungeon {
	public String[][] tiles;
	public Tile[][] tileMap;
	
	//For rendering
	private Vector2 renderPosition;
	private final int RENDER_MAX_WIDTH = 5, RENDER_MAX_HEIGHT = 5;
	private int renderX, renderY, renderWidth, renderHeight;
	
	public Dungeon(String[][] tiles) {
		this.tiles = tiles;
	}

	public void setRenderPosition(Vector2 renderPosition) {
		this.renderPosition = renderPosition;
	}
	
	public void render(SpriteBatch batch) {
		for (int i = 0; i < tileMap.length; i++) {
			for (int j = 0; j < tileMap.length; j++) {
				tileMap[i][j].render(batch);
			}
		}
		
		
//		for (int y = renderY; y < renderY+renderHeight ; y++) {
//			for (int x = renderX; x < renderX+renderWidth; x++) {
//				tileMap[x][y].render(batch);
//			}
//		}
	}
}
