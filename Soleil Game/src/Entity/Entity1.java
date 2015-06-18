
package Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Graphics;
import java.awt.Rectangle;

public interface Entity1 {
    
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();
    
}
