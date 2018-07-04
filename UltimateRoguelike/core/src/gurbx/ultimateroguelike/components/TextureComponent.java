package gurbx.ultimateroguelike.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TextureComponent implements Component, Poolable {
	public static final ComponentMapper<TextureComponent> MAPPER = ComponentMapper.getFor(TextureComponent.class);
	public TextureRegion region;
	public boolean flipX;

	@Override
	public void reset() {
		region = null;
	}
}
