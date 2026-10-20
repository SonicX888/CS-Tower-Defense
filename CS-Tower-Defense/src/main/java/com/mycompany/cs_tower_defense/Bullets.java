package com.mycompany.cs_tower_defense;

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

    int x;
    int y;
    int damage;
    Enemy target;
    int speed;
    JLabel sprite;

    public Bullets(int x, int y, int damage, Enemy target, int speed) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.target = target;
        this.speed = speed;
    }

}
