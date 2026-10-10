package com.mycompany.tue_tds;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author kyllianlim
 */
public class TowerManager extends GameManager {
    
    ArrayList<Tower> towerList = new ArrayList<Tower>();
    private Tower tower;
    /**
    //Set location of the tower(only for testing)
    private void initTower(){
    private void buildTower(){
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
        towerList.add(tower);
        tower.sprite = new JLabel(new ImageIcon(TowerManager.class.getResource("/assets/towerDefense_tile291.png")));
        tower.sprite.setSize(tower.sprite.getPreferredSize());
    }
    **/

    @Override
    void update() {
        
        // buildTower();
    }
    
    @Override
    void draw(Game frame){
        
        /**
        for (Tower i: towerList){
            if (i.sprite.getParent() == null){
                frame.getLayeredPane().add(i.sprite, Integer.valueOf(1));
            }
            i.sprite.setLocation(i.x, i.y);
        }
        **/
    }
        
}


