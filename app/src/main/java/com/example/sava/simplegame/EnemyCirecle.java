package com.example.sava.simplegame;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by sava on 06.11.16.
 */

public class EnemyCirecle extends SimpleCircle {
    private static final int FROM_RADIUS = 10;
    private static final int TO_RADIUS = 110;
    private static final int ENEMY_COLOR = Color.RED;
    private static final int FOOD_COLOR = Color.GREEN;
    private static final int RANDOM_SPEED = 10;
    private int dx, dy;

    public EnemyCirecle(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }

    public static EnemyCirecle getRandomCircle() {
        Random random = new Random();
        int x = random.nextInt(GameManager.getWidth());
        int y = random.nextInt(GameManager.getHeight());
        int dx = 1 + random.nextInt(RANDOM_SPEED);
        int dy = 1 + random.nextInt(RANDOM_SPEED);
        int radius = FROM_RADIUS + random.nextInt(TO_RADIUS - FROM_RADIUS);
        EnemyCirecle enemyCirecle = new EnemyCirecle(x, y, radius, dx, dy);
        enemyCirecle.setColor(ENEMY_COLOR);
        return enemyCirecle;
    }

    public void calculateType(MainCircle mainCircle) {
        if (radius < mainCircle.getRadius())
            setColor(FOOD_COLOR);
        else
            setColor(ENEMY_COLOR);
    }

    public void moveOneStep() {
        x += dx;
        y += dy;
        if (x > GameManager.getWidth() || x < 0)
            dx = -dx;
        if (y > GameManager.getHeight() || y < 0)
            dy = -dy;
    }
}
