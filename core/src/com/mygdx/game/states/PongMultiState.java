package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Paddle;
import com.mygdx.game.sprites.SingleBall;

public class PongState extends State {

    private Paddle player1_paddle;
    private Paddle player2_paddle;
    //private Ball ball;
    private SingleBall ball;

    private BitmapFont font;

    public PongState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        player1_paddle = new Paddle(true);
        player2_paddle = new Paddle(false);
        //ball = new Ball();
        ball = SingleBall.getInstance();

        font = new BitmapFont();
        font.getData().setScale(1.5f);
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

        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            gsm.set(new MenuState(gsm));
        }

        //if(Gdx.input.justTouched()) {
          //  final Rectangle touch = new Rectangle(Gdx.input.getX(), 10, 1, 1);
       // }
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getY() < Gdx.graphics.getHeight()/2 && Gdx.input.getX() < Gdx.graphics.getWidth()/2) {
            System.out.println("UP:");
            System.out.println("Height touched: " + Gdx.input.getY());
            System.out.println("Width touched: " + Gdx.input.getX());
            System.out.print("Middle: " +  Gdx.graphics.getHeight()/2);
            System.out.println("");

            //System.out.println("Hello");
            player1_paddle.player1MoveUp();

        }
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getY() > Gdx.graphics.getHeight()/2 && Gdx.input.getX() < Gdx.graphics.getWidth()/2) {
            System.out.print(Gdx.input.getY());
            System.out.println("DOWN:");
            System.out.println("Height touched: " + Gdx.input.getY());
            System.out.println("Width touched: " + Gdx.input.getX());
            System.out.print("Middle: " +  Gdx.graphics.getHeight()/2);
            System.out.println("");

            player1_paddle.player1MoveDown();

        }

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getY() < Gdx.graphics.getHeight()/2 && Gdx.input.getX() > Gdx.graphics.getWidth()/2) {
            System.out.println("UP:");
            System.out.println("Height touched: " + Gdx.input.getY());
            System.out.println("Width touched: " + Gdx.input.getX());
            System.out.print("Middle: " +  Gdx.graphics.getHeight()/2);
            System.out.println("");

            //System.out.println("Hello");
            player2_paddle.player2MoveUp();

        }
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getY() > Gdx.graphics.getHeight()/2 && Gdx.input.getX() > Gdx.graphics.getWidth()/2) {
            System.out.print(Gdx.input.getY());
            System.out.println("DOWN:");
            System.out.println("Height touched: " + Gdx.input.getY());
            System.out.println("Width touched: " + Gdx.input.getX());
            System.out.print("Middle: " +  Gdx.graphics.getHeight()/2);
            System.out.println("");

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
            ball.changeDirectionPlayer(player2_paddle.getPosition().y, player2_paddle.getBounds().getHeight());

        }
        if(ball.getPosition().x < 50) {
            player1_paddle.reset(true);
            player2_paddle.reset(false);
            player2_paddle.givePoint();
            ball.reset(false);
        }

        if(ball.getPosition().x > 750) {
            player1_paddle.reset(true);
            player2_paddle.reset(false);
            player1_paddle.givePoint();
            ball.reset(true);
        }

        if(player1_paddle.getPoints() >= 21 || player2_paddle.getPoints() >= 21) {
            gsm.set(new GameOverState(gsm));
        }

        ball.update();
        player2_paddle.update();
        player1_paddle.update();

    }

    private boolean collidesWithBall(Paddle p) {
        return p.getBounds().overlaps(ball.getBounds());
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);

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

        font.draw(sb, "Player1" + player1_paddle.toString(), 30, MyGdxGame.HEIGHT - 180 );
        font.draw(sb, "Player2" + player2_paddle.toString(), 680, MyGdxGame.HEIGHT - 180 );
        font.draw(sb, "Press Q for menu", 10, MyGdxGame.HEIGHT - 130);
        font.draw(sb, "Press R for reset", 10, MyGdxGame.HEIGHT - 100);
        font.draw(sb, "Control Right Player with Arrow UP and Arrow DOWN", 10, MyGdxGame.HEIGHT - 70);
        font.draw(sb, "Control Left Player with W and S", 10, MyGdxGame.HEIGHT - 40);

        sb.end();
    }

    @Override
    protected void dispose() {
        player1_paddle.dispose();
        player2_paddle.dispose();
        ball.dispose();
    }
}
