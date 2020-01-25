package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Helicopter;

import java.awt.Rectangle;
import java.awt.Window;

public class PlayState extends State {
    private static final int HELICOPTER_COUNT = 1;
    private static final int MIN = 50;
    private static final int MAX = 450;

    private Texture background;
    private Helicopter helicopter;

    private Array<Helicopter> helicopters;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        //helicopter = new Helicopter(getRandomSpawn(), getRandomSpawn());
        //helicopter = new Helicopter(10,10);

        background = new Texture("bg.png");

        helicopters = new Array<Helicopter>();

        for (int i=1; i <= HELICOPTER_COUNT; i++){
            helicopters.add(new Helicopter(getRandomSpawn(), getRandomSpawn(), cam));
        }
    }

    private int getRandomSpawn() {
        return MIN + (int)(Math.random() * ((MAX - MIN) + 1));
    }

    @Override
    protected void handleInput() {
        //Helicopter(s) should start moving after game start. Random directions.
        if(Gdx.input.justTouched()){

            //For now dont need to do anything

            //startSingleHelicopter();
            //startMultipleHelicopters();
        }
    }

    //private void startSingleHelicopter() {
    //    helicopter.update();
    //}

    private void startMultipleHelicopters() {
        //for (int i=1; i <= helicopters.length ; i++){
        //    helicopters.get(i).start();
        //}
    }

    @Override
    protected void update(float dt) {
        handleInput();
        // helicopter.update(dt);

        for (int i=0; i <= helicopters.size-1 ; i++){
            helicopters.get(i).update(dt);
        }

        //cam.position.x = helicopter.getPosition().x + 80;
        //cam.update();
    }



    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);

        Vector2 originLine = new Vector2(MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        //float angle = (float) Math.atan2(helicopter.getVelocity().y, helicopter.getVelocity().x);
        //float angle = (float) (Math.atan2(helicopter.getVelocity().y, helicopter.getVelocity().x) -
        //        Math.atan2(originLine.x, originLine.y));

        /*float lineX = helicopter.getPosition().x; //- MyGdxGame.WIDTH / 2;
        float lineY = helicopter.getPosition().y; //- MyGdxGame.HEIGHT / 2;
        float tan = lineY/lineX;
        float angle = (float) Math.atan((double) tan);

        float degreeAngle = (float) Math.toDegrees(angle);*/

        // Following: SpriteBatch.draw(textureRegion, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
        //draw(Texture texture, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY,
        // float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY)

        /*sb.draw(helicopter.getTexture(),
                helicopter.getPosition().x,
                helicopter.getPosition().y,
                helicopter.getTexture().getRegionWidth() / 2,
                helicopter.getTexture().getRegionHeight() / 2,
                helicopter.getTexture().getRegionWidth(),
                helicopter.getTexture().getRegionHeight(),
                1,1,
                degreeAngle);*/

        /*sb.draw(helicopter.getTexture(),
                helicopter.getPosition().x,
                helicopter.getPosition().y,
                helicopter.getTexture().getRegionWidth() / 2,
                helicopter.getTexture().getRegionHeight() / 2,
                helicopter.getTexture().getRegionWidth(),
                helicopter.getTexture().getRegionHeight(),
                helicopter.getScale(),1,
                degreeAngle);*/


        //sb.draw(helicopter.getTexture(), helicopter.getPosition().x, helicopter.getPosition().y,MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2,
        //        helicopter.getTexture().getWidth(),helicopter.getTexture().getHeight(),(float) 1, (float) 1,angle, 0, 0, 0, 0, false, false);

        //sb.draw(helicopter.getTexture(), helicopter.getPosition().x, helicopter.getPosition().y);

        for (int i=0; i <= helicopters.size-1 ; i++){
            sb.draw(helicopters.get(i).getTexture(), helicopters.get(i).getPosition().x, helicopters.get(i).getPosition().y);
            sb.draw(helicopters.get(i).getTexture(),
                    helicopters.get(i).getPosition().x,
                    helicopters.get(i).getPosition().y,
                    helicopters.get(i).getTexture().getRegionWidth() / 2,
                    helicopters.get(i).getTexture().getRegionHeight() / 2,
                    helicopters.get(i).getTexture().getRegionWidth(),
                    helicopters.get(i).getTexture().getRegionHeight(),
                    helicopters.get(i).getScale(),
                    1,
                    0);
            //helicopters.get(i).getVelocity().angle()
        }

        sb.end();
    }

    @Override
    protected void dispose() {
        background.dispose();
        helicopter.dispose();

        //for (int i=1; i <= helicopters.length ; i++){
        //    helicopters.get(i).dispose();
        //}
    }
}
