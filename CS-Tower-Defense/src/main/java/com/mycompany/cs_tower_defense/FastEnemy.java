package com.mycompany.cs_tower_defense;

/**
 *
 * @author kyllianlim
 */
public class FastEnemy extends Enemy {
    
    public FastEnemy(int x, int y, int hp, int speed, int reward, Node node) {
        super(x, y, hp/2, speed*2, reward*2, node, "/images/shower.png");
    }
    
}
