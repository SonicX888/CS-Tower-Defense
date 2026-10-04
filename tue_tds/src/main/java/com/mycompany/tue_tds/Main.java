package com.mycompany.tue_tds;

/**
 *
 * @author kyllianlim
 */

public class Main {
    
    static Game frame = new Game();
    static Enemies enemy = new Enemies();
    
    private static void update() {
        enemy.update();
    }
    
    private static void draw() {
        enemy.draw(frame);
    }
    
    private static void run() {
        while (true) {
            update();
            draw();
        }
    }
    
    public static void main(String[] args) {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        run();
    }
}
