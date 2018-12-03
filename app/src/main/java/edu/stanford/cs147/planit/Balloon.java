package edu.stanford.cs147.planit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;

public class Balloon {

    private boolean popped;
    private String idea;
    private float left;
    private float top;
    private float right;
    private float bottom;
    private BitmapDrawable bitmapDrawable;
    private Bitmap bitmap;

    public Balloon(String idea, BitmapDrawable imageDrawable, float left, float top) {
        popped = false;
        this.idea = idea;
        this.bitmapDrawable = imageDrawable;
        bitmap = bitmapDrawable.getBitmap();
        this.left = left;
        this.top = top;
        this.right = left + bitmap.getWidth();
        this.bottom = top + 500f;
    }

    public void drawBalloon(Canvas canvas) {
        if (!popped) {
            canvas.drawBitmap(bitmap, left, top, null);
        }
    }

    public void popBalloon() {
        popped = true;
    }

    public void retrieveBalloon() {
        popped = false;
    }

    public boolean isPopped() {
        return popped;
    }

    public String getIdea() {
        return idea;
    }

    public float getLeft() {
        return left;
    }
    public float getRight() {
        return right;
    }

    public float getTop() {
        return top;
    }

    public float getBottom() {
        return bottom;
    }
}
