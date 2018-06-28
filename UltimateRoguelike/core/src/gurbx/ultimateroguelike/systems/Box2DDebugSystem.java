package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import gurbx.ultimateroguelike.utils.Constants;

public class Box2DDebugSystem extends EntitySystem implements Disposable {
	private Box2DDebugRenderer b2dr;
    private World world;
    private OrthographicCamera camera;

	public Box2DDebugSystem(World world, OrthographicCamera camera) {
		this.world = world;
		this.camera = camera;
		b2dr = new Box2DDebugRenderer();
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		b2dr.render(world, camera.combined.scl(Constants.PPM));
	}

	@Override
	public void dispose() {
		b2dr.dispose();
	}

}
