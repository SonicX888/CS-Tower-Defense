package com.mycompany.cs_tower_defense;

/**
 *
 * @author kyllianlim
 */
public class BasicEnemy extends Enemy {
    
    public BasicEnemy(int x, int y, int hp, int speed, int reward, Node node) {
        super(x, y, hp, speed, reward, node, "/images/grass_block.png");
    }
    
}
