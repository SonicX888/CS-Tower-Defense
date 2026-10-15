package com.mycompany.cs_tower_defense;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author kyllianlim
 */
public class TimeManager extends GameManager {
    
    long timeStart = System.currentTimeMillis();
    long now;
    static String stringSeconds;
    static String stringMinutes;
    JLabel amount;
    JLabel oldAmount;
    
    @Override
    void update() {
        now = System.currentTimeMillis();
        oldAmount = amount;
        long seconds = ((now - timeStart) / 1000) % 60;
        long minutes = (now - timeStart) / 1000 / 60;
        if (seconds < 10) {
            stringSeconds = "0" + Long.toString(seconds);
        } else {
            stringSeconds = Long.toString(seconds);
        }
        if (minutes < 10) {
            stringMinutes = "0" + Long.toString(minutes);
        } else {
            stringMinutes = Long.toString(minutes);
        }
        amount = new JLabel("Time Survived: " + stringMinutes + ":" + stringSeconds);
        amount.setLocation(32, 656);
        Dimension size = amount.getPreferredSize();
        size.height *= 2;
        size.width *= 2;
        amount.setSize(size);
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
}
