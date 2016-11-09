package com.example.sava.simplegame;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by sava on 06.11.16.
 */

public class GameManager {
    private static final int MAX_CIRCLES = 10;
    private static int width, height;
    private CanvasView canvasView;
    private MainCircle m_main_circle;
    private ArrayList<EnemyCirecle> cirecles;
    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
    }
    private void initMainCircle() {
        m_main_circle = new MainCircle(width / 2, height / 2);
    }
    private void initEnemyCircle() {
        SimpleCircle main_circle_area = m_main_circle.getCircleArea();
        cirecles = new ArrayList<>();
        for (int i = 0; i < MAX_CIRCLES; ++i) {
            EnemyCirecle cirecle;
            do {
                cirecle = EnemyCirecle.getRandomCircle();
            } while (cirecle.isIntersect(main_circle_area));
            cirecles.add(cirecle);
        }
        calculateColor();
    }

    private void calculateColor() {
        for (EnemyCirecle cirecle: cirecles)
            cirecle.calculateType(m_main_circle);
    }

    public GameManager(CanvasView canvasView, int w, int h) {
        this.canvasView = canvasView;
        width = w;
        height = h;
        initMainCircle();
        initEnemyCircle();
    }
    public void onDraw() {
        canvasView.drawCircle(m_main_circle);
        for (EnemyCirecle cirecle: cirecles)
            canvasView.drawCircle(cirecle);
    }

    public void onTouchEvent(int x, int y) {
        m_main_circle.moveMainCircle(x, y);
        checkCollision();
        moveCircles();
    }

    private void checkCollision() {
        SimpleCircle must_be_killed = null;
        for (EnemyCirecle cirecle : cirecles)
            if (m_main_circle.isIntersect(cirecle)) {
                if (cirecle.getRadius() < m_main_circle.getRadius()) {
                    m_main_circle.growRadius(cirecle);
                    must_be_killed = cirecle;
                    calculateColor();
                    break;
                } else {
                    gameEnd("One more?");
                    return;
                }
            }
        if (must_be_killed != null)
            cirecles.remove(must_be_killed);
        if (cirecles.isEmpty())
            gameEnd("I will be back!");
    }

    private void gameEnd(String text) {
        canvasView.showMessage(text);
        m_main_circle.initRadius();
        initEnemyCircle();
        canvasView.redraw();
    }

    private void moveCircles() {
        for (EnemyCirecle cirecle : cirecles)
            cirecle.moveOneStep();
    }
}
