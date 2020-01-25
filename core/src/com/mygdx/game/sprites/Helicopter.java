package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class Helicopter {

    private static final int MIN = 5;
    private static final int MAX = 10;

    private OrthographicCamera cam;

    private Vector2 velocity;

    private Vector3 mousePosition;

    private boolean changeDirection = false;

    //private static final int HORIZONTAL_MOVEMENT = getRandomMovement();
    //private static final int VERTICAL_MOVEMENT = getRandomMovement();

    private Vector2 position;

    private Rectangle roof;
    private Rectangle ground;
    private Rectangle leftWall;
    private Rectangle rightWall;

    private Rectangle heliBounds;

    private int scale = -1;

    private TextureRegion helicopter;
    //private Texture helicopter;

    //private Animation helicopterAnimation;
    //private Texture texture;

    //.ogg format
    //private Sound blades;

    public Helicopter(int x, int y, OrthographicCamera cam) {

        this.cam = cam;

        position = new Vector2(x, y);
        //velocity = new Vector2(10, 0);
        velocity = new Vector2(getRandomMovement(), getRandomMovement());

        mousePosition = new Vector3(0, 0, 0);

        roof = new Rectangle(0, MyGdxGame.HEIGHT, MyGdxGame.WIDTH, 10);
        ground = new Rectangle(0, 0, MyGdxGame.WIDTH, 10);
        leftWall = new Rectangle(0, 0, 10, MyGdxGame.HEIGHT);
        rightWall = new Rectangle(MyGdxGame.WIDTH, 0, 10, MyGdxGame.HEIGHT);

        //helicopter = new TextureRegion(new Texture("attackhelicopter.png"));
        //heliBounds = new Rectangle(x, y, helicopter.getRegionWidth(), helicopter.getRegionWidth());

        helicopter = new TextureRegion(new Texture("attackhelicopter.png"));
        heliBounds = new Rectangle(x, y, helicopter.getRegionWidth(), helicopter.getRegionHeight());
        //helicopterAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        //blades = Gdx.audio.newSound(Gdx.files.internal("sfx_blades.ogg"));
    }

    private int getRandomMovement() {
        return MIN + (int)(Math.random() * ((MAX - MIN) + 1));
    }

    public void update(float dt) {
        //helicopterAnimation.update(dt);

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

        mousePosition.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);



        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if(position.y >= 0) {
                if(collides() != -1) changeDirectionVelocity();

                position.add(velocity.x, velocity.y);
                heliBounds.setPosition(position.x, position.y);

            }
        } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            if(position.y >= 0) {
                if(collides() == -1) {
                    position.add(mousePosition.x,
                            Gdx.graphics.getHeight() - helicopter.getRegionHeight());
                    heliBounds.setPosition(position.x, position.y);
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

    public Vector2 getPosition() {return position;}

    //public TextureRegion getTexture() {return birdAnimation.getFrame();}

    public TextureRegion getTexture() {return helicopter;}

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
        scale = scale * -1;

    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public int getScale() {
        return scale;
    }
}

