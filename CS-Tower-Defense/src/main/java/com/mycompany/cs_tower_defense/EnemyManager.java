package com.mycompany.cs_tower_defense;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author kyllianlim
 */
public class EnemyManager extends GameManager {

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
            Node enemyNode = Astar.grid[cell][0];
            Enemy enemy = new Enemy(
                    0,
                    64 * (cell + 1),
                    2,
                    2,
                    2,
                    1,
                    0,
                    false,
                    enemyNode
            );
            enemiesArray.add(enemy);
            enemy.sprite = new JLabel(new ImageIcon(
                    EnemyManager.class.getResource(
                            "/assets/grass_block.png"
                    )));
            enemy.sprite.setSize(enemy.sprite.getPreferredSize());
        }
    }

    void enemiesMovements() {
        for (Enemy e : enemiesArray) {
            ArrayList<Node> path = Astar.findPath(e.node);
            if (path == null || path.size() < 2) {
                continue;
            }
            Node next = path.get(1);
            if (e.x == next.x && e.y == next.y) {
                e.node = next;
            } else {
                if (next.x > e.x) {
                    e.x += e.speed;
                }
                if (next.x < e.x) {
                    e.x -= e.speed;
                }
                if (next.y > e.y) {
                    e.y += e.speed;
                }
                if (next.y < e.y) {
                    e.y -= e.speed;
                }
            }
        }
    }

    @Override
    void update() {
        enemiesCreation();
        enemiesMovements();
    }

    @Override
    void draw(Game frame) {
        for (Enemy e : enemiesArray) {
            if (e.sprite.getParent() == null) {
                frame.getLayeredPane().add(e.sprite, Integer.valueOf(1));
            }
            e.sprite.setLocation(e.x, e.y);
        }
    }
}
