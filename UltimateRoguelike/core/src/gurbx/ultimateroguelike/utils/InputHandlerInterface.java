package gurbx.ultimateroguelike.utils;

import com.badlogic.gdx.math.Vector2;

public interface InputHandlerInterface {
	void walk(Vector2 direction);
	void shoot();
	void interact();
}
