package com.mycompany.cs_tower_defense;

import com.mycompany.cs_tower_defense.inputs.MouseInputs;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author kyllianlim
 */
public class TowerManager extends GameManager {
    
    public long towerRange = 200;
    public int towerDamage = 1;
    public int bulletSpeed = 5;
    public static ArrayList<Tower> towerList = new ArrayList<Tower>();
    public static ArrayList<Bullets> bulletList = new ArrayList<Bullets>();
    private Tower tower;
    private int id = 0;
    
    //Set location of the tower(only for testing)
    private void buildTower(){
        buildTower1();
    }
    
    private void buildTower1(){
        tower = new Tower(MouseInputs.x * 64, MouseInputs.y * 64, id, 0, 1000000);
        id++;
        towerList.add(tower);
        tower.sprite = new JLabel(new ImageIcon(TowerManager.class.getResource("/images/computer.png")));
        tower.sprite.setSize(tower.sprite.getPreferredSize());
    }
    
    private void shootEnemy(){
        long now = System.currentTimeMillis();
        
        for (Tower t: towerList){
            if (now - t.lastShot >= t.fireRate){
                Enemy closestEnemy = findEnemy(t);
                if (closestEnemy != null){
                    t.lastShot = now;
                    Bullets bullet = new Bullets(t.x, t.y, towerDamage, closestEnemy, bulletSpeed);
                    bullet.sprite = new JLabel(new ImageIcon(TowerManager.class.getResource("/images/java_logo.png")));
                    bullet.sprite.setSize(64, 64);
                    bulletList.add(bullet);
                    //closestEnemy.takeDamage(towerDamage);
                    //System.out.println("Tower attacked enemy:" + closestEnemy);
                }
            }
        }
        
    }
    
    private void shootBullet(){
        bulletList.removeIf(b -> {
            double dx = b.target.x - b.x;
            double dy = b.target.y - b.y;
            double distance = Math.hypot(dx, dy);
            if (distance <= b.speed){
                b.target.takeDamage(b.damage);
                removeBullet(b);
                return true;
            } else {
                b.x = (int) (b.x + b.speed * (dx/distance));
                b.y = (int) (b.y + b.speed * (dy/distance));
            }
            return false;
        });
    }
    private void removeBullet(Bullets b){
        Container parent = b.sprite.getParent();
        if (b.sprite != null && parent != null){
            parent.remove(b.sprite);
            parent.repaint();
        }
    }
    
    private Enemy findEnemy(Tower t){
        Enemy closestEnemy = null;
        double minDistance = Double.MAX_VALUE;
        
        for (Enemy e: EnemyManager.enemiesArray){
            double distance = Math.hypot(e.x - t.x, e.y - t.y);
            if (distance < towerRange && distance < minDistance){
                minDistance = distance;
                closestEnemy = e;
            }
        }
        return closestEnemy;
    }

    @Override
    void update() {
        buildTower();
        shootEnemy();
        shootBullet();
    }
    
    @Override
    void draw(Game frame){
        for (Tower i: towerList){
            if (i.sprite.getParent() == null){
                frame.getLayeredPane().add(i.sprite, Integer.valueOf(1));
            }
            i.sprite.setLocation(i.x, i.y);
        }
        for (Bullets b: bulletList){
            if (b.sprite.getParent() == null){
                frame.getLayeredPane().add(b.sprite, Integer.valueOf(1));
            }
            b.sprite.setLocation(b.x, b.y); 
        }
    }
        
}


