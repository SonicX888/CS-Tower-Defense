package com.mycompany.tue_tds;

/**
 *
 * @author kyllianlim
 */

import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    
    static Game frame = new Game();
    static ArrayList<GameManager> managers = new ArrayList<GameManager>();
    static EnemyManager enemies = new EnemyManager();
    static TowerManager towers = new TowerManager();
    
    static void run() {
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (GameManager m : managers) {
                    m.update();
                }
                for (GameManager m : managers) {
                    m.draw(frame);
                }
                frame.getLayeredPane().repaint();
            }
        });
        timer.start();
    }
    
    public static void main(String[] args) {
        frame.setLocationRelativeTo(null);
        frame.initInputs();
        frame.initInputs(); 
        frame.setVisible(true);
        managers.add(enemies);
        managers.add(towers);
        Astar.gridCreation();
        run();
        
    }
}
