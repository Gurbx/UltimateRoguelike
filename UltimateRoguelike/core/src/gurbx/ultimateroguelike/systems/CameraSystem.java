package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.utils.ScreenShaker;

public class CameraSystem extends EntitySystem {
	
	private static OrthographicCamera camera;
	private static ScreenShaker screenShaker;
	
	private static Vector2 target;
	private final float LERP = 10f;
	
	public CameraSystem(OrthographicCamera camera) {
		this.camera = camera;
		screenShaker = new ScreenShaker(camera);
	}
	
	public static void setCameraTarget(Vector2 position) {
		target = position;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		screenShaker.update(deltaTime);
		
		Vector3 position = camera.position;
		position.x += (target.x * Constants.PPM  - position.x) * LERP * deltaTime;
		position.y += (target.y * Constants.PPM  - position.y) * LERP * deltaTime;
		
		if (Gdx.input.isKeyPressed(Keys.Q)) camera.zoom -= 10 * deltaTime;
		if (Gdx.input.isKeyPressed(Keys.E)) camera.zoom += 10 * deltaTime;
	}
	
	public static void shakeScreen(int shakes, float velocity, boolean priority) {
		screenShaker.shakeScreen(shakes, camera.position, velocity, priority);
	}

}
