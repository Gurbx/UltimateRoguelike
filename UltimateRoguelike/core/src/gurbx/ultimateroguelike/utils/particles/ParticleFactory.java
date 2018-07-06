package gurbx.ultimateroguelike.utils.particles;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.physics.box2d.Body;

import gurbx.ultimateroguelike.components.ParticleEffectComponent;

public class ParticleFactory {
	private ParticleEffectManager particleEffectManager;
	
	public ParticleFactory(AssetManager assets) {
		particleEffectManager = new ParticleEffectManager();
		
		//Init effects
		particleEffectManager.addParticleEffect(ParticleEffectManager.SMOKE, assets.get("particles/hit.p", ParticleEffect.class), 1f/64f);
	}
	
	public Entity makeParticleEffect(int type, float x, float y){
		Entity entity = new Entity();
		ParticleEffectComponent pec = new ParticleEffectComponent();
		pec.particleEffect = particleEffectManager.getPooledParticleEffect(type);
		pec.particleEffect.setPosition(x, y);
		entity.add(pec);
		return entity;
	}
	
	public Entity makeParticleEffect(int type, Body b2dbody){
		return makeParticleEffect(type,b2dbody,0,0);
	}

	public Entity makeParticleEffect(int type, Body body, float xo, float yo){
		Entity entPE = new Entity();
		ParticleEffectComponent pec = new ParticleEffectComponent();
		pec.particleEffect = particleEffectManager.getPooledParticleEffect(type);
		pec.particleEffect.setPosition(body.getPosition().x, body.getPosition().y);
		pec.particleEffect.getEmitters().first().setAttached(true); //manually attach for testing
	        pec.xOffset = xo; 
	        pec.yOffset = yo;
		pec.isAttached = true;
		pec.attachedBody = body;
		entPE.add(pec);
		return entPE;
	}
}
