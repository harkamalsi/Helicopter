package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Ball;
import com.mygdx.game.sprites.Paddle;

public class PongState extends State {

    private Paddle player_paddle;
    private Paddle comp_paddle;
    private Ball ball;

    public PongState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        player_paddle = new Paddle(100, 400, 0);
        comp_paddle = new Paddle(700, 400, 5);
        ball = new Ball();
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
        ball.update();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sb.draw(comp_paddle.getTexture(),
                comp_paddle.getPosition().x,
                comp_paddle.getPosition().y);
        sb.draw(player_paddle.getTexture(),
                player_paddle.getPosition().x,
                player_paddle.getPosition().y);
        sb.draw(ball.getTexture(),
                ball.getPosition().x,
                ball.getPosition().y);
        sb.end();
    }

    @Override
    protected void dispose() {

    }
}
