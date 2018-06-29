package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import gurbx.ultimateroguelike.components.AnimationComponent;
import gurbx.ultimateroguelike.components.StateComponent;
import gurbx.ultimateroguelike.components.TextureComponent;

public class AnimationSystem extends IteratingSystem {
	private AnimationComponent animComp;
	private StateComponent stateComp;
	private TextureComponent texComp;

    public AnimationSystem(){
        super(Family.all(AnimationComponent.class, StateComponent.class, TextureComponent.class).get());
    }

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		animComp = AnimationComponent.MAPPER.get(entity);
		stateComp = StateComponent.MAPPER.get(entity);
		
		if (animComp.animations.containsKey(stateComp.getState())) {
			texComp = TextureComponent.MAPPER.get(entity);
			texComp.region = (TextureRegion) animComp.animations.get(stateComp.getState()).getKeyFrame(stateComp.time, stateComp.isLooping);
		}
		
		stateComp.time += deltaTime;
	}
}
