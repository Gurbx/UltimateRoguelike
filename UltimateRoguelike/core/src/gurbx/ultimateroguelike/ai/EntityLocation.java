package gurbx.ultimateroguelike.ai;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;

import gurbx.ultimateroguelike.utils.SteeringUtils;

public class EntityLocation implements Location<Vector2> {

    Vector2 position;
    float orientation;
    
    public EntityLocation(Vector2 position) {
        this.position = position;
        this.orientation = 0;
    }

    public EntityLocation() {
        this.position = new Vector2();
        this.orientation = 0;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public float getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    @Override
    public Location<Vector2> newLocation() {
        return new EntityLocation();
    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return SteeringUtils.vectorToAngle(vector);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        return SteeringUtils.angleToVector(outVector, angle);
    }
}
