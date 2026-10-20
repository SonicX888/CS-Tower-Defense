package com.mycompany.cs_tower_defense;

import com.mycompany.cs_tower_defense.enemies.EnemyManager;
import com.mycompany.cs_tower_defense.gui.Economy;
import com.mycompany.cs_tower_defense.gui.Game;
import com.mycompany.cs_tower_defense.gui.GameOver;
import com.mycompany.cs_tower_defense.gui.HPManager;
import com.mycompany.cs_tower_defense.gui.TimeManager;
import com.mycompany.cs_tower_defense.path.Astar;
import com.mycompany.cs_tower_defense.towers.TowerManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Main class that starts the game and runs it
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class Main {

    // window of the game
    static Game frame = new Game();
    // list where each manager is stored
    static ArrayList<GameManager> managers = new ArrayList<GameManager>();
    // managers of the game
    static EnemyManager enemies = new EnemyManager();
    public static TowerManager towers = new TowerManager();
    public static TimeManager time = new TimeManager();
    public static HPManager hp = new HPManager();
    static Economy economy = new Economy();
    // checks if the game over window was already shown
    static boolean isGameOver = false;
    // music of the game
    static Sound mainTheme = new Sound("/sounds/PVZ_theme.wav");
    static Sound gameOverTheme = new Sound("/sounds/Game_over_theme.wav");

    /**
     * Starts a timer that updates and draws the game every 16 milliseconds
     * (about 60 times per second) and shows the game over window when the base
     * has no hp left.
     */
    static void run() {
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // checks if the base still has hp
                if (hp.hp > 0) {
                    // updates every manager
                    for (GameManager m : managers) {
                        m.update();
                    }
                    // draws every manager
                    for (GameManager m : managers) {
                        m.draw(frame);
                    }
                    // refreshes the window
                    frame.getLayeredPane().repaint();
                } else {
                    // checks if the game over window was not shown yet
                    if (!isGameOver) {
                        // hides the game and shows the game over window
                        GameOver gameOverFrame = new GameOver();
                        frame.setVisible(false);
                        gameOverFrame.setLocationRelativeTo(null);
                        gameOverFrame.setVisible(true);
                        // stops the main theme and plays the game over theme
                        mainTheme.stop();
                        gameOverTheme.play();
                        gameOverTheme.loop();
                        // makes sure the game over window is shown only once
                        isGameOver = true;
                    }
                }
            }
        });
        timer.start();
    }

    /**
     * Opens the window, prepares the game and starts it.
     *
     * @param args arguments given when the program starts (not used)
     */
    public static void main(String[] args) {
        // plays the main theme in a loop
        mainTheme.play();
        mainTheme.loop();
        // centers the window on the screen and shows it
        frame.setLocationRelativeTo(null);
        frame.initInputs();
        frame.setVisible(true);
        // adds each manager to the list
        managers.add(enemies);
        managers.add(towers);
        managers.add(time);
        managers.add(hp);
        managers.add(economy);
        // creates the grid of the map
        Astar.gridCreation();
        // loads the damage sounds
        hp.soundInit();
        // starts the game
        run();

    }
}
