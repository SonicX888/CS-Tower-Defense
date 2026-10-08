package com.mycompany.tue_tds;

import javax.swing.JLabel;

/**
 *
 * @author kyllianlim
 */
public class Tower {
       
    int x;
    int y;
    int id;
    int towerType;
    JLabel sprite;
    
    public static class TowerTypes{
        public static final int TOWER_A = 0;
        public static final int TOWER_B = 1;
        public static final int TOWER_C = 2;
    }
    
    public Tower(int x, int y, int id, int towerType){
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTowerType() {
        return towerType;
    }

    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }
}
