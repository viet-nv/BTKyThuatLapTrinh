/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Main.Game;
import java.awt.image.BufferedImage;

public class Textures {
    
    public BufferedImage player, bullet, boss;
    public BufferedImage enemy, enemy1;
    public BufferedImage[] bulletBoss = new  BufferedImage[4];
    
    private PlayerSprite ps0, ps1, ps2, ps3, ps4, ps5;
    
    public Textures(Game game) {
        ps0 = new PlayerSprite(game.getBoss());
        ps1 = new PlayerSprite(game.getPlayer());
        ps2 = new PlayerSprite(game.getEnemy());
        ps3 = new PlayerSprite(game.getBullet());
        ps4 = new PlayerSprite(game.getEnemy1());
        ps5 = new PlayerSprite(game.getBulletBoss());
        getTextures();
    }
    
    private void getTextures() {
        player = ps1.grabImage(1, 1, 32, 32);
        enemy = ps2.grabImage(1, 1, 32, 32);
        bullet = ps3.grabImage(1, 1, 13, 33);
        enemy1 = ps4.grabImage(1, 1, 32, 32);
        boss = ps0.grabImage(1, 1, 32, 32);
        
        bulletBoss[0] = ps5.grabImageBullet(1, 1, 30, 30);
        bulletBoss[1] = ps5.grabImageBullet(2, 1, 30, 30);
        bulletBoss[2] = ps5.grabImageBullet(3, 1, 30, 30);
        bulletBoss[3] = ps5.grabImageBullet(4, 1, 30, 30);
    }
    
}
