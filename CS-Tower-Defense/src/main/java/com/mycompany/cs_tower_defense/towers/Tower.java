package com.mycompany.cs_tower_defense.towers;

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
 
    // position of the tower on the window
    int x;
    int y;
    // unique number of the tower
    int id;
    // level of the tower (starts at 0 and has no maximum)
    int towerLevel;
    // time when the tower shot for the last time
    long lastShot;
    // number of milliseconds between two shots
    long fireRate;
    // hp removed from an enemy by each bullet of the tower
    int damage;
    // distance in pixels where the tower can shoot an enemy
    long range;
    // image of the tower
    JLabel sprite;
 
    /**
     * Creates a tower with its position, level, last shot, fire rate, damage
     * and range.
     *
     * @param x x position of the tower on the window
     * @param y y position of the tower on the window
     * @param towerLevel level of the tower
     * @param lastShot time when the tower shot for the last time
     * @param fireRate number of milliseconds between two shots
     * @param damage hp removed from an enemy by each bullet of the tower
     * @param range distance in pixels where the tower can shoot an enemy
     */
    public Tower(int x, int y, int towerLevel, long lastShot, long fireRate,
            int damage, long range) {
        this.x = x;
        this.y = y;
        this.towerLevel = towerLevel;
        this.lastShot = lastShot;
        this.fireRate = fireRate;
        this.damage = damage;
        this.range = range;
    }
 
    /**
     * Returns the time when the tower shot for the last time.
     *
     * @return the time of the last shot in milliseconds
     */
    public long getLastShot() {
        return lastShot;
    }
 
    /**
     * Sets the time when the tower shot for the last time.
     *
     * @param lastShot time of the last shot in milliseconds
     */
    public void setLastShot(long lastShot) {
        this.lastShot = lastShot;
    }
 
    /**
     * Returns the time between two shots.
     *
     * @return the number of milliseconds between two shots
     */
    public long getFireRate() {
        return fireRate;
    }
 
    /**
     * Sets the time between two shots.
     *
     * @param fireRate number of milliseconds between two shots
     */
    public void setFireRate(long fireRate) {
        this.fireRate = fireRate;
    }
 
    /**
     * Returns the x position of the tower.
     *
     * @return the x position of the tower on the window
     */
    public int getX() {
        return x;
    }
 
    /**
     * Sets the x position of the tower.
     *
     * @param x new x position of the tower on the window
     */
    public void setX(int x) {
        this.x = x;
    }
 
    /**
     * Returns the y position of the tower.
     *
     * @return the y position of the tower on the window
     */
    public int getY() {
        return y;
    }
 
    /**
     * Sets the y position of the tower.
     *
     * @param y new y position of the tower on the window
     */
    public void setY(int y) {
        this.y = y;
    }
 
    /**
     * Returns the id of the tower.
     *
     * @return the unique number of the tower
     */
    public int getId() {
        return id;
    }
 
    /**
     * Sets the id of the tower.
     *
     * @param id unique number of the tower
     */
    public void setId(int id) {
        this.id = id;
    }
 
    /**
     * Returns the level of the tower.
     *
     * @return the level of the tower
     */
    public int getTowerLevel() {
        return towerLevel;
    }
 
    /**
     * Sets the level of the tower.
     *
     * @param towerLevel new level of the tower
     */
    public void setTowerLevel(int towerLevel) {
        this.towerLevel = towerLevel;
    }
 
    /**
     * Returns the damage of the tower.
     *
     * @return the hp removed from an enemy by each bullet of the tower
     */
    public int getDamage() {
        return damage;
    }
 
    /**
     * Sets the damage of the tower.
     *
     * @param damage new hp removed from an enemy by each bullet of the tower
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }
 
    /**
     * Returns the range of the tower.
     *
     * @return the distance in pixels where the tower can shoot an enemy
     */
    public long getRange() {
        return range;
    }
 
    /**
     * Sets the range of the tower.
     *
     * @param range new distance in pixels where the tower can shoot an enemy
     */
    public void setRange(long range) {
        this.range = range;
    }
}