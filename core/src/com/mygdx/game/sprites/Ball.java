package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball {

    private Texture ball;
    private Vector2 position, velocity, bounds;

    public Ball() {
        ball = new Texture("ball.png");

        position = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        velocity = new Vector2(0, 0);
        bounds = new Vector2(ball.getWidth(), ball.getHeight());
    }

    public void update() {
        setPosition(velocity);
        bounds.set(position);
    }

    public boolean collidesWithRoofBottom() {
        if (position.y > 0 && position.y < Gdx.graphics.getHeight()) return false;
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





}
