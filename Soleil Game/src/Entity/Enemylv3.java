/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Main.Game;
import Manager.Controller;
import Manager.Physics;
import Manager.Textures;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Dell
 */
public class Enemylv3 extends GameObjects implements Entity2 {

    private Textures tex;
    private Controller c;
    private Game game;

    private int speed = 4;
    private int a;
    private int direction;
    private int way;

    private Animation ani;

    public Enemylv3(int x, int y, Textures tex, Controller c, Game game, int direction, int way) {
        super(x, y);

        this.way = way;
        this.tex = tex;
        this.c = c;
        this.game = game;
        this.direction = direction;

        ani = new Animation(4, tex.bulletBoss[0], tex.bulletBoss[1], tex.bulletBoss[2], tex.bulletBoss[3]);
    }

    public void tick() {
        move();

      
        ani.runAnimation();

    }

    public void move() {

        y += speed;
        x += way * (speed / direction);
        if (y >= Game.HEIGHT) {
            x = Game.boss.getX() + 4;
            y = Game.boss.getY() + 8;
        }

    }

    public void render(Graphics g) {
        ani.drawAnimation(g, x, y, 0);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x + 5, (int) y + 5, 10, 10);
    }

}
