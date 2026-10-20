package com.mycompany.cs_tower_defense;

import java.awt.Color;
import javax.swing.JLabel;

/**
 * Class that handles the gold exchange(economy) of the game
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class Economy extends GameManager {

    public static int gold = 20;
    JLabel panel = new JLabel("Gold: " + gold);

    public Economy() {
        panel.setSize(panel.getPreferredSize());
    }

    @Override
    void update() {
        panel.setText("Gold: " + gold);
        panel.setSize(panel.getPreferredSize());
        panel.setForeground(Color.white);
    }

    @Override
    void draw(Game frame) {
        if (panel.getParent() == null) {
            frame.getLayeredPane().add(panel, Integer.valueOf(2));

        }
        panel.setLocation(9 * 64, 664);

    }

}
