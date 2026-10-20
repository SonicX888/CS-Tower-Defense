package com.mycompany.cs_tower_defense;

import com.mycompany.cs_tower_defense.inputs.MouseInputs;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This class handles building the towers and making them shoot enemies
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class TowerManager extends GameManager {

    public long towerRange = 150;
    public int towerDamage = 1;
    public int bulletSpeed = 7;
    public int towerCost = 10;
    public static ArrayList<Tower> towerList = new ArrayList<>();
    public static ArrayList<Bullets> bulletList = new ArrayList<>();
    private Tower tower;

    public void buildTower() {
        buildTower1();
    }

    // Builds a tower on a grid that the player clicked if there is enough gold
    public void buildTower1() {
        int x = MouseInputs.x;
        int y = MouseInputs.y;

        if (!canPlaceTower(x, y)) {
            return;
        }

        if (Economy.gold >= towerCost) {
            Economy.gold -= towerCost;
            tower = new Tower(x * 64, y * 64, 0, 0, 2000);
            towerList.add(tower);
            tower.sprite = new JLabel(new ImageIcon(
                    TowerManager.class.getResource("/assets/computer.png")));
            tower.sprite.setSize(tower.sprite.getPreferredSize());
        } else {
            Game.gameWindow.popUpText("You don't have enough gold");
        }

    }

    // Finds the node at the given coordinates
    // A helper method used in canPlaceTower
    private Node findNode(int x, int y) {
        for (Node n : Astar.gridList) {
            if (n.getX() == x * 64 && n.getY() == y * 64) {
                return n;
            }
        }
        return null;
    }

    // Checks whether the place intended to build a tower is suitable
    // returns true if it is suitable
    private boolean canPlaceTower(int x, int y) {
        //prevents placing towers outside of the 9x9 grid and at the first column
        if (x == 0 || y == 0 || y >= 10) {
            Game.gameWindow.popUpText("Can't place a tower at the first column"
                    + " or outside of the grid");
            return false;
        }

        //prevents placing tower on the end node
        if (x == 10 && y == 5) {
            Game.gameWindow.popUpText("Can't place a tower at the end node");
            return false;
        }

        //prevents placing tower over other towers
        for (Tower t : towerList) {
            if (t.x / 64 == x && t.y / 64 == y) {
                Game.gameWindow.popUpText("Can't place a tower over "
                        + "another tower");
                return false;
            }
        }
        // Checks whether placing a tower in the intended location would block
        // all paths that lead to the end node
        Node nTower = findNode(x, y);
        nTower.setIsTower(true);
        for (Node n : Astar.gridList) {
            if (Astar.findPath(n) == null) {
                nTower.setIsTower(false);
                Game.gameWindow.popUpText("Can't block all paths,"
                        + " no cheating allowed");
                return false;
            }
        }
        return true;
    }

    // Makes the towers shoot bullets if their cooldown is over
    private void shootEnemy() {
        long now = System.currentTimeMillis();

        for (Tower t : towerList) {
            if (now - t.lastShot >= t.fireRate) {
                Enemy closestEnemy = findEnemy(t);
                if (closestEnemy != null) {
                    t.lastShot = now;
                    Bullets bullet = new Bullets(t.x, t.y, towerDamage, closestEnemy, bulletSpeed);
                    bullet.sprite = new JLabel(new ImageIcon(
                            TowerManager.class.getResource("/images/java_logo.png")));
                    bullet.sprite.setSize(bullet.sprite.getPreferredSize());
                    bulletList.add(bullet);
                }
            }
        }

    }

    // Bullets track enemies and hit them
    private void shootBullet() {
        bulletList.removeIf(b -> {
            double dx = b.target.x - b.x;
            double dy = b.target.y - b.y;
            double distance = Math.hypot(dx, dy);
            if (distance <= b.speed) {
                b.target.takeDamage(b.damage);
                removeBullet(b);
                return true;
            } else {
                b.x = (int) (b.x + b.speed * (dx / distance));
                b.y = (int) (b.y + b.speed * (dy / distance));
            }
            return false;
        });
    }

    // Removes the bullet
    // Helper method for shootBullet()
    private void removeBullet(Bullets b) {
        Container parent = b.sprite.getParent();
        if (b.sprite != null && parent != null) {
            parent.remove(b.sprite);
            parent.repaint();
        }
    }

    // Finds the closest enemy to a tower
    // Helper method for buildTower1()
    private Enemy findEnemy(Tower t) {
        Enemy closestEnemy = null;
        double minDistance = Double.MAX_VALUE;

        for (Enemy e : EnemyManager.enemiesArray) {
            double distance = Math.hypot(e.x - t.x, e.y - t.y);
            if (distance < towerRange && distance < minDistance) {
                minDistance = distance;
                closestEnemy = e;
            }
        }
        return closestEnemy;
    }

    @Override
    void update() {
        shootEnemy();
        shootBullet();
    }

    @Override
    void draw(Game frame) {
        for (Tower i : towerList) {
            if (i.sprite.getParent() == null) {
                frame.getLayeredPane().add(i.sprite, Integer.valueOf(1));
            }
            i.sprite.setLocation(i.x, i.y);
        }
        for (Bullets b : bulletList) {
            if (b.sprite.getParent() == null) {
                frame.getLayeredPane().add(b.sprite, Integer.valueOf(1));
            }
            b.sprite.setLocation(b.x, b.y);
        }
    }

}
