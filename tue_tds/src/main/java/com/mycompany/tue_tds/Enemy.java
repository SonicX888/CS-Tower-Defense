package com.mycompany.tue_tds;

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
    JLabel sprite;
    
    public Enemy(
            int x, 
            int y, 
            int hp, 
            int speed, 
            int reward, 
            int type, 
            int countBullet, 
            boolean bulletTouched
    ) {
        
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.speed = speed;
        this.reward = reward;
        this.type = type;
        this.countBullet = countBullet; 
        this.bulletTouched = bulletTouched;
        
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
