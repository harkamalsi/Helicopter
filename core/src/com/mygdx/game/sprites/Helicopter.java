package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class Helicopter {

    private static final int MIN = -10;
    private static final int MAX = 10;

    private Vector2 velocity;

    private Vector2 mousePosition;

    private boolean changeDirection = false;

    //private static final int HORIZONTAL_MOVEMENT = getRandomMovement();
    //private static final int VERTICAL_MOVEMENT = getRandomMovement();

    private Vector2 position;

    private Rectangle roof;
    private Rectangle ground;
    private Rectangle leftWall;
    private Rectangle rightWall;

    private Rectangle heliBounds;

    private int scaleX = -1;

    private TextureRegion helicopter;
    private Animation heliAnimation;
    //private Texture helicopter;

    //private Animation helicopterAnimation;
    //private Texture texture;

    //.ogg format
    //private Sound blades;

    public Helicopter(int x, int y) {

        position = new Vector2(x, y);
        //velocity = new Vector2(10, 0);
        velocity = new Vector2(getRandomMovement(), getRandomMovement());
        //velocity = new Vector2(10,10);

        mousePosition = new Vector2(0, 0);

        roof = new Rectangle(0, MyGdxGame.HEIGHT, MyGdxGame.WIDTH, 10);
        ground = new Rectangle(0, 0, MyGdxGame.WIDTH, 10);
        leftWall = new Rectangle(0, 0, 10, MyGdxGame.HEIGHT);
        rightWall = new Rectangle(MyGdxGame.WIDTH, 0, 10, MyGdxGame.HEIGHT);

        //helicopter = new TextureRegion(new Texture("attackhelicopter.png"));
        //heliBounds = new Rectangle(x, y, helicopter.getRegionWidth(), helicopter.getRegionWidth());

        helicopter = new TextureRegion(new Texture("helianimation.jpeg"));
        heliAnimation = new Animation(new TextureRegion(helicopter), 4, 0.5f);

        heliBounds = new Rectangle(x, y, helicopter.getRegionWidth() / 4, helicopter.getRegionHeight());
        //helicopterAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        //blades = Gdx.audio.newSound(Gdx.files.internal("sfx_blades.ogg"));
    }

    private int getRandomMovement() {
        return MIN + (int)(Math.random() * ((MAX - MIN) + 1));
    }

    public void update(float dt) {
        heliAnimation.update(dt);

        //Need to check for the screens and return the heli in opposite direction

        /*if((position.y + helicopter.getHeight()) <= MyGdxGame.HEIGHT && !changeDirection ) {
            position.add(velocity.x, velocity.y);
            if(position.y + helicopter.getHeight() >= MyGdxGame.HEIGHT || position.y <= 0) {
                changeDirection = !changeDirection;
            }
            heliBounds.setPosition(position.x, position.y);
        } else if(changeDirection) {
            position.add(-velocity.x, -velocity.y);
            if((position.y + helicopter.getHeight()) >= MyGdxGame.HEIGHT || position.y <= 0){
                position.set(position.x, position.y - 10);
                changeDirection = !changeDirection;
            }
            heliBounds.setPosition(position.x, position.y);
        }*/

        mousePosition.set(Gdx.input.getX() - Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - Gdx.input.getY());

        float angle = MathUtils.radiansToDegrees * (MathUtils.atan2(mousePosition.y - (helicopter.getRegionHeight() / 4 + position.y),
                mousePosition.x - (helicopter.getRegionWidth() / 4 + position.x)));

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if(position.y >= 0) {
                if(collides() != -1) changeDirectionVelocity();

                position.add(velocity.x, velocity.y);
                heliBounds.setPosition(position.x, position.y);

                if(Gdx.input.isKeyPressed(Input.Keys.R)) {
                    velocity.set(MathUtils.random(-10, 10), MathUtils.random(-10, 10));
                }
            }
        } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            velocity.set(0,0);
            if(position.y >= 0) {
                // -1 = No collision
                if(collides() == -1) {
                    velocity.add(mapMouseToVelocity(mousePosition).x, mapMouseToVelocity(mousePosition).y);
                    position.add(velocity.x, velocity.y);
                    //Gdx.graphics.getHeight() - helicopter.getRegionHeight()
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
                    System.out.println("HeliB1: " + heliBounds);
                    //Not collision anymore
                    if(collides() == -1) {
                        position.add(mapMouseToVelocity(mousePosition).x, mapMouseToVelocity(mousePosition).y);
                        heliBounds.setPosition(position.x, position.y);
                    } else {
                        System.out.println("Pos: " + position);
                        heliBounds.setPosition(position.x, position.y);
                        System.out.println("HeliB2: " + heliBounds);
                    }


                    //position.set(position.x, position.y);
                }
            }
        }

        //System.out.println(velocity);
        //System.out.println(position);


        //position.add(MOVEMENT * dt, velocity.y, 0);
        //if (position.y < 0)
        //    position.y = 0;
        //velocity.scl(1/dt);



        //if(position.x > 0 && position.y > 0)
        //    velocity.add(MOVEMENT.x, MOVEMENT.y);
        //velocity.scl(dt);
        //position.add(velocity.x * dt, velocity.y * dt);
    }

    public Vector2 mapMouseToVelocity(Vector2 mousePosition) {
        Vector2 output = new Vector2(
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

        return output;
    }

    public Vector2 mapMouseToVelocity(Vector2 mousePosition, boolean collision) {
        if (collision) {
            //Made it little powerfull to get out of collision zone
            Vector2 output = new Vector2(
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
            return output;

        }

        return mapMouseToVelocity(mousePosition);
    }

    private float getMapping(float value, float A, float B, float a, float b) {
        return ((value - A) * (b - a) / (B - A)) + a;
    }

    public Vector2 getPosition() {return position;}

    //public TextureRegion getTexture() {return birdAnimation.getFrame();}

    public TextureRegion getTexture() {return heliAnimation.getFrame();}

    public void dispose() {
        //helicopter.dispose();
        //blades.dispose();
    }

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

    public int collides() {
        if (getRoof().overlaps(getHelicopterRectangle())) return 0;
        if (getGround().overlaps(getHelicopterRectangle())) return 1;
        if (getLeftWall().overlaps(getHelicopterRectangle())) return 2;
        if (getRightWall().overlaps(getHelicopterRectangle())) return 3;

        // Means not any collision
        return -1;
    }

    public void changeDirectionVelocity() {
        float prevVelocityX = velocity.x;
        float prevVelocityY = velocity.y;

        velocity.set(-prevVelocityX, -prevVelocityY);
        scaleX = scaleX * -1;

    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public int getScaleX() {
        return scaleX;
    }
}

