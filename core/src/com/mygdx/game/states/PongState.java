package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Paddle;

public class PongState extends State {

    private Paddle player_paddle;
    private  Paddle comp_paddle;

    public PongState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        player_paddle = new Paddle(100, 400, 0);
        comp_paddle = new Paddle(700, 400, 5);

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {

        }
    }

    @Override
    protected void update(float dt) {
        handleInput();

        comp_paddle.update(dt);
        player_paddle.update(dt);
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(comp_paddle.getTexture(),
                comp_paddle.getPosition().x,
                comp_paddle.getPosition().y);
        sb.draw(player_paddle.getTexture(),
                player_paddle.getPosition().x,
                player_paddle.getPosition().y);

        sb.end();
    }

    @Override
    protected void dispose() {

    }
}
