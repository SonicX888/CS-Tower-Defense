package com.mycompany.tue_tds;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 *
 * @author kyllianlim
 */
public class TowerManager extends GameManager {
    
    ArrayList<Tower> towerTypeList = new ArrayList<Tower>();
    private Tower tower;
    BufferedImage[] towerImages = new BufferedImage[1];
    
    public TowerManager(){
        loadImage();
        initTower();
        
    }
    
    @Override
    void update() {
        
    }
    
    @Override
    void draw(Game frame){
        
    }
    
    //Set location of the tower(only for testing)
    private void initTower(){
        tower = new Tower(3 * 64, 64, 0, 0);
    }
    //Locates the location of the image and saves it into the array of tower type list
    private void loadImage(){
        URL url = getClass().getResource("/assets/towerDefense_tile291.png");
        try {
            BufferedImage img = ImageIO.read(url);
            towerImages[0] = img;
        } catch (IOException e){
            System.out.println("Image can not be found");
        }
            
        
    }
    // Draws the tower
    public void drawTower(Graphics g){
        g.drawImage(towerImages[0], 0, 0, null);
    }
        
}
