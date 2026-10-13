/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tue_tds.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author omero
 */
public class KeyboardInputs implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("Pressed A");
        } else if (e.getKeyCode() == KeyEvent.VK_B) {
            System.out.println("Pressed B");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
