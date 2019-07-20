package com.example.snowfall.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.snowfall.model.Snowflake;

import java.util.ArrayList;
import java.util.Random;

public class SnowflakesView extends View {

    public SnowflakesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        paint = new Paint();
        random = new Random();
        snowflakes = new ArrayList<>();
        paint.setAntiAlias(true);
        width = w;
        height = h;
        dw = width / 4;
        for (int i = 0; i < 300; i++) {
            snowflakes.add(new Snowflake());
            snowflakes.get(i).setPosition(new Point(random.nextInt(width + 2 * dw - 1), 0));
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        move();
        drawSnow(canvas);
        invalidate();
    }

    private void move() {
        for (int i = 0; i < snowflakes.size(); i++) {
            final Point newPoint = new Point();
            final int dw = width / 4;
            snowflake = snowflakes.get(i);
            final Point oldPoint = snowflake.getPosition();
            if ((oldPoint.x > (width + dw)) || (oldPoint.x < -dw) || (oldPoint.y > height) || (oldPoint.y < 0)) {
                snowflake.setPosition(new Point(random.nextInt(width + 2 * dw - 1) - dw, 0));
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
        for (int i = 0; i < snowflakes.size(); i++) {
            snowflake = snowflakes.get(i);
            final Point pos = snowflake.getPosition();
            paint.setColor(snowflake.getColor());
            canvas.drawCircle(pos.x, pos.y, snowflake.getRadius(), paint);
        }
    }

    public int dw;
    public Snowflake snowflake;
    public Paint paint;
    public int width;
    public int height;
    public Random random;
    public ArrayList<Snowflake> snowflakes;
}
