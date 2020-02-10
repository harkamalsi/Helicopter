package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class MenuState extends State {

    private Texture background;
    private TextureRegion heligo;
    private TextureRegion pong;
    private BitmapFont font;
    private Rectangle heli_bound;
    private Rectangle pong_bound;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
        background = new Texture("bg.png");
        heligo = new TextureRegion(new Texture("Heligo.png"));
        pong = new TextureRegion(new Texture("Pong.png"));
        heli_bound = new Rectangle(Gdx.graphics.getWidth()/2 - heligo.getRegionWidth()/2, Gdx.graphics.getHeight()/2 - heligo.getRegionHeight()/2, heligo.getRegionWidth(), heligo.getRegionHeight());
        pong_bound = new Rectangle(Gdx.graphics.getWidth()/2 - pong.getRegionWidth()/2, Gdx.graphics.getHeight()/2 - pong.getRegionHeight()/2 +150, pong.getRegionWidth(), pong.getRegionHeight());

        font = new BitmapFont();
        font.getData().setScale(2);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched() && heli_bound.contains(Gdx.input.getX(), Gdx.input.getY())){
            System.out.println(Gdx.input.getX());
            System.out.println(Gdx.input.getY());
            gsm.set(new PlayState(gsm));
        }
        if(Gdx.input.justTouched() && pong_bound.contains(Gdx.input.getX(), Gdx.input.getY()))
            gsm.set(new PongSingleState(gsm));
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3))
            gsm.set(new GameOverState(gsm));
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(heligo.getTexture(), Gdx.graphics.getWidth()/2 - heligo.getRegionWidth()/2, Gdx.graphics.getHeight()/2 - heligo.getRegionHeight()/2, heligo.getRegionWidth(), heligo.getRegionHeight());
        sb.draw(pong.getTexture(), Gdx.graphics.getWidth()/2 - pong.getRegionWidth()/2, Gdx.graphics.getHeight()/2 - pong.getRegionHeight()/2 - 150);
        sb.end();
    }

    @Override
    protected void dispose() {
        background.dispose();
    }

}
