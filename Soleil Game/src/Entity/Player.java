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
public class Player extends GameObjects implements Entity1 {

    private Textures tex;

    private int velX, velY;

    private Game game;
    private Controller c;
    private AudioPlayer au;

    public Player(int x, int y, Textures tex, Controller c, Game game) {
        super(x, y);
        this.tex = tex;

        this.c = c;
        this.game = game;
        au = new AudioPlayer("/enemyKilled.mp3");
    }

    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0) {
            x = 0;
        }

        if (y <= 0) {
            y = 0;
        }

        if (x >= 800 - 32) {
            x = 800 - 32;
        }
        if (y >= 600 - 32) {
            y = 600 - 32;
        }

        int a = 1;
        for (int i = 0; i < game.e2.size(); i++) {
            Entity2 tempEnt = game.e2.get(i);
            if (Physics.Collision(this, tempEnt)) {
                if (game.level == 1 || game.level == 2) {
                    c.removeEntity(tempEnt);
                    c.removeEntity(this);
                    au.play();
                    Game.HEALTH -= 20;
                } else if (game.level == 3) {
                    if (Game.HEALTHBOSS == 0) {
                        c.removeEntity(tempEnt);
                        c.removeEntity(this);
                    }
                    Game.HEALTH -= 4;
                }
                if (game.level == 1) {
                    game.setEnemy1Killed(game.getEnemy1Killed() + 1);
                } else if (game.level == 2) {
                    game.setEnemyKilled(game.getEnemyKilled() + 1);
                }
            }
        }

    }

    public void render(Graphics g) {
        g.drawImage(tex.player, (int) x, (int) y, null);
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

}
