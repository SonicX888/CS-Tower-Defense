package com.mycompany.tue_tds;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author kyllianlim
 */
public class Enemies {
    
    ArrayList<Enemy> enemiesArray = new ArrayList<Enemy>();
    int creationSpeed = 5000;
    final int UPGRADESPEED = 900;
    String upgradesOrder = "hp";
    long lastSpawn = System.currentTimeMillis();
    
    void enemiesCreation() {
        long now = System.currentTimeMillis();
        if ((now - lastSpawn) >= creationSpeed) {
            lastSpawn = now;
            int cell = (int) (Math.random() * 9);
            Enemy enemy = new Enemy(0, 64 * (cell + 1), 2, 4, 2, 1, 0, false);
            enemiesArray.add(enemy);
            enemy.sprite = new JLabel(new ImageIcon(Enemies.class.getResource(
                    "/assets/grass_block_64x64.png"
            )));
            enemy.sprite.setSize(enemy.sprite.getPreferredSize());
        }
    }
    
    void update() {
        enemiesCreation();
    }
    
    void draw(Game frame) {
        for (Enemy e : enemiesArray) {
            if (e.sprite.getParent() == null) {
                frame.getLayeredPane().add(e.sprite, Integer.valueOf(1));
            }
            e.sprite.setLocation(e.x, e.y);
        }
        frame.getLayeredPane().repaint();
    }
}
