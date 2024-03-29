package gurbx.ultimateroguelike.utils;

import com.badlogic.gdx.math.Vector2;

public class SteeringUtils {

	public static Vector2 angleToVector(Vector2 outVector, float angle) {
        outVector.x = -(float)Math.sin(angle);
        outVector.y = (float)Math.cos(angle);
        return outVector;
	}

	public static float vectorToAngle(Vector2 vector) {
		return (float)Math.atan2(-vector.x, vector.y);
	}

}
