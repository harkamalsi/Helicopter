package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.MyGdxGame;

public class GameOver extends State{

    private Texture background;

    public GameOver(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("game_over.jpg");


    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
            gsm.set(new PongState(gsm));
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            gsm.set(new MenuState(gsm));
        }

    }

    @Override
    protected void update(float dt) {
        handleInput();

    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, MyGdxGame.WIDTH / 2 - background.getWidth() / 2, MyGdxGame.HEIGHT / 2 - background.getHeight() / 2);
        sb.end();

    }

    @Override
    protected void dispose() {

    }
}
