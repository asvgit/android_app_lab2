package com.example.sava.simplegame;

/**
 * Created by sava on 06.11.16.
 */
public class SimpleCircle {
    protected int x, y, radius;
    private int color;

    public SimpleCircle(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isIntersect(SimpleCircle circle) {
        return radius + circle.radius >= Math.sqrt(Math.pow(x - circle.getX(),2) + Math.pow(y - circle.getY(),2));
    }
}
