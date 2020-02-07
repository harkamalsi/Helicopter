package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class SingleBall {


    private Texture ball = new Texture("ball.png");
    private Vector2 position = new Vector2(MyGdxGame.WIDTH / 2 , MyGdxGame.HEIGHT / 2);
    private Vector2 velocity = new Vector2(5, (float)0);
    private Rectangle bounds =  new Rectangle(position.x,position.y,ball.getWidth(), ball.getHeight());

    protected SingleBall() {}

        public void update() {
            if (!collidesWithRoofOrGround()) {
                setPosition(velocity);
                updateBounds();
            } else {
                changeDirectionRoof();
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

        public void changeDirectionPlayer(float player, float height) {
            setVelocity(new Vector2((-velocity.x * (float)1.1), -(velocity.y * (float)(0.1 * (player - getPosition().y + (height / 2))) + (float)(0.1 * (player - getPosition().y + (height / 2))))));
            //velocity.add(1,(float)1.1);
        }

        public void changeDirectionComp(float comp, float height) {
            setVelocity(new Vector2((-velocity.x * (float)1.1), -(velocity.y * (float)(0.1 * (comp - getPosition().y + (height / 2))) + (float)(0.1 * (comp - getPosition().y + (height / 2))))));
            //velocity.add(1,(float)1.1);
        }

        public void changeDirectionRoof() {
            setVelocity(new Vector2((velocity.x * (float)1.1), -(velocity.y )));
            //velocity.add(1,(float)1.1);
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

        public void reset(boolean won) {
            position = new Vector2(MyGdxGame.WIDTH / 2 , MyGdxGame.HEIGHT / 2);

            if(won) {
                velocity = new Vector2(-(-5), (float)0);
            }
            else {
                velocity = new Vector2(-5, (float)0);
            }

            bounds = new Rectangle(position.x,position.y,ball.getWidth(), ball.getHeight());
        }

        public void dispose() {
            instance = null;
            ball.dispose();
        }

        public static SingleBall getInstance(){
            if(instance == null){
                instance = new SingleBall();
            }
            return instance;
        }
        private static SingleBall instance = null;


}
