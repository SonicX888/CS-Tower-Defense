package com.mycompany.cs_tower_defense.towers;

import com.mycompany.cs_tower_defense.GameManager;
import com.mycompany.cs_tower_defense.enemies.Enemy;
import com.mycompany.cs_tower_defense.enemies.EnemyManager;
import com.mycompany.cs_tower_defense.gui.Economy;
import com.mycompany.cs_tower_defense.gui.Game;
import com.mycompany.cs_tower_defense.inputs.MouseInputs;
import com.mycompany.cs_tower_defense.path.Astar;
import com.mycompany.cs_tower_defense.path.Node;
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
 
    // range in pixels of a new tower
    public long towerRange = 128;
    // damage of a new tower
    public int towerDamage = 1;
    // number of pixels a bullet moves at each update
    public int bulletSpeed = 11;
    // gold needed to build a tower
    public int towerCost = 10;
    // array where each tower is stored
    public static ArrayList<Tower> towerList = new ArrayList<>();
    // array where each bullet is stored
    public static ArrayList<Bullets> bulletList = new ArrayList<>();
    // last tower built
    private Tower tower;
 
    /**
     * Builds a tower (there is only one type of tower for now).
     */
    public void buildTower() {
        buildTower1();
    }
 
    /**
     * Builds a tower on the cell of the grid that the player clicked if there
     * is enough gold.
     */
    public void buildTower1() {
        // gets the cell clicked by the player
        int x = MouseInputs.x;
        int y = MouseInputs.y;
 
        // checks if the player has enough gold before testing the cell, so
        // the node is never marked as a tower if the tower can't be paid
        if (Economy.gold < towerCost) {
            // tells the player there is not enough gold
            Game.gameWindow.popUpText("You don't have enough gold");
            return;
        }
 
        // stops if a tower can't be placed on this cell
        if (!canPlaceTower(x, y)) {
            return;
        }
 
        // removes the cost of the tower from the gold
        Economy.gold -= towerCost;
        // creates a level 0 tower that shoots every 2 seconds with the base
        // damage and range
        tower = new Tower(x * 64, y * 64, 0, 0, 2000, towerDamage, towerRange);
        // adds the tower to the array
        towerList.add(tower);
        // loads the image of the tower
        tower.sprite = new JLabel(new ImageIcon(
                TowerManager.class.getResource("/images/computer.png")));
        // gives the label the size of the image
        tower.sprite.setSize(tower.sprite.getPreferredSize());
 
    }
 
    /**
     * Finds the node at the given coordinates. A helper method used in
     * canPlaceTower.
     *
     * @param x column of the cell
     * @param y row of the cell
     * @return the node of the cell, or null if there is none
     */
    private Node findNode(int x, int y) {
        // goes through each node of the grid
        for (Node n : Astar.gridList) {
            // returns the node if it is at the given position
            if (n.getX() == x * 64 && n.getY() == y * 64) {
                return n;
            }
        }
        // no node was found
        return null;
    }
 
    /**
     * Checks whether the place intended to build a tower is suitable.
     *
     * @param x column of the cell clicked by the player
     * @param y row of the cell clicked by the player
     * @return true if it is suitable, false otherwise
     */
    private boolean canPlaceTower(int x, int y) {
        //prevents placing towers outside of the 9x11 grid and at the first column
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
        // puts a tower on the node to test it
        Node nTower = findNode(x, y);
        nTower.setIsTower(true);
        // checks if each node without a tower can still reach the end node
        for (Node n : Astar.gridList) {
            if (!n.isIsTower() && Astar.findPath(n) == null) {
                // removes the tower from the node because it blocks all paths
                nTower.setIsTower(false);
                Game.gameWindow.popUpText("Can't block all paths,"
                        + " no cheating allowed");
                return false;
            }
        }
        // the tower stays on the node so the enemies go around it
        return true;
    }
 
    /**
     * Makes the towers shoot bullets if their cooldown is over.
     */
    private void shootEnemy() {
        long now = System.currentTimeMillis();
 
        // goes through each tower
        for (Tower t : towerList) {
            // checks if the cooldown of the tower is over
            if (now - t.lastShot >= t.fireRate) {
                // finds the closest enemy in the range of the tower
                Enemy closestEnemy = findEnemy(t);
                // shoots only if an enemy was found
                if (closestEnemy != null) {
                    // restarts the cooldown of the tower
                    t.lastShot = now;
                    // creates a bullet that starts on the tower, follows the
                    // enemy and has the damage of the tower
                    Bullets bullet = new Bullets(t.x, t.y, t.damage, closestEnemy, bulletSpeed);
                    // loads the image of the bullet
                    bullet.sprite = new JLabel(new ImageIcon(
                            TowerManager.class.getResource("/images/java_logo.png")));
                    // gives the label the size of the image
                    bullet.sprite.setSize(bullet.sprite.getPreferredSize());
                    // adds the bullet to the array
                    bulletList.add(bullet);
                }
            }
        }
 
    }
 
    /**
     * Bullets track enemies and hit them.
     */
    private void shootBullet() {
        // goes through each bullet and removes it if it hit its target
        bulletList.removeIf(b -> {
            // removes the bullet if its target is already dead or is not on
            // the map anymore (killed or reached the goal)
            if (b.target.isDead()
                    || !EnemyManager.enemiesArray.contains(b.target)) {
                removeBullet(b);
                return true;
            }
            // computes the distance between the bullet and its target
            double dx = b.target.x - b.x;
            double dy = b.target.y - b.y;
            double distance = Math.hypot(dx, dy);
            // checks if the bullet is close enough to hit the target
            if (distance <= b.speed) {
                // damages the target and removes the bullet
                b.target.takeDamage(b.damage);
                removeBullet(b);
                return true;
            } else {
                // moves the bullet towards the target
                b.x = (int) (b.x + b.speed * (dx / distance));
                b.y = (int) (b.y + b.speed * (dy / distance));
            }
            return false;
        });
    }
 
    /**
     * Removes the bullet from the window. Helper method for shootBullet().
     *
     * @param b bullet to remove
     */
    private void removeBullet(Bullets b) {
        // checks if the bullet has an image
        if (b.sprite != null) {
            // removes the image of the bullet from the window
            Container parent = b.sprite.getParent();
            if (parent != null) {
                parent.remove(b.sprite);
                parent.repaint();
            }
        }
    }
 
    /**
     * Finds the closest enemy to a tower. Helper method for shootEnemy().
     *
     * @param t tower that wants to shoot
     * @return the closest enemy in the range of the tower, or null if there is
     * none
     */
    private Enemy findEnemy(Tower t) {
        Enemy closestEnemy = null;
        // puts the smallest distance to the highest possible value
        double minDistance = Double.MAX_VALUE;
 
        // goes through each enemy
        for (Enemy e : EnemyManager.enemiesArray) {
            // computes the distance between the enemy and the tower
            double distance = Math.hypot(e.x - t.x, e.y - t.y);
            // keeps the enemy if it is in the range of the tower and closer
            // than the others
            if (distance < t.range && distance < minDistance) {
                minDistance = distance;
                closestEnemy = e;
            }
        }
        return closestEnemy;
    }
 
    /**
     * Method that makes the towers shoot and moves the bullets and is called
     * infinitely in the class Main.
     */
    @Override
    public void update() {
        // makes the towers shoot
        shootEnemy();
        // moves the bullets
        shootBullet();
    }
 
    /**
     * Method that draws the towers and the bullets on the window and is called
     * infinitely in the class Main.
     *
     * @param frame window of the game where the towers and the bullets are
     * drawn
     */
    @Override
    public void draw(Game frame) {
        // goes through each tower
        for (Tower i : towerList) {
            // draws the tower if it is not on the window yet
            if (i.sprite.getParent() == null) {
                frame.getLayeredPane().add(i.sprite, Integer.valueOf(1));
            }
            i.sprite.setLocation(i.x, i.y);
        }
        // goes through each bullet
        for (Bullets b : bulletList) {
            // draws the bullet if it is not on the window yet
            if (b.sprite.getParent() == null) {
                frame.getLayeredPane().add(b.sprite, Integer.valueOf(1));
            }
            // moves the image of the bullet to its new position
            b.sprite.setLocation(b.x, b.y);
        }
    }
 
}