package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Ball;
import com.mygdx.game.sprites.Paddle;

import java.awt.Rectangle;

public class PongState extends State {

    private Paddle player_paddle;
    private Paddle comp_paddle;
    private Ball ball;

    private BitmapFont font;

    public PongState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        player_paddle = new Paddle(true);
        comp_paddle = new Paddle(false);
        ball = new Ball();
        comp_paddle.comp_paddle_move();

        font = new BitmapFont();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            gsm.set(new PongState(gsm));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player_paddle.playerMoveUp();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            player_paddle.playerMoveDown();
        }


    }

    @Override
    protected void update(float dt) {
        handleInput();

        if (collidesWithBall(player_paddle)) {
            ball.changeDirectionPlayer(player_paddle.getPosition().y, player_paddle.getBounds().getHeight());
        }
        if(collidesWithBall(comp_paddle)) {
            ball.changeDirectionComp(comp_paddle.getPosition().y, comp_paddle.getBounds().getHeight());

        }

        ball.update();
        comp_paddle.update();
        player_paddle.update();


    }

    private boolean collidesWithBall(Paddle p) {
        //System.out.println(p.getBounds().overlaps(ball.getBounds()));
        return p.getBounds().overlaps(ball.getBounds());
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

        font.draw(sb, "Press R for reset", 10, MyGdxGame.HEIGHT - 100);

        sb.end();
    }

    @Override
    protected void dispose() {

    }
}
