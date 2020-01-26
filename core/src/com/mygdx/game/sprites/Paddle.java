package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class Paddle {

    private Vector2 velocity, position;
    private Rectangle bounds;

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
        if (position.y > 0 && position.y < MyGdxGame.HEIGHT) return false;
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

    public void playerMoveUp() {
        velocity.set(0, 5);
    }

    public void playerMoveDown() {
        velocity.set(0, -5);
    }

    public void comp_paddle_move() {
        velocity.set(0, -7);
    }

}
