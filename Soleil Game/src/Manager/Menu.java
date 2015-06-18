/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Main.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Dell
 */
public class Menu {
    
    public Rectangle play = new Rectangle((Game.WIDTH/2) - 50, 250, 100, 50);
    public Rectangle quit = new Rectangle((Game.WIDTH/2) - 50 , 350, 100, 50);
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        Font font0 = new Font("arial", Font.BOLD, 50);
        g.setFont(font0);
        g.setColor(Color.YELLOW);
        g.drawString("SOLEIL GAME", (int) (Game.WIDTH/3.5), 100);
        
        Font font1 = new Font("Serif", Font.CENTER_BASELINE, 30);
        g.setFont(font1);
        g.drawString("Play", play.x + 19, play.y + 30);
        g2d.draw(play);
        g.drawString("Quit", quit.x + 19, quit.y + 30);
        g2d.draw(quit);
    }
}
