package com.mycompany.cs_tower_defense;

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

    //Creates and displays the tower upgrade frame
    //Upgrades the tower if the the player has enough gold
    public TowerUpgrade(Tower t, int x, int y) {
        //Creates the upgrade frame
        setTitle("Tower Upgrade");
        setSize(180, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(x, y);
        setLayout(new GridLayout(3, 1, 10, 10));
        setUndecorated(true);

        int upgradeCost = t.towerLevel * 10 + 10;

        JLabel level = new JLabel("Tower Level: " + t.towerLevel, SwingConstants.CENTER);
        JLabel cost = new JLabel("Upgrade Cost: " + upgradeCost, SwingConstants.CENTER);
        JButton upgradeButton = new JButton("Upgrade");
        JLabel maxLevel = new JLabel("Tower is already at max level", SwingConstants.CENTER);

        //Maximum level a tower can reach is 3
        //Therefore, does not allow to upgrade if the tower is at level 3
        if (t.towerLevel == 3) {
            add(level);
            add(maxLevel);
        } else {
            add(level);
            add(cost);
            add(upgradeButton);
        }

        //Terminates the upgrade frame if the player clicks anywhere else
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                dispose();
            }
        });

        //Upgrades the tower and deducts gold
        //Then terminates the frame
        // With each upgrade towers fire two times faster
        upgradeButton.addActionListener(l -> {
            if (Economy.gold >= upgradeCost && t.towerLevel < 3) {
                Economy.gold -= upgradeCost;
                t.setTowerLevel(t.towerLevel + 1);
                t.setFireRate(t.fireRate / 2);
                System.out.println("Tower Upgraded");
                dispose();
            } else {
                Game.gameWindow.popUpText("You don't have enough gold");
            }
        });
        setVisible(true);
    }
}
