package com.mycompany.cs_tower_defense;

import java.awt.Container;
import java.util.ArrayList;

/**
 *
 * @author kyllianlim
 */
public class EnemyManager extends GameManager {

    public static ArrayList<Enemy> enemiesArray = new ArrayList<Enemy>();
    int creationSpeed = 3000;
    final int UPGRADE_SPEED = 20000;
    String upgradesOrder = "creation speed";
    long lastSpawn = System.currentTimeMillis();
    long lastSpawnStrong = System.currentTimeMillis();
    long lastUpgrade = System.currentTimeMillis();
    int enemyHP = 2;
    int enemySpeed = 1;

    void enemiesCreation() {
        long nowCreation = System.currentTimeMillis();
        long elapsed = nowCreation - lastSpawn;
        long elapsedStrong = nowCreation - lastSpawnStrong;
        
        if (elapsed >= creationSpeed) {
            
            lastSpawn = nowCreation;
            int cell = (int) (Math.random() * 9);
            int y = 64 * (cell + 1);
            Node enemyNode = Astar.grid[cell][0];
            Enemy enemy;
            int minutes = Integer.parseInt(Main.time.stringMinutes);
            
            if (minutes >= 1 && elapsedStrong >= creationSpeed*2) {
                lastSpawnStrong = nowCreation;
                int type = (int) (Math.random() * 2);
                if (minutes >= 2 && type == 1) {
                    enemy = new TankEnemy(0, y, enemyHP, enemySpeed, enemyHP, enemyNode);
                } else {
                    enemy = new FastEnemy(0, y, enemyHP, enemySpeed, enemyHP, enemyNode);
                }
            } else {
                enemy = new BasicEnemy(0, y, enemyHP, enemySpeed, enemyHP, enemyNode);
            }

            enemiesArray.add(enemy);
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
    
    void enemiesUpgrade() {
        long nowUpgrade = System.currentTimeMillis();
        if ((nowUpgrade - lastUpgrade) >= UPGRADE_SPEED) {
            lastUpgrade = nowUpgrade;
            if (upgradesOrder.equals("creation speed")) {
                if (creationSpeed > 250) {
                    creationSpeed -= 250;
                }
                upgradesOrder = "hp";
            } else if (upgradesOrder.equals("hp")) {
                enemyHP += 1;
                upgradesOrder = "speed";
            } else if (upgradesOrder.equals("speed")) {
                if (enemySpeed < 4) {
                    enemySpeed *= 2;
                }
                upgradesOrder = "creation speed";
            }
        }
    }
    
    void enemyDead(){
        enemiesArray.removeIf(e -> {
            if (e.isDead()){
                Economy.gold += e.reward;
                if (e.sprite != null){
                    Container parent = e.sprite.getParent();
                    if (parent != null){
                        parent.remove(e.sprite);
                        parent.repaint();
                    }
                }
                return true;
            }
            return false;
        });
    }
    
    void enemiesGoalReached() {
        enemiesArray.removeIf(e -> {
            if (e.goalReached()){
                if (e.sprite != null){
                    Container parent = e.sprite.getParent();
                    if (parent != null){
                        Main.hp.takeDamage(e.hp);
                        parent.remove(e.sprite);
                        parent.repaint();
                    }
                }
                return true;
            }
            return false;
        });
    }

    @Override
    void update() {
        enemiesCreation();
        enemiesMovements();
        enemiesUpgrade();
        enemyDead();
        enemiesGoalReached();
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
