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
import java.util.Random;

/**
 *
 * @author Dell
 */
public class Boss extends GameObjects implements Entity2 {

    private Textures tex;
    private Controller c;
    private Game game;
    private int healthBoss;
    private AudioPlayer au;

    Random r = new Random();
    private int speed = 3;
    private int a = 0;

    public Boss(int x, int y, int healthBoss, Textures tex, Controller c, Game game) {
        super(x, y);

        this.healthBoss = healthBoss;
        this.tex = tex;
        this.c = c;
        this.game = game;
        au = new AudioPlayer("/enemyKilled.mp3");
    }

    public void tick() {
        move();
        int a = 1;
        for (int i = 0; i < game.e1.size(); i++) {
            Entity1 entTemp = game.e1.get(i);
            if (Physics.Collision(this, entTemp)) {
                    c.removeEntity(entTemp);
                    c.removeEntity(this);
                    au.play();
                    game.HEALTHBOSS -= 2;
                
            }
        }
    }

    public void move() {
        if (a == 0) {
            x += speed;
            if (x >= (Game.WIDTH - 100)) {
                a = 1;
            }
        } else {
            x -= speed;
            if (x <= 100) {
                a = 0;
            }
        }

    }

    public void render(Graphics g) {
        g.drawImage(tex.boss, (int) x, (int) y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
