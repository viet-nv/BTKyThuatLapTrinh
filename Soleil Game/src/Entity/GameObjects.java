/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.Rectangle;

public class GameObjects {
    
    public double x, y;
    
    public GameObjects(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public Rectangle getBounds(int w, int h){
        return new Rectangle((int) x, (int) y, w, h);
    }
}
