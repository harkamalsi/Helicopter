package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Rectangle;

public class Wall extends RectangleShapeGenerator {

    public Wall(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public boolean collides(Rectangle r) {
        return getRectangle().overlaps(r);
    }

    @Override
    public Rectangle getRectangle() {
        return super.getRectangle();
    }
}
