package com.example.sava.simplegame;

/**
 * Created by sava on 06.11.16.
 */

public interface ICanvasView {
    void drawCircle(SimpleCircle circle);
    void redraw();
    void showMessage(String text);
}
