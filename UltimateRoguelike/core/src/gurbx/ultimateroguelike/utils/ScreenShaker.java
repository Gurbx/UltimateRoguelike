package gurbx.ultimateroguelike.utils;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class ScreenShaker {
	private OrthographicCamera camera;
	private Vector3 camPos;
	private Random random = new Random();
	private boolean shakeScreen = false;
	private int shakes = 0;
	private int nShakes = 0;
	private float velocity = 1f;
	private boolean shake2 = false;
	
	public ScreenShaker(OrthographicCamera camera) {
		this.camera = camera;
	}
	
	public void update(float delta) {
		
		if (shakeScreen) {
			Gdx.input.vibrate(10); //If mobile
			float n1 = random.nextInt(2) == 0 ? random.nextFloat() * velocity : random.nextFloat() * velocity * -1;
			float n2 = random.nextInt(2) == 0 ? random.nextFloat() * velocity : random.nextFloat() * velocity * -1;
			
			camera.translate(n2, n1, 0);
			
	         if (shake2) {
	            shake2 = false;
	            camera.position.set(camPos);
	         } else {
	            shake2 = true;
	         }
	         shakes++;

	         if (shakes > nShakes) {
	            shakeScreen = false;
	            nShakes = 0;
	            shakes = 0;
	            camera.position.set(camPos);
	         }
		}
	}
	
	public void shakeScreen(int nshakes, Vector3 camPos, float velocity, boolean priority) {
	      if (!shakeScreen || priority) {
	         nShakes = nshakes;
	         shakeScreen = true;
	         this.camPos = camPos;
	         this.velocity = velocity;  
	      }
	}
}
