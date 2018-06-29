package gurbx.ultimateroguelike.factories;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationBuilder {
	
	public static Animation createAnimation(TextureAtlas atlas, String name, int numberOfFrames, float fps) {
		TextureRegion[] frames = new TextureRegion[numberOfFrames];
		
		for (int i = 0; i < frames.length; i++) {
			frames[i] = atlas.findRegion(name + (i+1));
		}
		return new Animation(fps, frames);
	}
}
