/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tue_tds;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author omero
 */
public class TowerButtons {

    
    private JButton tower1Button;
    
    public TowerButtons(){
        tower1Button();
    }
    public void tower1Button(){
        ImageIcon tower1Image = new ImageIcon(getClass().getResource("/assets/computer.png"));
        tower1Button = new JButton(tower1Image);
        tower1Button.setFocusPainted(false);
        tower1Button.setBounds(5 * 64, 10 * 64, 64, 64);
        tower1Button.setBorderPainted(false);
        
        tower1Button.addActionListener( e -> {
            Game.selectedTowerType = "Tower 1";
            System.out.println("Tower 1 is selected");
        });
        
        
    
    }
    
    
    
    public JButton getTower1Button() {
        return tower1Button;
    }
    
    
}
