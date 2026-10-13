/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cs_tower_defense.inputs;

/**
 *
 * @author omero
 */
import com.mycompany.cs_tower_defense.Game;
import com.mycompany.cs_tower_defense.TowerManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener{

    public static int x;
    public static int y;
    
    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getButton() == MouseEvent.BUTTON1){
            if (!"null".equals(Game.selectedTowerType)){
                x = e.getX() / 64;
                y = e.getY() / 64;
                Game.selectedTowerType = "null";
                System.out.print(x);
                System.out.println(y);
            }
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    
}
