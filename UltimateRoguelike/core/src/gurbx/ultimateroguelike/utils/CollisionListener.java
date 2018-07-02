package gurbx.ultimateroguelike.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public interface CollisionListener {

	public void onBeginContact(Fixture fixtureA, Fixture fixtureB);
}
