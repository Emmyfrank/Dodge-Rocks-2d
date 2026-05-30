package com.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Player {
    public static final int WIDTH = 48;
    public static final int HEIGHT = 36;
    public static final float SPEED = 420f;

    private float x;
    private final float y;
    private boolean moveLeft;
    private boolean moveRight;

    public Player(float startX, float groundY) {
        this.x = startX;
        this.y = groundY - HEIGHT;
    }

    public void resetPosition(float startX) {
        x = startX;
        moveLeft = false;
        moveRight = false;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public void update(float dt, int worldWidth) {
        if (moveLeft) {
            x -= SPEED * dt;
        }
        if (moveRight) {
            x += SPEED * dt;
        }
        x = Math.max(0, Math.min(worldWidth - WIDTH, x));
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean intersects(FallingRock rock) {
        return x < rock.getX() + rock.getSize()
                && x + WIDTH > rock.getX()
                && y < rock.getY() + rock.getSize()
                && y + HEIGHT > rock.getY();
    }

    public void draw(Graphics2D g) {
        float cx = x + WIDTH / 2f;
        float top = y + 6;
        float bottom = y + HEIGHT;

        Path2D.Float ship = new Path2D.Float();
        ship.moveTo(cx, top);
        ship.lineTo(x + WIDTH - 4, bottom);
        ship.lineTo(x + 4, bottom);
        ship.closePath();

        g.setColor(new Color(60, 220, 255));
        g.fill(ship);
        g.setColor(new Color(200, 245, 255));
        g.draw(ship);

        g.setColor(new Color(255, 180, 60));
        g.fillOval((int) (x + WIDTH / 2f - 5), (int) (bottom - 10), 10, 8);
    }
}
