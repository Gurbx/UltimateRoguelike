package gurbx.ultimateroguelike.utils;

import java.util.List;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class CollisionSystem implements ContactListener {
	private final List<CollisionListener> collisionListeners;
	
	public CollisionSystem(World world, List<CollisionListener> collisionListeners) {
		this.collisionListeners = collisionListeners;
		world.setContactListener(this);
	}

	@Override
	public void beginContact(Contact contact) {
		for (CollisionListener collisionListener : collisionListeners) {
			collisionListener.onBeginContact(contact.getFixtureA(), contact.getFixtureB());
		}
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
