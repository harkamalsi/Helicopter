package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class MenuState extends State {

    private Texture background;
    private BitmapFont font;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT );
        background = new Texture("bg.png");
        font = new BitmapFont();
        font.getData().setScale(2);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1))
            gsm.set(new PlayState(gsm));
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2))
            gsm.set(new PongState(gsm));
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3))
            gsm.set(new GameOver(gsm));
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        font.draw(sb, "Press 1 for Helicopter Task", 10, MyGdxGame.HEIGHT - 50);
        font.draw(sb, "Press 2 for Pong Task", 10, MyGdxGame.HEIGHT - 100);
        sb.end();
    }

    @Override
    protected void dispose() {
        background.dispose();
    }

}
