package com.mycompany.cs_tower_defense.towers;

import com.mycompany.cs_tower_defense.enemies.Enemy;
import javax.swing.JLabel;

/**
 * Class that constructs the bullets
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class Bullets {

    // position of the bullet on the window
    int x;
    int y;
    // hp removed from the enemy when the bullet hits it
    int damage;
    // enemy followed by the bullet
    Enemy target;
    // number of pixels the bullet moves at each update
    int speed;
    // image of the bullet
    JLabel sprite;

    /**
     * Creates a bullet with its position, damage, target and speed.
     *
     * @param x x position of the bullet on the window
     * @param y y position of the bullet on the window
     * @param damage hp removed from the enemy when the bullet hits it
     * @param target enemy followed by the bullet
     * @param speed number of pixels the bullet moves at each update
     */
    public Bullets(int x, int y, int damage, Enemy target, int speed) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.target = target;
        this.speed = speed;
    }

}
