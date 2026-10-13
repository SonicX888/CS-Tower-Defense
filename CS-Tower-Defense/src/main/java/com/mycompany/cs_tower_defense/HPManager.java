package com.mycompany.cs_tower_defense;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JLabel;

/**
 *
 * @author kyllianlim
 */
public class HPManager extends GameManager implements Damageable {
    
    int hp = 100;
    JLabel amount;
    JLabel oldAmount;
    
    @Override
    void update() {
        oldAmount = amount;
        amount = new JLabel("HP: " + hp);
        amount.setLocation(328, 24);
        amount.setSize(amount.getPreferredSize());
        amount.setForeground(Color.white);
    }
    
    @Override
    void draw(Game frame) {
        if (amount.getParent() == null) {
            if (oldAmount != null){
                Container parent = oldAmount.getParent();
                if (parent != null){
                    parent.remove(oldAmount);
                    parent.repaint();
                }
            }
            frame.getLayeredPane().add(amount, Integer.valueOf(2));
        }
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
