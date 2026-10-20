package com.mycompany.cs_tower_defense.towers;

import com.mycompany.cs_tower_defense.gui.Economy;
import com.mycompany.cs_tower_defense.gui.Game;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * This class is responsible for upgrading the towers and displaying
 * the upgrade frame
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class TowerUpgrade extends JFrame {
 
    /**
     * Creates and displays the tower upgrade frame. Upgrades the tower if the
     * player has enough gold.
     *
     * @param t tower to upgrade
     * @param x x position of the frame on the screen
     * @param y y position of the frame on the screen
     */
    public TowerUpgrade(Tower t, int x, int y) {
        //Creates the upgrade frame
        setTitle("Tower Upgrade");
        setSize(180, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(x, y);
        // puts the elements of the frame in 3 rows
        setLayout(new GridLayout(3, 1, 10, 10));
        // removes the title bar and the borders of the frame
        setUndecorated(true);
 
        // cost of the next upgrade (starts at 10 gold and doubles at each
        // level)
        int upgradeCost = 10 * (int) Math.pow(2, t.towerLevel);
 
        // creates the labels and the button of the frame
        JLabel level = new JLabel("Tower Level: " + t.towerLevel, SwingConstants.CENTER);
        JLabel cost = new JLabel("Upgrade Cost: " + upgradeCost, SwingConstants.CENTER);
        JButton upgradeButton = new JButton("Upgrade");
 
        // adds the labels and the button to the frame
        // (towers can be upgraded infinitely, so there is no max level)
        add(level);
        add(cost);
        add(upgradeButton);
 
        //Terminates the upgrade frame if the player clicks anywhere else
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                // nothing happens when the frame gets the focus
            }
 
            @Override
            public void windowLostFocus(WindowEvent e) {
                // closes the frame
                dispose();
            }
        });
 
        //Upgrades the tower and deducts gold
        //Then terminates the frame
        upgradeButton.addActionListener(l -> {
            // checks if the player has enough gold
            if (Economy.gold >= upgradeCost) {
                // removes the cost of the upgrade from the gold
                Economy.gold -= upgradeCost;
                // increases the level of the tower
                t.setTowerLevel(t.towerLevel + 1);
                // adds 1 damage to the bullets of the tower
                t.setDamage(t.damage + 1);
                // adds 16 pixels to the range, up to 192 pixels
                t.setRange(Math.min(t.range + 16, 192));
                // removes 250 milliseconds between two shots, down to 250
                // milliseconds
                t.setFireRate(Math.max(t.fireRate - 250, 250));
                // closes the frame
                dispose();
            } else {
                // tells the player there is not enough gold
                Game.gameWindow.popUpText("You don't have enough gold");
            }
        });
        // shows the frame
        setVisible(true);
    }
}
 