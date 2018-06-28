package gurbx.ultimateroguelike.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class StateComponent implements Component {
	public static final ComponentMapper<StateComponent> MAPPER = ComponentMapper.getFor(StateComponent.class);
	private String state = "DEFAULT";
    public float time = 0.0f;
    public boolean isLooping = false;

    public void set(String newState) {
        state = newState;
        time = 0.0f;
    }

    public String getState() {
        return state;
    }
}
