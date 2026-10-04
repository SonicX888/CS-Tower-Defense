package com.mycompany.tue_tds;

import javax.swing.JLabel;


/**
 *
 * @author kyllianlim
 */
public class Enemy {
    
    int x;
    int y;
    int hp;
    int speed;
    int money;
    int type;
    int count_bullet; 
    boolean bullet_touched;
    JLabel sprite;
    
    public Enemy(
            int x, 
            int y, 
            int hp, 
            int speed, 
            int money, 
            int type, 
            int countBullet, 
            boolean bulletTouched
    ) {
        
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.speed = speed;
        this.money = money;
        this.type = type;
        this.count_bullet = countBullet; 
        this.bullet_touched = bulletTouched;
        
    }
    
}
