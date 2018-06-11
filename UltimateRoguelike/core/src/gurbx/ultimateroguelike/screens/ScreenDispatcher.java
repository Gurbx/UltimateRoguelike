package gurbx.ultimateroguelike.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;

public class ScreenDispatcher {
	private ArrayList<Screen> screens;
    private boolean isCurrenScreenEnded = false;
    private int currentIndex = 0;
	
	public ScreenDispatcher() {
		screens = new ArrayList<Screen>();
	}
	
    public void addScreen(Screen screen) {
        screens.add(screen);
    }
    
    public void endCurrentScreen() {
    	isCurrenScreenEnded = true;
    }
    
    public Screen getNextScreen() {
        if (isCurrenScreenEnded) {
            isCurrenScreenEnded = false;
            //Do logic to pick the next screen
            currentIndex++;
        }

        if (screens.size() > currentIndex) {
            return screens.get(currentIndex);
        } else {
            return screens.get(0);
        }
    }
}
