package com.mycompany.cs_tower_defense.gui;

import com.mycompany.cs_tower_defense.Damageable;
import com.mycompany.cs_tower_defense.GameManager;
import com.mycompany.cs_tower_defense.Sound;
import java.awt.Color;
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
    public int hp = 100;
    // label that shows the hp on the window (created once and updated)
    JLabel amount = new JLabel("HP: " + hp);
    // sounds played when the base takes damage
    Sound[] damageArray = new Sound[5];
    // index of the next damage sound to be played
    int next = 0;

    /**
     * Creates the hp manager and places the label at the top of the window.
     */
    public HPManager() {
        amount.setLocation(328, 24);
        amount.setSize(amount.getPreferredSize());
        amount.setForeground(Color.white);
    }

    /**
     * Loads the damage sound 5 times so several sounds can play at the same
     * time.
     */
    public void soundInit() {
        for (int i = 0; i < damageArray.length; i++) {
            damageArray[i] = new Sound("/sounds/Base_damage.wav");
        }
    }

    /**
     * Method that writes the current hp in the label and is called infinitely
     * in the class Main.
     */
    @Override
    public void update() {
        // writes the current hp in the label
        amount.setText("HP: " + hp);
        // gives the label the size of its new text
        amount.setSize(amount.getPreferredSize());
    }

    /**
     * Method that draws the hp on the window and is called infinitely in the
     * class Main.
     *
     * @param frame window of the game where the hp is drawn
     */
    @Override
    public void draw(Game frame) {
        // draws the label if it is not on the window yet
        if (amount.getParent() == null) {
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
        next = (next + 1) % damageArray.length;
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
