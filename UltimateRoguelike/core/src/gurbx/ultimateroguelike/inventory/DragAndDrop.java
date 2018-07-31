package gurbx.ultimateroguelike.inventory;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DragAndDrop implements InputProcessor {
	private ArrayList<InventoryRenderer> openInventories;
	
	public DragAndDrop() {
		openInventories = new ArrayList<InventoryRenderer>();
	}
	
	public void addInventory(InventoryRenderer inv) {
		openInventories.add(inv);
	}
	
	public void removeInventory(InventoryRenderer inv) {
		for (int i = 0; i < openInventories.size(); i++) {
			if (inv == openInventories.get(i)) {
				openInventories.remove(i);
				return;
			}
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			for (int i = 0; i < openInventories.size(); i++) {
				openInventories.get(i).getItemStack(screenX, screenY);
			}
			return true;
		}
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
