package com.example.sava.simplegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by sava on 06.11.16.
 */

public class CanvasView extends View implements ICanvasView {
    private static int width;
    private static int height;
    private GameManager m_game_manager;
    private Canvas canvas;
    private Paint m_paint;
    private Toast toast;
    private void initPaint() {
        m_paint = new Paint();
        m_paint.setAntiAlias(true);
        m_paint.setStyle(Paint.Style.FILL);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initWidthAndHeight(context);
        m_game_manager = new GameManager(this, width, height);
    }
    private void initWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        width = point.x;
        height = point.y;
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        m_game_manager.onDraw();
    }

    @Override
    public void drawCircle(SimpleCircle circle) {
        m_paint.setColor(circle.getColor());
        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), m_paint);
    }

    @Override
    public void redraw() {
        invalidate();
    }

    @Override
    public void showMessage(String text) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX(), y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_MOVE)
            m_game_manager.onTouchEvent(x, y);
        invalidate();
        return true;
    }
}
