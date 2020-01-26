package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Ball;
import com.mygdx.game.sprites.Paddle;

import java.awt.Rectangle;

public class PongState extends State {

    private Paddle player1_paddle;
    private Paddle player2_paddle;
    private Ball ball;

    private BitmapFont font;

    public PongState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        player1_paddle = new Paddle(true);
        player2_paddle = new Paddle(false);
        ball = new Ball();


        font = new BitmapFont();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            gsm.set(new PongState(gsm));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player1_paddle.player1MoveUp();
        }
        else{
            player1_paddle.stop();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player1_paddle.player1MoveDown();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player2_paddle.player2MoveUp();
        }
        else{
            player2_paddle.stop();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player2_paddle.player2MoveDown();
        }



    }

    @Override
    protected void update(float dt) {
        handleInput();

        if (collidesWithBall(player1_paddle)) {
            ball.changeDirectionPlayer(player1_paddle.getPosition().y, player1_paddle.getBounds().getHeight());
        }
        if(collidesWithBall(player2_paddle)) {
            ball.changeDirectionComp(player2_paddle.getPosition().y, player2_paddle.getBounds().getHeight());

        }
        if(ball.getPosition().x < 50) {
            player1_paddle.reset(true);
            player2_paddle.reset(false);
            player2_paddle.givePoint();
            ball.reset(false);
            System.out.println("player2:" + player2_paddle.getPoints());
        }

        if(ball.getPosition().x > 750) {
            player1_paddle.reset(true);
            player2_paddle.reset(false);
            player1_paddle.givePoint();
            ball.reset(true);
            System.out.println("player1:" + player1_paddle.getPoints());
        }

        if(player1_paddle.getPoints() >= 3 || player2_paddle.getPoints() >= 3) {
            gsm.set(new PongState(gsm));
        }

        ball.update();
        player2_paddle.update();
        player1_paddle.update();


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
        sb.draw(player2_paddle.getTexture(),
                player2_paddle.getPosition().x,
                player2_paddle.getPosition().y);
        sb.draw(player1_paddle.getTexture(),
                player1_paddle.getPosition().x,
                player1_paddle.getPosition().y);
        sb.draw(ball.getTexture(),
                ball.getPosition().x,
                ball.getPosition().y);

        font.draw(sb, "Press R for reset", 10, MyGdxGame.HEIGHT - 100);
        font.draw(sb, "Player1" + player1_paddle.toString(), 30, MyGdxGame.HEIGHT - 150 );
        font.draw(sb, "Player2" + player2_paddle.toString(), 700, MyGdxGame.HEIGHT - 150 );

        sb.end();
    }

    @Override
    protected void dispose() {

    }
}
