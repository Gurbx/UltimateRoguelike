package gurbx.ultimateroguelike.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class StateComponent implements Component {
	//STATES
	public static final String IDLE = "idle"; //Default
	public static final String RUN = "run";
	public static final String ATTACK = "attack";
	
	public static final ComponentMapper<StateComponent> MAPPER = ComponentMapper.getFor(StateComponent.class);
	private String state = IDLE;
    public float time = 0.0f;
    public boolean isLooping = true;

    public void set(String newState) {
        state = newState;
        time = 0.0f;
    }

    public String getState() {
        return state;
    }
}
