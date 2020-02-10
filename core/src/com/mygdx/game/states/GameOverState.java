package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class GameOverState extends State{

    private Texture background;
    private BitmapFont font;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        background = new Texture("game_over.jpg");
        font = new BitmapFont();
    }

    @Override
    protected void handleInput() {
      
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            gsm.set(new MenuState(gsm));
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
            gsm.set(new PongMultiState(gsm));
        }
        if(Gdx.input.justTouched()){
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
        font.draw(sb, "Press Q for menu", 10, MyGdxGame.HEIGHT - 100);
        sb.draw(background, MyGdxGame.WIDTH / 2 - background.getWidth() / 2, MyGdxGame.HEIGHT / 2 - background.getHeight() / 2);
        sb.end();
    }

    @Override
    protected void dispose() {
        background.dispose();
    }
}
