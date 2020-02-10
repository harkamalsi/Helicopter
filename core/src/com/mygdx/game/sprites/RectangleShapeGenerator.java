package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Rectangle;

public abstract class RectangleShapeGenerator extends Rectangle {

    private Rectangle rectangle;

    public RectangleShapeGenerator(float x, float y, float width, float height) {
        rectangle = new Rectangle(x, y, width, height);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setParameters(float x, float y, float width, float height) {
        rectangle.set(x, y, width, height);
    }

    public void changePosition(float x, float y) {
        rectangle.setPosition(x, y);
    }

    public void setMeasures(float width, float height) {
        setParameters(rectangle.getX(), rectangle.getY(), width, height);
    }
}
