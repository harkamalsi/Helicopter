package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Helicopter;


public class PlayState extends State {
    private static final int HELICOPTER_COUNT = 3;

    private Texture background;
    private Array<Helicopter> helicopters;
    private BitmapFont font;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("bg.png");
        helicopters = new Array<Helicopter>();

        font = new BitmapFont();
        font.getData().setScale(1.5f);

        for (int i=1; i <= HELICOPTER_COUNT; i++){
            helicopters.add(new Helicopter(MathUtils.random(50, (int) MyGdxGame.WIDTH / 4)*i,
                    MathUtils.random(200, (int) MyGdxGame.HEIGHT / 4)*i));
        }
    }

    @Override
    protected void handleInput() {
        //Go back to menu
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();

        //Collision checking and direction change if collision
        for (int i=0; i <= helicopters.size - 1 ; i++){
            helicopters.get(i).update(dt);

            for (int j = 0; j <= helicopters.size - 1; j++) {
                if (i != j && j < helicopters.size - 1 && collides(helicopters.get(i), helicopters.get(j))) {
                    helicopters.get(i).changeDirectionVelocity();
                    helicopters.get(i).update(dt);

                    helicopters.get(j).changeDirectionVelocity();
                    helicopters.get(j).update(dt);
                }
            }
        }

    }

    private boolean collides(Helicopter h1, Helicopter h2) {
        return h1.getHelicopterRectangle().overlaps(h2.getHelicopterRectangle());
    }


    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        for (int i=0; i <= helicopters.size-1 ; i++){
            sb.draw(helicopters.get(i).getTexture(),
                    helicopters.get(i).getPosition().x,
                    helicopters.get(i).getPosition().y,
                    helicopters.get(i).getTexture().getRegionWidth() / 2,
                    helicopters.get(i).getTexture().getRegionHeight() / 2,
                    helicopters.get(i).getTexture().getRegionWidth(),
                    helicopters.get(i).getTexture().getRegionHeight(),
                    helicopters.get(i).getScaleX(),
                   1,
                    0);
        }
            font.draw(sb, "Press and hold LEFT MOUSE for random movement", 10, MyGdxGame.HEIGHT - 50);
            font.draw(sb, "Press and hold RIGHT MOUSE to control the movement with mouse", 10, MyGdxGame.HEIGHT - 80);
            font.draw(sb, helicopters.get(0).getPosition().toString(), 10, MyGdxGame.HEIGHT - 110);
            font.draw(sb, "Press R for reset movement", 10, MyGdxGame.HEIGHT - 140);
            font.draw(sb, "Press Q for menu", 10, MyGdxGame.HEIGHT - 170);

        sb.end();
    }

    @Override
    protected void dispose() {
        background.dispose();
        for (int i=0; i <= helicopters.size - 1; i++){
            helicopters.get(i).dispose();
        }
    }
}
