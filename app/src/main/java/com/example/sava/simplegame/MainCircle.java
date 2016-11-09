package com.example.sava.simplegame;

import android.graphics.Color;

/**
 * Created by sava on 06.11.16.
 */

public class MainCircle extends SimpleCircle {
    private static final int INIT_RADIUS = 50;
    private static final int MAIN_SPEED = 50;
    private static final int OUR_COLOR = Color.BLUE;

    public MainCircle(int x, int y){
        super(x, y, INIT_RADIUS);
        radius = INIT_RADIUS;
        setColor(OUR_COLOR);
    }

    public int getRadius() {
        return radius;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void moveMainCircle(int x1, int y1) {
        int dx = (x1 - x) * MAIN_SPEED / GameManager.getWidth();
        int dy = (y1 - y) * MAIN_SPEED / GameManager.getHeight();
        x += dx;
        y += dy;
    }

    public SimpleCircle getCircleArea() {
        return new SimpleCircle(x, y, radius * 3);
    }

    public void initRadius() {
        this.radius = INIT_RADIUS;
    }

    public void growRadius(SimpleCircle circle) {
        radius = (int) Math.sqrt(Math.pow(radius, 2) + Math.pow(circle.radius, 2));
    }
}
