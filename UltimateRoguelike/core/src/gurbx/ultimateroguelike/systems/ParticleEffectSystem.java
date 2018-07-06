package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import gurbx.ultimateroguelike.components.ParticleEffectComponent;

public class ParticleEffectSystem extends IteratingSystem {

	private Array<Entity> renderQueue;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	public ParticleEffectSystem(SpriteBatch batch, OrthographicCamera camera) {
		super(Family.all(ParticleEffectComponent.class).get());
		this.batch = batch;
		this.camera = camera;
		renderQueue = new Array<Entity>();
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		System.out.println(renderQueue.size);
		
		batch.setProjectionMatrix(camera.combined);
    	batch.enableBlending();

    	batch.begin();
    	for (Entity entity : renderQueue) {
    		ParticleEffectComponent pec = ParticleEffectComponent.MAPPER.get(entity);
        	pec.particleEffect.draw(batch, deltaTime);
    	}
   		batch.end();
    	
    	renderQueue.clear();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		ParticleEffectComponent pec = ParticleEffectComponent.MAPPER.get(entity);
		if(pec.isDead){
			pec.timeTilDeath -= deltaTime;
		}
		
		 // Move PE if attached
		if(pec.isAttached){
			pec.particleEffect.setPosition(
					pec.attachedBody.getPosition().x + pec.xOffset,
					pec.attachedBody.getPosition().y + pec.yOffset);
		}
		
		 // free PE if completed
		if(pec.particleEffect.isComplete() || pec.timeTilDeath <= 0){
			getEngine().removeEntity(entity);
		}else{
			renderQueue.add(entity);
		}
		
	}

}
