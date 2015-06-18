/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Manager.Textures;
import java.awt.Graphics;
import java.awt.Rectangle;
import Main.Game;

public class Bullet extends GameObjects implements Entity1{

    private Textures tex;
    
    Game game;

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bullet(int x, int y, Textures tex, Game game) {
        super(x, y);
        this.tex = tex;
        this.game = game;
    }

    @Override
    public void tick() {
        y -= 10;
        
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tex.bullet, (int) x, (int) y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
