package com.example.snowfall.model;

import android.graphics.Color;
import android.graphics.Point;

import java.util.Random;

public class Snowflake {

    private int coefficient;
    private int radius;
    private Point position;
    private int velocity;
    private static final int GRAY  = 169;
    private static final int WHITE = 255;

    public Snowflake() {
        final Random random = new Random();
        coefficient = random.nextInt(4) + 1;
        if (random.nextBoolean()) {
            coefficient *= -1;
        }
        radius = random.nextInt(12) + 5;
        velocity = random.nextInt(2) + 1;
    }

    public int getColor() {
        return velocity == 1 ? Color.rgb(GRAY, GRAY, GRAY) : Color.rgb(WHITE, WHITE,WHITE);
    }

    public int getCoefficient() {
        return coefficient;
    }

    public int getRadius() {
        return radius;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point newPosition) {
        position = newPosition;
    }

    public void setPosition(int x, int y) {
        final Point newPosition = new Point(x, y);
        setPosition(newPosition);
    }

    public int getVelocity() {
        return velocity;
    }

    public void setDefaultPosition(int w, int dw) {
        final Random random = new Random();
        final int x = random.nextInt(w + 2 * dw - 1) - dw;
        setPosition(x, 0);
    }
}
