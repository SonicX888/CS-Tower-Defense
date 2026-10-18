package com.mycompany.cs_tower_defense;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JLabel;

/**
 * Class that manages the time survived
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class TimeManager extends GameManager {

    // time when the game started
    long timeStart = System.currentTimeMillis();
    // current time
    long now;
    // seconds and minutes survived written with 2 digits
    static String stringSeconds;
    static String stringMinutes;
    // label that shows the time on the window
    JLabel amount;
    // label of the previous update that has to be removed
    JLabel oldAmount;

    /**
     * Method that computes the time survived, creates a new label with it and
     * is called infinitely in the class Main.
     */
    @Override
    void update() {
        now = System.currentTimeMillis();
        // keeps the old label so it can be removed
        oldAmount = amount;
        // computes the seconds and minutes since the start of the game
        long seconds = ((now - timeStart) / 1000) % 60;
        long minutes = (now - timeStart) / 1000 / 60;
        // adds a 0 in front of the seconds if they are lower than 10
        if (seconds < 10) {
            stringSeconds = "0" + Long.toString(seconds);
        } else {
            stringSeconds = Long.toString(seconds);
        }
        // adds a 0 in front of the minutes if they are lower than 10
        if (minutes < 10) {
            stringMinutes = "0" + Long.toString(minutes);
        } else {
            stringMinutes = Long.toString(minutes);
        }
        // creates the label with the time survived
        amount = new JLabel(
                "Time Survived: " + stringMinutes + ":" + stringSeconds
        );
        amount.setLocation(32, 656);
        // doubles the size of the label
        Dimension size = amount.getPreferredSize();
        size.height *= 2;
        size.width *= 2;
        amount.setSize(size);
        amount.setForeground(Color.white);
    }

    /**
     * Method that draws the time on the window and is called infinitely in the
     * class Main.
     *
     * @param frame window of the game where the time is drawn
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
}
