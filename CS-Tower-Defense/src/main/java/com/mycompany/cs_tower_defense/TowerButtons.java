package com.mycompany.cs_tower_defense;

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

    private JButton tower1Button;

    public TowerButtons() {
        ImageIcon tower1Image = new ImageIcon(getClass().getResource("/images/computer.png"));
        tower1Button = new JButton(tower1Image);
        tower1Button.setFocusPainted(false);
        tower1Button.setBounds(5 * 64, 10 * 64, 64, 64);
        tower1Button.setBorderPainted(false);

        tower1Button.addActionListener(e -> {
            Game.selectedTowerType = "Tower 1";
        });

    }

    public JButton getTower1Button() {
        return tower1Button;
    }

}
