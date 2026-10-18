package com.mycompany.cs_tower_defense;

/**
 * Abstract class that every manager of the game extends
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public abstract class GameManager {

    /**
     * Method that updates the manager and is called infinitely in the class
     * Main.
     */
    abstract void update();

    /**
     * Method that draws the manager on the window and is called infinitely in
     * the class Main.
     *
     * @param frame window of the game where the manager is drawn
     */
    abstract void draw(Game frame);

}
