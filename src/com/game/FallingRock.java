package com.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.concurrent.ThreadLocalRandom;

public class FallingRock {
    private float x;
    private float y;
    private final float speed;
    private final int size;
    private final Color baseColor;
    private final Color highlightColor;

    public FallingRock(int worldWidth, float speedMultiplier) {
        size = ThreadLocalRandom.current().nextInt(28, 48);
        x = ThreadLocalRandom.current().nextInt(0, Math.max(1, worldWidth - size));
        y = -size - ThreadLocalRandom.current().nextInt(0, 200);
        float baseSpeed = 140f + ThreadLocalRandom.current().nextFloat() * 180f;
        speed = baseSpeed * speedMultiplier;
        int shade = ThreadLocalRandom.current().nextInt(90, 150);
        baseColor = new Color(shade + 40, shade, shade - 10);
        highlightColor = new Color(shade + 90, shade + 70, shade + 50);
    }

    public void update(float dt) {
        y += speed * dt;
    }

    public boolean isOffScreen(int worldHeight) {
        return y > worldHeight + size;
    }

    public void draw(Graphics2D g) {
        g.setColor(baseColor);
        g.fill(new Ellipse2D.Float(x, y, size, size));
        g.setColor(highlightColor);
        g.fill(new Ellipse2D.Float(x + size * 0.15f, y + size * 0.12f, size * 0.35f, size * 0.3f));
        g.setColor(new Color(40, 35, 45, 120));
        g.draw(new Ellipse2D.Float(x, y, size, size));
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
}
