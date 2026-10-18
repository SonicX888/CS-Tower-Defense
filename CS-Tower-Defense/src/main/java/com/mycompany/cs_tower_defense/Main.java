package com.mycompany.cs_tower_defense;

/**
 *
 * @author kyllianlim
 */
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

    static Game frame = new Game();
    static ArrayList<GameManager> managers = new ArrayList<GameManager>();
    static EnemyManager enemies = new EnemyManager();
    public static TowerManager towers = new TowerManager();
    static TimeManager time = new TimeManager();
    static HPManager hp = new HPManager();
    static boolean isGameOver = false;
    static Sound mainTheme = new Sound("/sounds/PVZ_theme.wav");
    static Sound gameOverTheme = new Sound("/sounds/Game_over_theme.wav");
    
    static void run() {
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hp.hp > 0) {
                    for (GameManager m : managers) {
                        m.update();
                    }
                    for (GameManager m : managers) {
                        m.draw(frame);
                    }
                    frame.getLayeredPane().repaint();
                } else {
                    if (!isGameOver) {
                        GameOver GameOverFrame = new GameOver();
                        frame.setVisible(false);
                        GameOverFrame.setLocationRelativeTo(null);
                        GameOverFrame.setVisible(true);
                        mainTheme.stop();
                        gameOverTheme.play();
                        gameOverTheme.loop();
                        isGameOver = true;
                    }
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        frame.setLocationRelativeTo(null);
        frame.initInputs();
        frame.setVisible(true);
        managers.add(enemies);
        managers.add(towers);
        managers.add(time);
        managers.add(hp);
        Astar.gridCreation();
        hp.soundInit();
        mainTheme.play();
        mainTheme.loop();
        run();

    }
}
