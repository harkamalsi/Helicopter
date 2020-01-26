package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Paddle {

    private Vector2 velocity;
    private  Vector2 position;

    private TextureRegion paddle;


    public Paddle(int x, int y, int speed) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, speed);
        paddle = new TextureRegion(new Texture("paddle.png"));
    }

    public void update(float dt) {
        position.add(velocity.x, velocity.y);
    }

    public Vector2 getPosition() {return position;}

    public TextureRegion getTexture() {return paddle;}




}
