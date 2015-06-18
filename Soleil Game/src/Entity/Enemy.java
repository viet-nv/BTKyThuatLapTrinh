/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Main.Game;
import Manager.AudioPlayer;
import Manager.Controller;
import Manager.Physics;
import Manager.Textures;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Dell
 */
public class Enemy extends GameObjects implements Entity2 {

    private Textures tex;
    private Controller c;
    private Game game;

    private double speedy ;
    private double speedx ;
    private int a;
    private int b=1;
    
    private AudioPlayer au;

    public Enemy(double x, double y,Textures tex, Controller c, Game game,double speedx ,double speedy ) {
        super(x, y);

        this.tex = tex;
        this.c = c;
        this.game = game;
        this.speedx=speedx;
        this.speedy=speedy;
        au = new AudioPlayer("/enemyKilled.mp3");
     
    }


    public void tick() {
        move();

        for (int i = 0; i < game.e1.size(); i++) {
            Entity1 entTemp = game.e1.get(i);
            if (Physics.Collision(this, entTemp)) {
                c.removeEntity(entTemp);
                c.removeEntity(this);
                au.play();
                game.setEnemyKilled(game.getEnemyKilled() + 1);
            }
        }

    }

    public void move() {
        y+=speedy;
        if (a % 200 < 25 || (a % 200 >= 100 && a % 200 < 125)) {
            a++;
            b=0;
        } else if (a % 200 < 100 && a % 200 >= 25) {
            b=1;
            
            a++;
        } else {
            b=-1;
            a++;
        }
        x+=b*speedx;
        if (y > (Game.HEIGHT)) {
            y = -32 * 3;
            x=Game.WIDTH-x;
            b=-b;
        }
        if (x > (Game.WIDTH - 32)) {
            x = (Game.WIDTH - 32);
        }
        if (x < 0) {
            x = 0;
        }
    }

    public void render(Graphics g) {
        g.drawImage(tex.enemy, (int) x, (int) y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
