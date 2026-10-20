package com.mycompany.cs_tower_defense.gui;

import com.mycompany.cs_tower_defense.GameManager;
import com.mycompany.cs_tower_defense.path.Astar;
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

    // gold of the player at the start of the game
    public static int gold = 30;
    // label that shows the gold on the window
    JLabel panel = new JLabel("Gold: " + gold);

    /**
     * Creates the economy and gives the label the size of its text.
     */
    public Economy() {
        panel.setSize(panel.getPreferredSize());
    }

    /**
     * Method that writes the current gold in the label and is called infinitely
     * in the class Main.
     */
    @Override
    public void update() {
        // writes the current gold in the label
        panel.setText("Gold: " + gold);
        // gives the label the size of its new text
        panel.setSize(panel.getPreferredSize());
        panel.setForeground(Color.white);
    }

    /**
     * Method that draws the gold on the window and is called infinitely in the
     * class Main.
     *
     * @param frame window of the game where the gold is drawn
     */
    @Override
    public void draw(Game frame) {
        // draws the label if it is not on the window yet
        if (panel.getParent() == null) {
            frame.getLayeredPane().add(panel, Integer.valueOf(2));

        }
        // places the label at the bottom of the window
        panel.setLocation(9 * Astar.CELL_SIZE, 664);

    }

}
