package gurbx.ultimateroguelike.components;

import java.util.LinkedList;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;

public class DeathComponent implements Component {
	public static final ComponentMapper<DeathComponent> MAPPER = ComponentMapper.getFor(DeathComponent.class);
	
	public LinkedList<Disposable> disposables = new LinkedList<Disposable>();
	public Body body;
}
