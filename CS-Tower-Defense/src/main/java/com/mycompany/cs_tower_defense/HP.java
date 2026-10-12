package com.mycompany.cs_tower_defense;

/**
 *
 * @author kyllianlim
 */
public class HP implements Damageable {
    
    int hp = 100;
    
    @Override
    public void takeDamage(int amount) {
        hp -= amount;
    }
    
    @Override
    public boolean isDead() {
        return hp <= 0;
    }
}
