package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import gurbx.ultimateroguelike.components.AnimationComponent;
import gurbx.ultimateroguelike.components.StateComponent;
import gurbx.ultimateroguelike.components.TextureComponent;

public class AnimationSystem extends IteratingSystem {
    
//	ComponentMapper<TextureComponent> tm;
//    ComponentMapper<AnimationComponent> am;
//    ComponentMapper<StateComponent> sm;

    public AnimationSystem(){
        super(Family.all(TextureComponent.class,
                AnimationComponent.class,
                StateComponent.class).get());

//        tm = ComponentMapper.getFor(TextureComponent.class);
//        am = ComponentMapper.getFor(AnimationComponent.class);
//        sm = ComponentMapper.getFor(StateComponent.class);
    }

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
		
	}
}
