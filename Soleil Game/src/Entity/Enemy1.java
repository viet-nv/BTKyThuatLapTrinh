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
public class Enemy1 extends GameObjects implements Entity2 {

    private Textures tex;
    private Controller c;
    private Game game;
    private AudioPlayer au;

    private int speed = 2;
    private int a;

    public Enemy1(int x, int y, Textures tex, Controller c, Game game) {
        super(x, y);

        this.tex = tex;
        this.c = c;
        this.game = game;
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
                game.setEnemy1Killed(game.getEnemy1Killed() + 1);
            }
        }

    }

    public void move() {
        if (a % 200 < 25 || (a % 200 >= 100 && a % 200 < 125)) {
            y += speed;
            a++;
        } else if (a % 200 < 100 && a % 200 >= 25) {
            x += speed;
            a++;
        } else {
            x -= speed;
            a++;
        }
        if (y > (Game.HEIGHT)) {
            y = -32 * 3;
        }
        if (x > (Game.WIDTH - 32)) {
            x = (Game.WIDTH - 32);
        }
        if (x < 0) {
            x = 0;
        }
    }

    public void render(Graphics g) {
        g.drawImage(tex.enemy1, (int) x, (int) y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

}
