package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class Helicopter {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 mousePosition;

    private Rectangle roof;
    private Rectangle ground;
    private Rectangle leftWall;
    private Rectangle rightWall;
    private Rectangle heliBounds;
    private boolean button = false;

    private int scaleX = 1;

    private TextureRegion helicopter;
    private Animation heliAnimation;

    public Helicopter(int x, int y) {

        position = new Vector2(x, y);
        velocity = new Vector2(MathUtils.random(-5, 5), MathUtils.random(-5, 5));

        mousePosition = new Vector2(0, 0);

        roof = new Rectangle(0, MyGdxGame.HEIGHT, MyGdxGame.WIDTH, 10);
        ground = new Rectangle(0, 0, MyGdxGame.WIDTH, 10);
        leftWall = new Rectangle(0, 0, 10, MyGdxGame.HEIGHT);
        rightWall = new Rectangle(MyGdxGame.WIDTH, 0, 10, MyGdxGame.HEIGHT);

        helicopter = new TextureRegion(new Texture("helianimation.jpeg"));
        heliAnimation = new Animation(new TextureRegion(helicopter), 4, 0.1f);
        heliBounds = new Rectangle(x, y, helicopter.getRegionWidth() / 4, helicopter.getRegionHeight());
    }

    public void update(float dt) {
        heliAnimation.update(dt);

        mousePosition.set(Gdx.input.getX() - Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - Gdx.input.getY());

        //Following checks if random movement or mouse movement should be used
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !button) {
            if(position.y >= 0) {
                if(collides()) changeDirectionVelocity();

                position.add(velocity.x, velocity.y);
                heliBounds.setPosition(position.x, position.y);

                //Resets the movement (velocity)
                if(Gdx.input.isKeyPressed(Input.Keys.R)) {
                    velocity.set(MathUtils.random(-10, 10), MathUtils.random(-10, 10));
                }

                if (velocity.x >= 0) {
                    scaleX = -1;
                } else {
                    scaleX = 1;
                }
            }
        }
        else if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && button) {
            velocity.set(0,0);
            if(position.y >= 0 && !collides()) {
                velocity.add(mapMouseToVelocity(mousePosition).x, mapMouseToVelocity(mousePosition).y);
                position.add(velocity.x, velocity.y);
                heliBounds.setPosition(position.x, position.y);

                if (velocity.x >= 0) {
                    scaleX = -1;
                } else {
                    scaleX = 1;
                }

            } else {
                velocity.add(mapMouseToVelocity(mousePosition, true).x,
                        mapMouseToVelocity(mousePosition, true).y);
                heliBounds.setPosition(position.x + velocity.x,
                        position.y + velocity.y);

                if(!collides()) {
                    position.add(mapMouseToVelocity(mousePosition).x, mapMouseToVelocity(mousePosition).y);
                    heliBounds.setPosition(position.x, position.y);
                } else {
                    heliBounds.setPosition(position.x, position.y);
                }
            }
        }
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.getX() < Gdx.graphics.getWidth()/10 && Gdx.input.getX() < Gdx.graphics.getHeight()/10) {
            System.out.println(Gdx.graphics.getHeight());

            if(!button) {
                button = true;
            }
            else if(button) {
                button = false;
            }
        }
    }

    public Vector2 mapMouseToVelocity(Vector2 mousePosition) {
        return new Vector2(
                getMapping(mousePosition.x,
                        -Gdx.graphics.getWidth() / 2,
                        Gdx.graphics.getWidth() / 2,
                        -10,
                        10),
                getMapping(mousePosition.y,
                        -Gdx.graphics.getHeight() / 2,
                        Gdx.graphics.getHeight() / 2,
                        -10,
                        10));
    }

    public Vector2 mapMouseToVelocity(Vector2 mousePosition, boolean collision) {
        //Gives helicopter a little boost to get out of collision zone

        if (collision) {
            return new Vector2(
                    getMapping(mousePosition.x,
                            -Gdx.graphics.getWidth() / 2,
                            Gdx.graphics.getWidth() / 2,
                            -30,
                            30),
                    getMapping(mousePosition.y,
                            -Gdx.graphics.getHeight() / 2,
                            Gdx.graphics.getHeight() / 2,
                            -30,
                            30));
        }

        return mapMouseToVelocity(mousePosition);
    }

    private float getMapping(float value, float A, float B, float a, float b) {
        return ((value - A) * (b - a) / (B - A)) + a;
    }

    public Vector2 getPosition() {return position;}

    public TextureRegion getTexture() {return heliAnimation.getFrame();}

    public Rectangle getRoof() {
        return roof;
    }

    public Rectangle getGround() {
        return ground;
    }

    public Rectangle getLeftWall() {
        return leftWall;
    }

    public Rectangle getRightWall() {
        return rightWall;
    }

    public Rectangle getHelicopterRectangle() {
        return heliBounds;
    }

    public boolean collides() {
        if (getRoof().overlaps(getHelicopterRectangle())) return true;
        if (getGround().overlaps(getHelicopterRectangle())) return true;
        if (getLeftWall().overlaps(getHelicopterRectangle())) return true;
        if (getRightWall().overlaps(getHelicopterRectangle())) return true;

        return false;
    }

    public void changeDirectionVelocity() {
        velocity.set(-velocity.x, -velocity.y);
        scaleX = -scaleX;
    }

    public void dispose() {

    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public int getScaleX() {
        return scaleX;
    }
}

