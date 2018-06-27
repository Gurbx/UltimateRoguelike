package gurbx.ultimateroguelike.factories;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import gurbx.ultimateroguelike.utils.Constants;

public class BodyBuilder {
	
	public static Body createBlockBody(float x, float y, int width, int height,  World world, Object object) {
        Body body;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(x/Constants.PPM, y/Constants.PPM);
        def.fixedRotation = true;
        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((float) width*0.5f / Constants.PPM, (float) height*0.5f / Constants.PPM);


        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 1.0f;

        body.createFixture(fixture).setUserData(object);
        shape.dispose();
        return body;
    }

    public static Body createDynamicBody(Vector2 position, float width, float height,
                                         World world) {
        Body body;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(position.x / Constants.PPM, position.y / Constants.PPM);
        def.fixedRotation = true;
        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2f/Constants.PPM, height/2f/Constants.PPM);

        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 1.0f;
        body.createFixture(fixture);

        shape.dispose();
        return body;
    }

}
