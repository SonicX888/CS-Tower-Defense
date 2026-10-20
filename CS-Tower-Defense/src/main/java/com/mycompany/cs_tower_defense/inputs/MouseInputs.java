package com.mycompany.cs_tower_defense.inputs;

/**
 * Class that takes the mouse input of the player
 *
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
import com.mycompany.cs_tower_defense.Game;
import com.mycompany.cs_tower_defense.Main;
import com.mycompany.cs_tower_defense.Tower;
import com.mycompany.cs_tower_defense.TowerManager;
import com.mycompany.cs_tower_defense.TowerUpgrade;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    public static int x;
    public static int y;

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        x = e.getX() / 64;
        y = e.getY() / 64;

        // Calculates where the player left clicked
        // Calls the method that build towers
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (!"null".equals(Game.selectedTowerType)) {
                Main.towers.buildTower();
                Game.selectedTowerType = "null";
            }
            // If the player right clicks on a tower
            // Founds that tower in the list
            // Then calls the method to upgrade the tower
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            for (Tower t : TowerManager.towerList) {
                if (t.getX() / 64 == x && t.getY() / 64 == y) {
                    new TowerUpgrade(t, e.getLocationOnScreen().x - 90,
                            e.getLocationOnScreen().y - 132);
                    break;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
