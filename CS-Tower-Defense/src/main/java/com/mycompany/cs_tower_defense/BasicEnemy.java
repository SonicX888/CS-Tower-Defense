package com.mycompany.cs_tower_defense;

/**
 * Class of the basic enemy that has the normal skills
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class BasicEnemy extends Enemy {

    /**
     * Creates a basic enemy with the normal hp, speed and reward.
     *
     * @param x x position of the enemy on the window
     * @param y y position of the enemy on the window
     * @param hp health points of the enemy
     * @param speed number of pixels the enemy moves at each update
     * @param reward gold earned when the enemy dies
     * @param node node of the grid where the enemy starts
     */
    public BasicEnemy(int x, int y, int hp, int speed, int reward, Node node) {
        super(x, y, hp, speed, reward, node, "/images/grass_block.png");
    }

}
