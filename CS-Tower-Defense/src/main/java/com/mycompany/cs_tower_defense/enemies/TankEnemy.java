package com.mycompany.cs_tower_defense.enemies;

import com.mycompany.cs_tower_defense.path.Node;

/**
 * Class of the tank enemy that has more hp but moves slower
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class TankEnemy extends Enemy {

    /**
     * Creates a tank enemy with double the hp, half the speed and double the
     * reward.
     *
     * @param x x position of the enemy on the window
     * @param y y position of the enemy on the window
     * @param hp normal health points, multiplied by 2 for this enemy
     * @param speed normal speed, divided by 2 for this enemy
     * @param reward normal reward, multiplied by 2 for this enemy
     * @param node node of the grid where the enemy starts
     */
    public TankEnemy(int x, int y, int hp, int speed, int reward, Node node) {
        super(x, y, hp * 2, speed / 2, reward * 2, node, "/images/sun.png");
    }

}
