package com.mycompany.tue_tds;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.ArrayList;

/**
 *
 * @author kyllianlim
 */
public class Enemies {
    
    static ArrayList<Enemy> enemiesArray = new ArrayList<Enemy>();
    private static int creationSpeed = 5000;
    private static final int UPGRADE_SPEED = 900;
    private String upgradesOrder = "hp";
    private static long lastSpawn = System.currentTimeMillis();
    
    private static void enemiesCreation() {
        long now = System.currentTimeMillis();
        if ((now - lastSpawn) >= creationSpeed) {
            lastSpawn = now;
            int cell = (int)(Math.random() * 9);
            Enemy enemy = new Enemy(0, 64 * (cell + 1), 2, 4, 2, 1, 0, false);
            enemiesArray.add(enemy);
            enemy.sprite = new JLabel(new ImageIcon(Enemies.class.getResource("/assets/grass_block_64x64.png")));
            enemy.sprite.setSize(enemy.sprite.getPreferredSize());
        }
    }
    public void update() {
        enemiesCreation();
    }
    
    public void draw(Game frame) {
        for (Enemy e : enemiesArray) {
            if (e.sprite.getParent() == null) {
                frame.getLayeredPane().add(e.sprite, Integer.valueOf(1));
            }
            e.sprite.setLocation(e.x, e.y);
        }
        frame.getLayeredPane().repaint();
    }
}
