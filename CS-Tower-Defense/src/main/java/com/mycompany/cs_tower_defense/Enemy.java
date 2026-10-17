package com.mycompany.cs_tower_defense;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 *
 * @author kyllianlim
 */
public abstract class Enemy implements Damageable {

    int x;
    int y;
    int hp;
    int speed;
    int reward;
    Node node;
    JLabel sprite;

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
        this.sprite = new JLabel(new ImageIcon(Enemy.class.getResource(imagePath)));
        this.sprite.setSize(sprite.getPreferredSize());

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
