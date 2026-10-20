package com.mycompany.cs_tower_defense;

import javax.swing.JLabel;

/**
 * Class that initializes the towers
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class Tower {

    int x;
    int y;
    int id;
    int towerLevel;
    long lastShot;
    long fireRate;
    JLabel sprite;

    public Tower(int x, int y, int towerLevel, long lastShot, long fireRate) {
        this.x = x;
        this.y = y;
        this.towerLevel = towerLevel;
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

    public int getTowerLevel() {
        return towerLevel;
    }

    public void setTowerLevel(int towerLevel) {
        this.towerLevel = towerLevel;
    }
}
