package com.example.snowfall.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.snowfall.model.Snowflake;

import java.util.ArrayList;
import java.util.Random;

public class SnowflakesView extends View {

    private int dw;
    private Paint paint;
    private int width;
    private int height;
    private Random random;
    private ArrayList<Snowflake> snowflakes;

    public SnowflakesView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

        paint = new Paint();
        paint.setAntiAlias(true);
        random = new Random();
        snowflakes = new ArrayList<>(300);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        dw = width / 4;
        for (int i = 0; i < 300; i++) {
            final Snowflake snowflake = new Snowflake();
            snowflake.setDefaultPosition(width, dw);
            snowflakes.add(snowflake);
        }
    }

    @Override
    protected void onDraw(final @NonNull Canvas canvas) {
        super.onDraw(canvas);
        move();
        drawSnow(canvas);
        invalidate();
    }

    private void move() {
        for (Snowflake snowflake : snowflakes) {
            final Point newPoint = new Point();
            final Point oldPoint = snowflake.getPosition();
            if ((oldPoint.x > width + dw) || (oldPoint.x < -dw) || (oldPoint.y > height) || (oldPoint.y < 0)) {
                snowflake.setDefaultPosition(width, dw);
                continue;
            }
            if (snowflake.getCoef() < 0) {
                newPoint.x = snowflake.getPosition().x - snowflake.getVelocity();
            } else {
                newPoint.x = snowflake.getPosition().x + snowflake.getVelocity();
            }
            newPoint.y = snowflake.getPosition().y + Math.abs(snowflake.getCoef()) * snowflake.getVelocity();
            snowflake.setPosition(newPoint);
        }
    }

    public void drawSnow(Canvas canvas) {
        for (Snowflake snowflake : snowflakes) {
            final Point pos = snowflake.getPosition();
            paint.setColor(snowflake.getColor());
            canvas.drawCircle(pos.x, pos.y, snowflake.getRadius(), paint);
        }
    }
}
