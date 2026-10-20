package com.mycompany.cs_tower_defense.inputs;

import com.mycompany.cs_tower_defense.Main;
import com.mycompany.cs_tower_defense.gui.Game;
import com.mycompany.cs_tower_defense.towers.Tower;
import com.mycompany.cs_tower_defense.towers.TowerManager;
import com.mycompany.cs_tower_defense.towers.TowerUpgrade;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Class that takes the mouse input of the player
 *
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class MouseInputs implements MouseListener, MouseMotionListener {

    // column and row of the cell clicked by the player
    public static int x;
    public static int y;

    /**
     * Returns the column of the cell clicked by the player.
     *
     * @return the column of the cell clicked
     */
    public static int getX() {
        return x;
    }

    /**
     * Returns the row of the cell clicked by the player.
     *
     * @return the row of the cell clicked
     */
    public static int getY() {
        return y;
    }

    /**
     * Not used, but required by the MouseListener interface. The clicks are
     * handled in mousePressed because on a Mac a click is ignored if the mouse
     * moves a little between the press and the release.
     *
     * @param e mouse event of the click
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Builds a tower when the player left clicks and opens the upgrade frame
     * when the player right clicks on a tower.
     *
     * @param e mouse event that contains the position and the button pressed
     */
    @Override
    public void mousePressed(MouseEvent e) {

        // converts the position of the click into a column and a row of the
        // grid (each cell is 64 pixels)
        x = e.getX() / 64;
        y = e.getY() / 64;

        // Calculates where the player left clicked
        // Calls the method that builds towers
        if (e.getButton() == MouseEvent.BUTTON1) {
            // checks if the player selected a tower before clicking
            if (!"null".equals(Game.selectedTowerType)) {
                Main.towers.buildTower();
                // unselects the tower so the next click doesn't build another
                Game.selectedTowerType = "null";
            }
            // If the player right clicks on a tower
            // Finds that tower in the list
            // Then calls the method to upgrade the tower
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            // goes through each tower
            for (Tower t : TowerManager.towerList) {
                // checks if the tower is on the cell clicked
                if (t.getX() / 64 == x && t.getY() / 64 == y) {
                    // opens the upgrade frame just above the mouse
                    new TowerUpgrade(t, e.getLocationOnScreen().x - 90,
                            e.getLocationOnScreen().y - 132);
                    // stops searching because the tower was found
                    break;
                }
            }
        }
    }

    /**
     * Not used, but required by the MouseListener interface.
     *
     * @param e mouse event of the release
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Not used, but required by the MouseListener interface.
     *
     * @param e mouse event when the mouse enters the window
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Not used, but required by the MouseListener interface.
     *
     * @param e mouse event when the mouse leaves the window
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Not used, but required by the MouseMotionListener interface.
     *
     * @param e mouse event of the drag
     */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Not used, but required by the MouseMotionListener interface.
     *
     * @param e mouse event of the movement
     */
    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
