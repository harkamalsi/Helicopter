package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;

public class MenuState extends State {

    private Texture background;
    private TextureRegion heligo;
    private TextureRegion pong;
    private BitmapFont font;
    private Rectangle heli_bound;
    private Rectangle pong_bound;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT );
        background = new Texture("bg.png");
        heligo = new TextureRegion(new Texture("Heligo.png"));
        pong = new TextureRegion(new Texture("Pong.png"));
        heli_bound = new Rectangle(MyGdxGame.WIDTH/2 - heligo.getRegionWidth()/2 - 80, MyGdxGame.HEIGHT/2 - heligo.getRegionHeight()/2 - 150 , heligo.getRegionWidth() -10, heligo.getRegionHeight()-30);
        pong_bound = new Rectangle();


        pong_bound.x = MyGdxGame.WIDTH/2 - pong.getRegionWidth()/2;
        pong_bound.y = MyGdxGame.HEIGHT/2 - pong.getRegionHeight()/2 - 150;
        pong_bound.height = pong.getRegionHeight()/2;
        pong_bound.width = pong.getRegionWidth()/2;
        //pong = new TextureRegion(new Texture("Pong.png"));
        font = new BitmapFont();
        font.getData().setScale(2);
    }

    @Override
    protected void handleInput() {
        //if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1))

       // if(Gdx.input.justTouched() && heli_bound.contains(Gdx.input.getX(), Gdx.input.getY())){


          //  System.out.println(Gdx.input.getX());
           // System.out.println(Gdx.input.getY());
            //gsm.set(new PlayState(gsm));

      //  }
        //if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2))
    //  gsm.set(new PongState(gsm));
        //if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3))
           // gsm.set(new GameOver(gsm));
        gsm.set(new PongState(gsm));
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(heligo.getTexture(), MyGdxGame.WIDTH/2 - heligo.getRegionWidth()/2, MyGdxGame.HEIGHT/2 - heligo.getRegionHeight()/2, heligo.getRegionWidth(), heligo.getRegionHeight());
        sb.draw(pong.getTexture(), MyGdxGame.WIDTH/2 - pong.getRegionWidth()/2, MyGdxGame.HEIGHT/2 - pong.getRegionHeight()/2 - 150);

        font.draw(sb, "Press 1 for Helicopter Task", 10, MyGdxGame.HEIGHT - 50);
        font.draw(sb, "Press 1 for Helicopter Task", MyGdxGame.WIDTH/2 - heligo.getRegionWidth()/2, MyGdxGame.HEIGHT/2 - heligo.getRegionHeight()/2);

        font.draw(sb, "Press 2 for Pong Task", 10, MyGdxGame.HEIGHT - 100);
        sb.end();
    }

    @Override
    protected void dispose() {
        background.dispose();
    }

}
