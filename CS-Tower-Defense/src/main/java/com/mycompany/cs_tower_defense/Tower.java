package com.mycompany.cs_tower_defense;

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
    long lastShot;
    long fireRate;
    JLabel sprite;
    
    public static class TowerTypes{
        public static final int TOWER_A = 0;
        public static final int TOWER_B = 1;
        public static final int TOWER_C = 2;
    }
    
    public Tower(int x, int y, int towerType, long lastShot, long fireRate){
        this.x = x;
        this.y = y;
        this.towerType = towerType;
        this.lastShot = lastShot;
        this.fireRate = fireRate;
    }

    public long getLastShot() {
        return lastShot;
    }

    public void setLastShot(long lastShot) {
        this.lastShot = lastShot;
    }

    public long getFireRate() {
        return fireRate;
    }

    public void setFireRate(long fireRate) {
        this.fireRate = fireRate;
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
