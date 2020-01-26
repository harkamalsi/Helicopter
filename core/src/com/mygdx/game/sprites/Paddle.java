package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class Paddle {

    private Vector2 velocity, position;
    private Rectangle bounds;
    private int points;


    private Texture paddle;


    public Paddle(boolean left) {
        paddle = new Texture("paddle.png");

        if (left) {
            position = new Vector2(100, (MyGdxGame.HEIGHT / 2 - paddle.getHeight() / 2));
        } else {
            position = new Vector2(MyGdxGame.WIDTH - 100, MyGdxGame.HEIGHT / 2 - (paddle.getHeight() / 2));
        }

        velocity = new Vector2(0, 0);
        bounds = new Rectangle(position.x,position.y,paddle.getWidth(), paddle.getHeight());
    }

    public int getPoints() {
        return points;
    }

    public void givePoint() {
        this.points += 1;
    }

    public String toString() {
        return ": " + points;
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

    public void setPosition(Vector2 newPosition) {
        position.add(newPosition);
    }

    public Vector2 getPosition() {return position;}

    public Texture getTexture() {return paddle;}

    public boolean collidesWithRoofOrGround() {
        if (position.y > 0 && position.y + getBounds().getHeight() < MyGdxGame.HEIGHT) return false;
        return true;
    }

    public void changeDirection() {
        setVelocity(new Vector2(-velocity.x, -velocity.y));
    }

    private void setVelocity(Vector2 v) {
        velocity.set(v);
    }

    private void updateBounds() {
        bounds.setPosition(position);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void player1MoveUp() {
        velocity.set(0, 5);
    }

    public void player1MoveDown() {
        velocity.set(0, -5);
    }

    public void player2MoveUp() {
        velocity.set(0, 5);
    }

    public void player2MoveDown() {
        velocity.set(0, -5);
    }

    public void stop() {
        velocity.set(0, 0);
    }

    public void reset(boolean left) {
        if (left) {
            position = new Vector2(100, (MyGdxGame.HEIGHT / 2 - paddle.getHeight() / 2));
        } else {
            position = new Vector2(MyGdxGame.WIDTH - 100, MyGdxGame.HEIGHT / 2 - (paddle.getHeight() / 2));
        }

        velocity = new Vector2(0, 0);
        bounds = new Rectangle(position.x,position.y,paddle.getWidth(), paddle.getHeight());
    }



}
