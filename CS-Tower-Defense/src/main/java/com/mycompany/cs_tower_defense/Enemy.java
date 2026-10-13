package com.mycompany.cs_tower_defense;

import javax.swing.JLabel;

/**
 *
 * @author kyllianlim
 */
public class Enemy implements Damageable {

    int x;
    int y;
    int hp;
    int speed;
    int reward;
    int type;
    int countBullet;
    boolean bulletTouched;
    Node node;
    JLabel sprite;

    public Enemy(
            int x,
            int y,
            int hp,
            int speed,
            int reward,
            int type,
            int countBullet,
            boolean bulletTouched,
            Node node
    ) {

        this.x = x;
        this.y = y;
        this.hp = hp;
        this.speed = speed;
        this.reward = reward;
        this.type = type;
        this.countBullet = countBullet;
        this.bulletTouched = bulletTouched;
        this.node = node;

    }
    
    public boolean goalReached() {
        return x == 640 && y == 320;
    }
    
    @Override
    public void takeDamage(int amount) {
        hp -= amount;
    }

    @Override
    public boolean isDead() {
        return hp <= 0;
    }

}
