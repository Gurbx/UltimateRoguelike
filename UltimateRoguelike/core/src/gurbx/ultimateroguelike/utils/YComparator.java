package gurbx.ultimateroguelike.utils;

import java.util.Comparator;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

import gurbx.ultimateroguelike.components.TransformComponent;

public class YComparator implements Comparator<Entity> {
	private ComponentMapper<TransformComponent> transComp;
	
	public YComparator() {
		transComp = ComponentMapper.getFor(TransformComponent.class);
	}

	@Override
	public int compare(Entity a, Entity b) {
		return (int) Math.signum(transComp.get(b).position.y -
                transComp.get(a).position.y);
	}

}
