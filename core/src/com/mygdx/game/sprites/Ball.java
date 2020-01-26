package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class Ball {

    private Texture ball;
    private Vector2 position, velocity;
    private Rectangle bounds;

    public Ball() {
        ball = new Texture("ball.png");

        position = new Vector2(MyGdxGame.WIDTH / 2 - ball.getWidth() / 2, MyGdxGame.HEIGHT / 2);
        velocity = new Vector2(-5, 0);
        bounds = new Rectangle(position.x,position.y,ball.getWidth(), ball.getHeight());
    }

    public void update() {
        if (!collidesWithRoofOrGround()) {
            setPosition(velocity);
            updateBounds();
        } else {
            changeDirection();
            setPosition(velocity);
            updateBounds();
        }
    }

    public boolean collidesWithRoofOrGround() {
        if (position.y > 0 && position.y < MyGdxGame.HEIGHT) return false;
        return true;
    }

    public void resetPosition() {
        position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }

    public void setPosition(Vector2 newPosition) {
        position.add(newPosition);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void changeDirection() {
        setVelocity(new Vector2(-velocity.x, -velocity.y));
    }

    public void changeDirection(Vector2 paddleVelocity) {
        setVelocity(new Vector2(-velocity.x, -velocity.y));
    }

    private void setVelocity(Vector2 v) {
        velocity.set(v);
    }

    public void changeVelocity(Vector2 v) {
        velocity.add(v);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Texture getTexture() {
        return ball;
    }

    private void updateBounds() {
        bounds.setPosition(position);
    }

    public Rectangle getBounds() {
        return bounds;
    }



}
