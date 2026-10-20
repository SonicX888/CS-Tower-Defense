package com.mycompany.cs_tower_defense.gui;

import com.mycompany.cs_tower_defense.GameManager;
import java.awt.Color;
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
    // seconds and minutes survived written with 2 digits (starts at 00 so
    // they can be read before the first update)
    static String stringSeconds = "00";
    public static String stringMinutes = "00";
    // label that shows the time on the window (created once and updated)
    JLabel amount = new JLabel();

    /**
     * Creates the time manager and places the label at the bottom left of the
     * window.
     */
    public TimeManager() {
        amount.setLocation(32, 656);
        amount.setForeground(Color.white);
    }

    /**
     * Method that computes the time survived, writes it in the label and is
     * called infinitely in the class Main.
     */
    @Override
    public void update() {
        now = System.currentTimeMillis();
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
        // writes the time survived in the label
        amount.setText("Time Survived: " + stringMinutes + ":" + stringSeconds);
        // doubles the size of the label
        Dimension size = amount.getPreferredSize();
        size.height *= 2;
        size.width *= 2;
        amount.setSize(size);
    }

    /**
     * Method that draws the time on the window and is called infinitely in the
     * class Main.
     *
     * @param frame window of the game where the time is drawn
     */
    @Override
    public void draw(Game frame) {
        // draws the label if it is not on the window yet
        if (amount.getParent() == null) {
            frame.getLayeredPane().add(amount, Integer.valueOf(2));
        }
    }
}
