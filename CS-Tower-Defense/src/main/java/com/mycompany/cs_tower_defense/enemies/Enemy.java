package com.mycompany.cs_tower_defense.enemies;

import com.mycompany.cs_tower_defense.Damageable;
import com.mycompany.cs_tower_defense.path.Astar;
import com.mycompany.cs_tower_defense.path.Node;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Abstract class that every type of enemy extends
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public abstract class Enemy implements Damageable {

    // position of the enemy on the window
    public int x;
    public int y;
    // health points of the enemy
    int hp;
    // number of pixels the enemy moves at each update
    int speed;
    // gold earned when the enemy dies
    int reward;
    // node of the grid where the enemy is
    Node node;
    // shortest path from the node of the enemy to the goal (null when it
    // has to be computed again)
    ArrayList<Node> path;
    // image of the enemy
    JLabel sprite;

    /**
     * Creates an enemy with its position, skills, node and image.
     *
     * @param x x position of the enemy on the window
     * @param y y position of the enemy on the window
     * @param hp health points of the enemy
     * @param speed number of pixels the enemy moves at each update
     * @param reward gold earned when the enemy dies
     * @param node node of the grid where the enemy starts
     * @param imagePath path of the image of the enemy in the resources
     */
    public Enemy(
            int x,
            int y,
            int hp,
            int speed,
            int reward,
            Node node,
            String imagePath
    ) {

        this.x = x;
        this.y = y;
        this.hp = hp;
        this.speed = speed;
        this.reward = reward;
        this.node = node;
        // loads the image of the enemy
        this.sprite = new JLabel(new ImageIcon(Enemy.class.getResource(
                imagePath
        )));
        // gives the label the size of the image
        this.sprite.setSize(sprite.getPreferredSize());

    }

    /**
     * Checks if the enemy is on the goal.
     *
     * @return true if the enemy reached the goal, false otherwise
     */
    public boolean goalReached() {
        return x == Astar.GOAL_X && y == Astar.GOAL_Y;
    }

    /**
     * Decreases the hp of the enemy by a specific amount.
     *
     * @param amount number of hp to remove from the enemy
     */
    @Override
    public void takeDamage(int amount) {
        hp -= amount;
    }

    /**
     * Checks if the enemy has no hp left.
     *
     * @return true if the enemy is dead, false otherwise
     */
    @Override
    public boolean isDead() {
        return hp <= 0;
    }

}
