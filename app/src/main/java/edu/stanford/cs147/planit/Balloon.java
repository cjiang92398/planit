package edu.stanford.cs147.planit;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Balloon {

    private boolean popped;
    private String idea;
    private RectF rectF;

    public Balloon(String idea, float left, float top, float right, float bottom) {
        popped = false;
        this.idea = idea;
        rectF = new RectF();
        rectF.left = left;
        rectF.right = right;
        rectF.top = top;
        rectF.bottom = bottom;
    }

    public void drawBalloon(Canvas canvas) {
        if (!popped) {
            Paint testPaint = new Paint();
            testPaint.setColor(Color.WHITE);
            testPaint.setStyle(Paint.Style.STROKE);
            testPaint.setStrokeWidth(15f);
            canvas.drawRect(rectF, testPaint);
            // draw balloon using maybe
        }
    }

    public void popBalloon() {
        popped = true;
    }

    public void retrieveBalloon() {
        popped = false;
    }

    public String getIdea() {
        return idea;
    }

    public RectF getRectF() {
        return rectF;
    }
}
