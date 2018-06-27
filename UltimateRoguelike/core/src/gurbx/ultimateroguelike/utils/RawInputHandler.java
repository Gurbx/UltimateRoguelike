package gurbx.ultimateroguelike.utils;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class RawInputHandler implements InputProcessor {
	
    private InputHandlerInterface listener;
    private Vector2 direction;
    
    public void registerListener(InputHandlerInterface listener) {
    	this.listener = listener;
    	direction = new Vector2(0,0);
    }
    
	@Override
	public boolean keyDown(int keycode) {
		
		if(keycode == Keys.W) {
            direction.y = 1;
            listener.walk(direction);
            return true;
	    } else if (keycode == Keys.S) {
	    	direction.y = -1;
            listener.walk(direction);
            return true;
	    } else if (keycode == Keys.D) {
	    	direction.x = 1;
            listener.walk(direction);
            return true;
	    } else if (keycode == Keys.A) {
	    	direction.x = -1;
            listener.walk(direction);
            return true;
	    }
		direction.set(0,0);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		direction.set(0,0);
		listener.walk(direction);
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
    
    

}
