package com.mycompany.cs_tower_defense;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JLabel;

/**
 * Class that manages the hp of the base
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class HPManager extends GameManager implements Damageable {

    // hp of the base at the start of the game
    int hp = 100;
    // label that shows the hp on the window
    JLabel amount;
    // label of the previous update that has to be removed
    JLabel oldAmount;
    // sounds played when the base takes damage
    Sound[] damageArray = new Sound[5];
    // index of the next damage sound to be played
    int next = 0;

    /**
     * Loads the damage sound 5 times so several sounds can play at the same
     * time.
     */
    void soundInit() {
        for (int i = 0; i < damageArray.length; i++) {
            damageArray[i] = new Sound("/sounds/Base_damage.wav");
        }
    }

    /**
     * Method that creates a new label with the current hp and is called
     * infinitely in the class Main.
     */
    @Override
    void update() {
        // keeps the old label so it can be removed
        oldAmount = amount;
        // creates the label with the current hp
        amount = new JLabel("HP: " + hp);
        amount.setLocation(328, 24);
        amount.setSize(amount.getPreferredSize());
        amount.setForeground(Color.white);
    }

    /**
     * Method that draws the hp on the window and is called infinitely in the
     * class Main.
     *
     * @param frame window of the game where the hp is drawn
     */
    @Override
    void draw(Game frame) {
        // checks if the new label is not on the window yet
        if (amount.getParent() == null) {
            // removes the old label from the window
            if (oldAmount != null) {
                Container parent = oldAmount.getParent();
                if (parent != null) {
                    parent.remove(oldAmount);
                    parent.repaint();
                }
            }
            // draws the new label
            frame.getLayeredPane().add(amount, Integer.valueOf(2));
        }
    }

    /**
     * Decreases the hp of the base and plays a damage sound.
     *
     * @param amount number of hp to remove from the base
     */
    @Override
    public void takeDamage(int amount) {
        hp -= amount;
        // plays the next damage sound
        damageArray[next].play();
        // goes to the next sound and back to the first one after the last
        next = (next + 1) % 5;
    }

    /**
     * Checks if the base has no hp left.
     *
     * @return true if the base is destroyed, false otherwise
     */
    @Override
    public boolean isDead() {
        return hp <= 0;
    }
}
