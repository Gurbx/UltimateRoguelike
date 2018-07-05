package gurbx.ultimateroguelike.ai;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import gurbx.ultimateroguelike.components.AnimationComponent;

public class SteeringAgentComponent implements Steerable<Vector2>, Component{
	public static final ComponentMapper<SteeringAgentComponent> MAPPER = ComponentMapper.getFor(SteeringAgentComponent.class);
	
	private static final SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());
    protected SteeringBehavior<Vector2> steeringBehavior;

    Body body;
    boolean tagged;
    boolean independentFacing;
    float boundingRadius;
    public float maxLinearSpeed;
    public float maxLinearAcceleration;
    public float maxAngularSpeed;
    public float maxAngularAcceleration;
	
	public SteeringAgentComponent(Body body, float boundingRadius, boolean independentFacing) {
        this.body = body;
        this.independentFacing = independentFacing;
        this.boundingRadius = boundingRadius;
        this.tagged = false;
	}
	
	public void update(float delta) {
		if (steeringBehavior == null) return;
		
		steeringBehavior.calculateSteering(steeringOutput);
		applySteering(steeringOutput, delta);
	}
	
	private void applySteering(SteeringAcceleration<Vector2> steeringoutput2, float delta) {
        boolean anyAccelerations = false;

        // Update position and linear velocity.
        if (!steeringOutput.linear.isZero()) {
            // this method internally scales the force by deltaTime
            body.applyForceToCenter(steeringOutput.linear, true);
            anyAccelerations = true;
        }

        // Update orientation and angular velocity
        if (isIndependentFacing()) {
            if (steeringOutput.angular != 0) {
                // this method internally scales the torque by deltaTime
                body.applyTorque(steeringOutput.angular, true);
                anyAccelerations = true;
            }
        } else {
            // If we haven't got any velocity, then we can do nothing.
            Vector2 linVel = getLinearVelocity();
            if (!linVel.isZero(getZeroLinearSpeedThreshold())) {
                float newOrientation = vectorToAngle(linVel);
                body.setAngularVelocity((newOrientation - getAngularVelocity()) * delta); // this is superfluous if independentFacing is always true
                body.setTransform(body.getPosition(), newOrientation);
            }
        }

        if (anyAccelerations) {
            // body.activate();

            // TODO:
            // Looks like truncating speeds here after applying forces doesn't work as expected.
            // We should likely cap speeds form inside an InternalTickCallback, see
            // http://www.bulletphysics.org/mediawiki-1.5.8/index.php/Simulation_Tick_Callbacks

            // Cap the linear speed
            Vector2 velocity = body.getLinearVelocity();
            float currentSpeedSquare = velocity.len2();
            float maxLinearSpeed = getMaxLinearSpeed();
            if (currentSpeedSquare > maxLinearSpeed * maxLinearSpeed) {
                body.setLinearVelocity(velocity.scl(maxLinearSpeed / (float) Math.sqrt(currentSpeedSquare)));
            }

            // Cap the angular speed
            float maxAngVelocity = getMaxAngularSpeed();
            if (body.getAngularVelocity() > maxAngVelocity) {
                body.setAngularVelocity(maxAngVelocity);
            }
        }	
	}

	@Override
	public Vector2 getPosition() {
		return body.getPosition();
	}

	@Override
	public float getOrientation() {
		return body.getAngle();
	}

	@Override
	public void setOrientation(float orientation) {
		this.body.setTransform(body.getPosition(), orientation);
	}

	@Override
	public float vectorToAngle(Vector2 vector) {
		return vector.angle();
	}

	@Override
	public Vector2 angleToVector(Vector2 outVector, float angle) {
		outVector.x = -(float)Math.sin(angle);
		outVector.y = (float)Math.cos(angle);
		return outVector;
	}

	@Override
	public Location<Vector2> newLocation() {
		return new EntityLocation();
	}

	@Override
	public float getZeroLinearSpeedThreshold() {
		return 0.001f;
	}

	@Override
	public void setZeroLinearSpeedThreshold(float value) {
		throw new UnsupportedOperationException();	
	}

	@Override
	public float getMaxLinearSpeed() {
		return maxLinearSpeed;
	}

	@Override
	public void setMaxLinearSpeed(float maxLinearSpeed) {
		this.maxLinearSpeed = maxLinearSpeed;		
	}

	@Override
	public float getMaxLinearAcceleration() {
		return maxLinearAcceleration;
	}

	@Override
	public void setMaxLinearAcceleration(float maxLinearAcceleration) {
		this.maxLinearAcceleration = maxLinearAcceleration;
	}

	@Override
	public float getMaxAngularSpeed() {
		return maxAngularSpeed;
	}

	@Override
	public void setMaxAngularSpeed(float maxAngularSpeed) {
		this.maxAngularSpeed = maxAngularSpeed;
	}

	@Override
	public float getMaxAngularAcceleration() {
		return maxAngularAcceleration;
	}

	@Override
	public void setMaxAngularAcceleration(float maxAngularAcceleration) {
		this.maxAngularAcceleration = maxAngularAcceleration;
	}

	@Override
	public Vector2 getLinearVelocity() {
		return body.getLinearVelocity();
	}

	@Override
	public float getAngularVelocity() {
		return body.getAngularVelocity();
	}

	@Override
	public float getBoundingRadius() {
		return boundingRadius;
	}

	@Override
	public boolean isTagged() {
		return tagged;
	}

	@Override
	public void setTagged(boolean tagged) {
		this.tagged = tagged;
	}
	
	public boolean isIndependentFacing() {
		 return independentFacing;
	}

	public void setIndependentFacing(boolean independentFacing) {
		this.independentFacing = independentFacing;
	}
	
	public void setBody(Body body) {
		this.body = body;
	}
	
	public Body getBody() {
		return body;
	}
	
	public void setBehavior(SteeringBehavior<Vector2> behavior) {
		this.steeringBehavior = behavior;
	}
	
	public SteeringBehavior<Vector2> getBehavior() {
		return steeringBehavior;
	}
}
