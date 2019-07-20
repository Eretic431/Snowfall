package com.example.snowfall.model;

import android.graphics.Color;
import android.graphics.Point;

import java.util.Random;

public class Snowflake {

    public Snowflake() {
        Random random = new Random();
        coef = random.nextInt(4) + 1;
        if (random.nextBoolean()) {
            coef *= -1;
        }
        radius = random.nextInt(12) + 5;
        velocity = random.nextInt(2) + 1;
    }

    public int getColor() {
        if (velocity == 1) {
            return Color.rgb(169, 169, 169);
        }
        return Color.rgb(255, 255, 255);
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

    public int getVelocity() {
        return velocity;
    }

    private int coef;
    private int radius;
    private Point position;
    private int velocity;
}
