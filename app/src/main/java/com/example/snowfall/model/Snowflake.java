package com.example.snowfall.model;

import android.graphics.Color;
import android.graphics.Point;

import java.util.Random;

public class Snowflake {

    private int coef;
    private int radius;
    private Point position;
    private int velocity;

    public Snowflake() {
        final Random random = new Random();
        coef = random.nextInt(4) + 1;
        if (random.nextBoolean()) {
            coef *= -1;
        }
        radius = random.nextInt(12) + 5;
        velocity = random.nextInt(2) + 1;
    }

    public int getColor() {
        return velocity == 1 ? Color.rgb(169, 169, 169) : Color.rgb(255, 255, 255);
    }

    public int getCoef() {
        return coef;
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
