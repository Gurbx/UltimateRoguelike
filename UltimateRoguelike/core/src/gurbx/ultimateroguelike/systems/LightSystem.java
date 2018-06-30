package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import box2dLight.RayHandler;
import gurbx.ultimateroguelike.utils.Constants;

public class LightSystem extends EntitySystem implements Disposable {
	private World world;
	private RayHandler rayHandler;
	private OrthographicCamera camera;
	
	public LightSystem(RayHandler rayHandler, OrthographicCamera camera) {
		this.rayHandler = rayHandler;
		this.camera = camera;
		
		RayHandler.useDiffuseLight(true);
		rayHandler.setShadows(true);
//		rayHandler.setAmbientLight(0.1f, 0.1f, 0.1f, 0.1f);
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		rayHandler.update();
		
		rayHandler.setCombinedMatrix(camera.combined.scl(Constants.PPM));
		rayHandler.render();
	}


	@Override
	public void dispose() {
	}

}
