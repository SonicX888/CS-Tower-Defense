package com.mycompany.cs_tower_defense.gui;

import com.mycompany.cs_tower_defense.path.Astar;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * This class initializes the creation of the tower buttons
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class TowerButtons {

    // button that selects the first type of tower
    private JButton tower1Button;

    /**
     * Creates the button of the first tower at the bottom of the window.
     */
    public TowerButtons() {
        // loads the image of the tower
        ImageIcon tower1Image = new ImageIcon(getClass().getResource(
                "/images/computer.png"
        ));
        // creates the button with the image of the tower
        tower1Button = new JButton(tower1Image);
        // removes the outline drawn around the image when it is clicked
        tower1Button.setFocusPainted(false);
        // places the button in the middle column, in the row below the grid
        tower1Button.setBounds(Astar.COLUMNS / 2 * Astar.CELL_SIZE,
                (Astar.ROWS + 1) * Astar.CELL_SIZE,
                Astar.CELL_SIZE, Astar.CELL_SIZE);
        // removes the border of the button
        tower1Button.setBorderPainted(false);

        // selects the first tower when the button is clicked
        tower1Button.addActionListener(e -> {
            Game.selectedTowerType = "Tower 1";
        });

    }

    /**
     * Returns the button of the first tower.
     *
     * @return the button of the first tower
     */
    public JButton getTower1Button() {
        return tower1Button;
    }

}
