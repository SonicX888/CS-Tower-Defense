package com.mycompany.cs_tower_defense.enemies;

import com.mycompany.cs_tower_defense.GameManager;
import com.mycompany.cs_tower_defense.Main;
import com.mycompany.cs_tower_defense.gui.Economy;
import com.mycompany.cs_tower_defense.gui.Game;
import com.mycompany.cs_tower_defense.path.Astar;
import com.mycompany.cs_tower_defense.path.Node;
import java.awt.Container;
import java.util.ArrayList;

/**
 * Class that manages the enemies
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class EnemyManager extends GameManager {

    // array where each enemy is stored
    public static ArrayList<Enemy> enemiesArray = new ArrayList<Enemy>();

    // initializes the number of milliseconds counted each time an enemy
    // is created
    int creationSpeed = 3000;
    // number of milliseconds counted each time an enemy is upgraded
    static final int UPGRADE_SPEED = 15000;
    // puts the creation speed as the first skill to be upgraded
    String upgradesOrder = "creation speed";

    // time when the last enemy was created
    long lastSpawn = System.currentTimeMillis();
    // time when the last fast or tank enemy was created
    long lastSpawnStrong = System.currentTimeMillis();
    // time when the enemies were last upgraded
    long lastUpgrade = System.currentTimeMillis();

    // hp given to each new enemy
    int enemyHP = 2;
    // speed given to each new enemy
    int enemySpeed = 1;

    /**
     * Creates an enemy on a random cell of the first column of the map every
     * time a specific amount of milliseconds is counted.
     */
    void enemiesCreation() {

        // computes the time since the last enemy and the last strong enemy
        long nowCreation = System.currentTimeMillis();
        long elapsed = nowCreation - lastSpawn;
        long elapsedStrong = nowCreation - lastSpawnStrong;

        // checks if it's time to create a new enemy
        if (elapsed >= creationSpeed) {

            lastSpawn = nowCreation;
            // chooses a random cell of the first column of the map
            int cell = (int) (Math.random() * 9);
            int y = 64 * (cell + 1);
            Node enemyNode = Astar.grid[cell][0];
            Enemy enemy;
            // gets the number of minutes survived
            int minutes = Integer.parseInt(Main.time.stringMinutes);

            // checks if a strong enemy can be created (after 1 minute and
            // twice slower than the basic enemies)
            if (minutes >= 1 && elapsedStrong >= creationSpeed * 2) {
                lastSpawnStrong = nowCreation;
                // chooses randomly between a fast and a tank enemy
                int type = (int) (Math.random() * 2);
                // creates a tank enemy (only after 2 minutes)
                if (minutes >= 2 && type == 1) {
                    enemy = new TankEnemy(
                            0, y, enemyHP, enemySpeed, enemyHP, enemyNode
                    );
                    // creates a fast enemy
                } else {
                    enemy = new FastEnemy(
                            0, y, enemyHP, enemySpeed, enemyHP, enemyNode
                    );
                }
                // creates a basic enemy
            } else {
                enemy = new BasicEnemy(
                        0, y, enemyHP, enemySpeed, enemyHP, enemyNode
                );
            }

            // adds the enemy to the array
            enemiesArray.add(enemy);
        }
    }

    /**
     * Moves the enemies towards the goal.
     */
    void enemiesMovements() {
        // goes through each enemy
        for (Enemy e : enemiesArray) {
            // finds the shortest path from the node of the enemy to the goal
            ArrayList<Node> path = Astar.findPath(e.node);
            // skips the enemy if there is no path or if it is on the goal
            if (path == null || path.size() < 2) {
                continue;
            }
            // next node where the enemy has to go
            Node next = path.get(1);
            // updates the node of the enemy if it reached the next node
            if (e.x == next.x && e.y == next.y) {
                e.node = next;
            } else {
                // moves the enemy to the right
                if (next.x > e.x) {
                    e.x += e.speed;
                }
                // moves the enemy to the left
                if (next.x < e.x) {
                    e.x -= e.speed;
                }
                // moves the enemy down
                if (next.y > e.y) {
                    e.y += e.speed;
                }
                // moves the enemy up
                if (next.y < e.y) {
                    e.y -= e.speed;
                }
            }
        }
    }

    /**
     * Method that upgrades a skill of the enemies every time a specific amount
     * of milliseconds is counted.
     */
    void enemiesUpgrade() {

        long nowUpgrade = System.currentTimeMillis();

        // checks if 15 seconds had passed
        if ((nowUpgrade - lastUpgrade) >= UPGRADE_SPEED) {
            lastUpgrade = nowUpgrade;
            // checks if it's the turn of the creation speed to be upgraded
            if (upgradesOrder.equals("creation speed")) {
                // limits the creation speed to higher than 0.25 seconds
                if (creationSpeed > 250) {
                    // decreases the creation speed of 0.25 seconds
                    creationSpeed -= 250;
                }
                // next skill to be upgraded: hp
                upgradesOrder = "hp";
                // checks if it's the turn of the hp to be upgraded
            } else if (upgradesOrder.equals("hp")) {
                // increases the hp of 1
                enemyHP += 1;
                // next skill to be upgraded: speed
                upgradesOrder = "speed";
                // checks if it's the turn of the speed to be upgraded
            } else if (upgradesOrder.equals("speed")) {
                // limits the speed to 4 pixels per update
                if (enemySpeed < 4) {
                    // doubles the speed
                    enemySpeed *= 2;
                }
                // next skill to be upgraded: creation speed
                upgradesOrder = "creation speed";
            }
        }
    }

    /**
     * Removes the dead enemies from the array and the window, and gives their
     * reward to the player.
     */
    void enemyDead() {
        // goes through each enemy and removes it if it is dead
        enemiesArray.removeIf(e -> {
            if (e.isDead()) {
                // gives the reward of the enemy to the player
                Economy.gold += e.reward;
                // removes the image of the enemy from the window
                if (e.sprite != null) {
                    Container parent = e.sprite.getParent();
                    if (parent != null) {
                        parent.remove(e.sprite);
                        parent.repaint();
                    }
                }
                return true;
            }
            return false;
        });
    }

    /**
     * Removes the enemies that reached the goal from the array and the window,
     * and damages the base.
     */
    void enemiesGoalReached() {
        // goes through each enemy and removes it if it reached the goal
        enemiesArray.removeIf(e -> {
            if (e.goalReached()) {
                if (e.sprite != null) {
                    Container parent = e.sprite.getParent();
                    if (parent != null) {
                        // removes the remaining hp of the enemy from the base
                        Main.hp.takeDamage(e.hp);
                        // removes the image of the enemy from the window
                        parent.remove(e.sprite);
                        parent.repaint();
                    }
                }
                return true;
            }
            return false;
        });
    }

    /**
     * Method that updates everything inside and is called infinitely in the
     * class Main.
     */
    @Override
    public void update() {
        // creates enemies
        enemiesCreation();
        // moves enemies
        enemiesMovements();
        // upgrades the enemies
        enemiesUpgrade();
        // removes the dead enemies
        enemyDead();
        // removes the enemies that reached the goal
        enemiesGoalReached();
    }

    /**
     * Method that draws the enemies on the window and is called infinitely in
     * the class Main.
     *
     * @param frame window of the game where the enemies are drawn
     */
    @Override
    public void draw(Game frame) {
        // goes through each enemy
        for (Enemy e : enemiesArray) {
            // checks if the enemy is not on the window yet
            if (e.sprite.getParent() == null) {
                // draws the enemies
                frame.getLayeredPane().add(e.sprite, Integer.valueOf(1));
            }
            // moves the image of the enemy to its new position
            e.sprite.setLocation(e.x, e.y);
        }
    }
}
