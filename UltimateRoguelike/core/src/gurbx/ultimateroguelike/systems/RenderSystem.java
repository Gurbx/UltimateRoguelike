package gurbx.ultimateroguelike.systems;

import java.util.Comparator;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import gurbx.ultimateroguelike.components.TextureComponent;
import gurbx.ultimateroguelike.components.TransformComponent;
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.utils.YComparator;
import gurbx.ultimateroguelike.world.Dungeon;

public class RenderSystem extends SortedIteratingSystem {
//	private ImmutableArray<Entity> entities;
	private Array<Entity> renderQueue;
	private static Comparator<Entity> comparator = new YComparator();
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Dungeon dungeon;
	
	public RenderSystem(SpriteBatch batch, OrthographicCamera camera, Dungeon dungeon) {
		super(Family.all(TransformComponent.class, TextureComponent.class).get(), comparator);
		this.batch = batch;
		this.camera = camera;
		this.dungeon = dungeon;
		
		renderQueue = new Array<Entity>();
	}
	
	@Override
		public void update(float deltaTime) {
			super.update(deltaTime);
			
			renderQueue.sort(comparator);
			
			camera.update();
			
			batch.setProjectionMatrix(camera.combined);
			batch.begin();

//			dungeon.render(batch);
			
			for (Entity entity : renderQueue) {
				TextureComponent texComp = TextureComponent.MAPPER.get(entity);
				TransformComponent transComp = TransformComponent.MAPPER.get(entity);
				
				if (texComp.region == null) continue;
				
				//Flip horizontal if needed
				if (texComp.flipX != texComp.region.isFlipX()) {
					texComp.region.flip(true, false);
				}
				
				batch.draw(texComp.region, 
						transComp.position.x * Constants.PPM - texComp.region.getRegionWidth() * 0.5f ,
						transComp.position.y * Constants.PPM - texComp.region.getRegionHeight() * 0.5f);
			}
			batch.end();
			renderQueue.clear();
		}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		renderQueue.add(entity);
	}
}
