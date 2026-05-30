package com.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
    private static final int TARGET_FPS = 60;
    private static final float BASE_SPAWN_INTERVAL = 0.85f;
    private static final float MIN_SPAWN_INTERVAL = 0.28f;
    private static final float SECONDS_PER_LEVEL = 12f;
    private static final float LEVEL_UP_FLASH_DURATION = 2f;

    private final int worldWidth;
    private final int worldHeight;
    private final Player player;
    private final List<FallingRock> rocks = new ArrayList<>();
    private final Timer timer;

    private float spawnTimer;
    private float elapsed;
    private int score;
    private int level = 1;
    private float levelUpFlash;
    private boolean running;
    private boolean gameOver;

    public GamePanel(int width, int height) {
        worldWidth = width;
        worldHeight = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        setBackground(Color.BLACK);

        float groundY = height - 70;
        player = new Player(width / 2f - Player.WIDTH / 2f, groundY);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKey(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                handleKey(e.getKeyCode(), false);
            }
        });

        timer = new Timer(1000 / TARGET_FPS, this);
    }

    public void start() {
        running = true;
        timer.start();
    }

    private void handleKey(int code, boolean pressed) {
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            player.setMoveLeft(pressed);
        }
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            player.setMoveRight(pressed);
        }
        if (pressed && code == KeyEvent.VK_R && gameOver) {
            reset();
        }
    }

    private void reset() {
        rocks.clear();
        player.resetPosition(worldWidth / 2f - Player.WIDTH / 2f);
        spawnTimer = 0;
        elapsed = 0;
        score = 0;
        level = 1;
        levelUpFlash = 0;
        gameOver = false;
    }

    private int computeLevel() {
        return 1 + (int) (elapsed / SECONDS_PER_LEVEL);
    }

    private float getSpawnInterval() {
        return Math.max(MIN_SPAWN_INTERVAL, BASE_SPAWN_INTERVAL - (level - 1) * 0.07f);
    }

    private float getFallSpeedMultiplier() {
        return 1f + (level - 1) * 0.2f;
    }

    private float getDoubleSpawnChance() {
        if (level < 3) {
            return 0f;
        }
        return Math.min(0.65f, 0.15f + (level - 3) * 0.08f);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!running) {
            return;
        }

        float dt = 1f / TARGET_FPS;
        if (!gameOver) {
            update(dt);
        }
        repaint();
    }

    private void update(float dt) {
        elapsed += dt;
        score = (int) elapsed * 10;

        int newLevel = computeLevel();
        if (newLevel > level) {
            level = newLevel;
            levelUpFlash = LEVEL_UP_FLASH_DURATION;
        }
        if (levelUpFlash > 0) {
            levelUpFlash -= dt;
        }

        player.update(dt, worldWidth);

        spawnTimer += dt;
        if (spawnTimer >= getSpawnInterval()) {
            spawnTimer = 0;
            float speedMult = getFallSpeedMultiplier();
            rocks.add(new FallingRock(worldWidth, speedMult));
            if (ThreadLocalRandom.current().nextFloat() < getDoubleSpawnChance()) {
                rocks.add(new FallingRock(worldWidth, speedMult));
            }
        }

        Iterator<FallingRock> it = rocks.iterator();
        while (it.hasNext()) {
            FallingRock rock = it.next();
            rock.update(dt);
            if (rock.isOffScreen(worldHeight)) {
                it.remove();
            } else if (player.intersects(rock)) {
                gameOver = true;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawBackground(g2);
        drawGround(g2);

        for (FallingRock rock : rocks) {
            rock.draw(g2);
        }
        player.draw(g2);
        drawHud(g2);

        if (gameOver) {
            drawGameOver(g2);
        }

        g2.dispose();
    }

    private void drawBackground(Graphics2D g) {
        g.setPaint(new GradientPaint(0, 0, new Color(15, 12, 45), 0, worldHeight, new Color(45, 25, 90)));
        g.fillRect(0, 0, worldWidth, worldHeight);

        float starSpeed = 20f + (level - 1) * 6f;
        g.setColor(new Color(255, 255, 255, 40));
        for (int i = 0; i < 60; i++) {
            int sx = (i * 137) % worldWidth;
            int sy = (i * 89 + (int) (elapsed * starSpeed)) % (worldHeight - 80);
            int r = 1 + (i % 2);
            g.fillOval(sx, sy, r, r);
        }
    }

    private void drawGround(Graphics2D g) {
        int groundTop = worldHeight - 70;
        g.setPaint(new GradientPaint(0, groundTop, new Color(35, 28, 55), 0, worldHeight, new Color(20, 18, 35)));
        g.fillRect(0, groundTop, worldWidth, 70);
        g.setColor(new Color(100, 80, 140, 80));
        g.drawLine(0, groundTop, worldWidth, groundTop);
    }

    private void drawHud(Graphics2D g) {
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.setColor(new Color(255, 255, 255, 220));
        g.drawString("Score: " + score, 20, 36);

        g.setFont(new Font("SansSerif", Font.BOLD, 18));
        g.setColor(new Color(255, 220, 100, 230));
        g.drawString("Level " + level, 20, 62);

        if (levelUpFlash > 0) {
            g.setFont(new Font("SansSerif", Font.BOLD, 28));
            g.setColor(new Color(100, 255, 180, (int) (220 * (levelUpFlash / LEVEL_UP_FLASH_DURATION))));
            String msg = "LEVEL UP!";
            int mw = g.getFontMetrics().stringWidth(msg);
            g.drawString(msg, (worldWidth - mw) / 2, 100);
        }

        if (!gameOver) {
            g.setFont(new Font("SansSerif", Font.PLAIN, 14));
            g.setColor(new Color(200, 200, 220, 180));
            g.drawString("← → or A D to move", 20, 84);
        }
    }

    private void drawGameOver(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, worldWidth, worldHeight);

        g.setFont(new Font("SansSerif", Font.BOLD, 42));
        g.setColor(new Color(255, 90, 90));
        String title = "Game Over";
        int tw = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (worldWidth - tw) / 2, worldHeight / 2 - 30);

        g.setFont(new Font("SansSerif", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        String sub = "Score: " + score + "  Level: " + level + "  —  Press R to restart";
        int sw = g.getFontMetrics().stringWidth(sub);
        g.drawString(sub, (worldWidth - sw) / 2, worldHeight / 2 + 20);
    }
}
