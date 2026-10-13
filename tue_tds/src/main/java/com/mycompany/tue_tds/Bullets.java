/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tue_tds;

import javax.swing.JLabel;

/**
 *
 * @author omero
 */
public class Bullets {
    int x;
    int y;
    int damage;
    Enemy target;
    int speed;
    JLabel sprite;
    
    public Bullets(int x, int y, int damage, Enemy target, int speed){
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.target = target;
        this.speed = speed;
    }
    
    
}
