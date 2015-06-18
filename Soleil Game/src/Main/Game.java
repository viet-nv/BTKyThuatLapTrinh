/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entity.Boss;
import Entity.Bullet;
import Entity.Enemy;
import Entity.Entity1;
import Entity.Entity2;
import Entity.Player;
import Manager.AudioPlayer;
import Manager.BufferedImageLoader;
import Manager.Controller;
import Manager.KeyInput;
import Manager.Menu;
import Manager.MouseInput;
import Manager.Textures;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Dell
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 790;
    public static final int HEIGHT = 590;

    private boolean isShooting = false;

    private boolean running = false;
    public static boolean checkMenu = true;
    private Thread thread;

    private int enemyCount = 5;
    private int enemyCount1 = 5;
    private int enemyCount3 = 3;
    private int enemyKilled;
    private int enemy1Killed;

    public static int HEALTH = 200;
    public static int HEALTHBOSS = 200;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage background;
    private BufferedImage player;
    private BufferedImage enemy;
    private BufferedImage menuBackground;
    private BufferedImage bulletBoss;
    private BufferedImage bossImage;

    public BufferedImage getEnemy1() {
        return enemy1;
    }

    public void setEnemy1(BufferedImage enemy1) {
        this.enemy1 = enemy1;
    }
    private BufferedImage bullet;
    private BufferedImage enemy1;

    public LinkedList<Entity1> e1;
    public LinkedList<Entity2> e2;

    public static Player p;
    public static Boss boss;
    private Textures tex;
    private Enemy e;
    private Controller c;
    private Menu menu;
    private AudioPlayer au;
    private AudioPlayer auBullet;

    public float level = (float) 0.5;

    public static enum STATE {

        MENU,
        GAME,
        GAMEOVER,
        VICTORY

    };

    public static STATE state = STATE.MENU;

    public void init() {
        requestFocus();
        au = new AudioPlayer("/level1-1.mp3");
        auBullet = new AudioPlayer("/bullet.wav");
        au.play();
        if (checkMenu) {
            this.addMouseListener(new MouseInput());
        }
        if(level-(int) level ==0.5) init0();
        else if (level == 1) {
            init1();
        } else if (level == 2) {
            init2();
        } else if (level == 3) {
            init3();
        }
    }
    
    public void init0() {

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            background = loader.loadImage("/nextLevel.jpg");
            player = loader.loadImage("/playerNextlv.png");
            enemy = loader.loadImage("/enemylv2.png");
            bullet = loader.loadImage("/bullet.png");
            enemy1 = loader.loadImage("/enemylv1.png");
            menuBackground = loader.loadImage("/menubackground.png");
            bulletBoss = loader.loadImage("/fireball.gif");
            bossImage = loader.loadImage("/boss.png");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.addKeyListener(new KeyInput(this));
        // this.addMouseListener(new MouseInput());

        tex = new Textures(this);
        c = new Controller(this, tex);
        if(level==0.5)
        p = new Player(150, 380, tex, c, this);
        if(level==1.5)
        p = new Player(331, 380, tex, c, this);
        if(level==2.5)
        p = new Player(601, 380, tex, c, this);
        menu = new Menu();

        e1 = c.getEntity1();
        e2 = c.getEntity2();

    }

    public void init3() {

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            background = loader.loadImage("/background3.jpg");
            player = loader.loadImage("/player.png");
            enemy = loader.loadImage("/enemylv2.png");
            bullet = loader.loadImage("/bullet.png");
            enemy1 = loader.loadImage("/enemylv1.png");
            menuBackground = loader.loadImage("/menubackground.png");
            bulletBoss = loader.loadImage("/fireball.gif");
            bossImage = loader.loadImage("/boss.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.addKeyListener(new KeyInput(this));
        // this.addMouseListener(new MouseInput());

        tex = new Textures(this);
        c = new Controller(this, tex);
        p = new Player(50, 50, tex, c, this);
        menu = new Menu();

        e1 = c.getEntity1();
        e2 = c.getEntity2();
        boss = new Boss(WIDTH / 2, 100, 200, tex, c, this);
        c.createEnemy3(enemyCount3);

    }

    public void init1() {

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            background = loader.loadImage("/background1.jpg");
            player = loader.loadImage("/player.png");
            enemy = loader.loadImage("/enemylv2.png");
            bullet = loader.loadImage("/bullet.png");
            enemy1 = loader.loadImage("/enemylv1.png");
            menuBackground = loader.loadImage("/menubackground.png");
            bulletBoss = loader.loadImage("/fireball.gif");
            bossImage = loader.loadImage("/boss.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.addKeyListener(new KeyInput(this));
        //   this.addMouseListener(new MouseInput());

        tex = new Textures(this);
        c = new Controller(this, tex);
        p = new Player(300, 300, tex, c, this);
        menu = new Menu();

        e1 = c.getEntity1();
        e2 = c.getEntity2();

        c.createEnemy1(enemyCount1);

    }

    public void init2() {
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            background = loader.loadImage("/background2.jpg");
            player = loader.loadImage("/player.png");
            enemy = loader.loadImage("/enemylv2.png");
            bullet = loader.loadImage("/bullet.png");
            enemy1 = loader.loadImage("/enemylv1.png");
            menuBackground = loader.loadImage("/menubackground.png");
            bulletBoss = loader.loadImage("/fireball.gif");
            bossImage = loader.loadImage("/boss.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.addKeyListener(new KeyInput(this));

        tex = new Textures(this);
        c = new Controller(this, tex);
        p = new Player(600, 600, tex, c, this);
        menu = new Menu();

        e1 = c.getEntity1();
        e2 = c.getEntity2();

        c.createEnemy(enemyCount);

    }

    synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.exit(1);
    }

    @Override
    public void run() {

        init();

        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            if(level-(int) level ==0.5){
                p.setVelX(1);
                if(p.x==320||p.x==600||p.x==750){
                level+=0.5;
                au.stop();
                run();
                }
            }
            else if (enemyCount1 >= 10) {
                level+=0.5;
                enemyCount1 = 0;
                enemy1Killed = 0;
                for (int i = 0; i < e2.size(); i++) {
                    e2.remove(e2.get(i));
                }
                au.stop();
                run();
            } else if (enemyCount >= 14) {
                level+=0.5;
                enemyCount = 0;
                enemyKilled = 0;
                for (int i = 0; i < e2.size(); i++) {
                    e2.remove(e2.get(i));
                }
                au.stop();
                run();
            }
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if ((System.currentTimeMillis() - timer) > 1000) {
                timer += 1000;
                System.out.println(updates + "Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }

        }

        stop();
    }

    public void setEnemyCount1(int enemyCount1) {
        this.enemyCount1 = enemyCount1;
    }

    public void setEnemy1Killed(int enemy1Killed) {
        this.enemy1Killed = enemy1Killed;
    }

    public int getEnemyCount1() {
        return enemyCount1;
    }

    public int getEnemy1Killed() {
        return enemy1Killed;
    }

    public void tick() {
        if (state == STATE.GAME) {
            p.tick();
            c.tick();
            if (level == 3) {
                boss.tick();
            }
        }
        if (level == 1) {
            if (enemy1Killed >= enemyCount1 * 4) {
                enemyCount1 += 2;
                enemy1Killed = 0;
                c.createEnemy1(enemyCount1);
            }
        } else if (level == 2) {
            if (enemyKilled >= enemyCount * 6) {
                enemyCount += 2;
                enemyKilled = 0;
                c.createEnemy(enemyCount);
            }
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, getWidth(), getHeight(), this);
        g.fillRect(0, 0, 1400, 1400);

        if (state == STATE.GAME) {
            checkMenu = false;

            g.drawImage(background, 0, 0, this);
            p.render(g);
            c.render(g);

            //health bar player
            g.setColor(Color.BLACK);
            g.fillRect(5, 5, 200, 15);

            g.setColor(Color.RED);
            g.fillRect(5, 5, HEALTH, 15);

            g.setColor(Color.YELLOW);
            g.drawRect(5, 5, 200, 15);
            if (level == 1) g.drawString("Level 1", 380, 15);
            if (level == 2) g.drawString("Level 2", 380, 15);
            if (level == 3) {
                
                g.drawString("Finally", 380, 15);
                boss.render(g);

                //healthbar boss
                g.setColor(Color.BLACK);
                g.fillRect(555, 5, 200, 15);

                g.setColor(Color.YELLOW);
                g.fillRect(555, 5, HEALTHBOSS, 15);

                g.setColor(Color.YELLOW);
                g.drawRect(555, 5, 200, 15);
            }
        } else if (state == STATE.MENU) {
            g.drawImage(menuBackground, 0, 0, this);
            menu.render(g);
        }

        if (HEALTH == 0) {
            checkMenu = true;
            state = STATE.GAMEOVER;
           
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 600);
            g.drawImage(background, 0, 0, this);
            
            Font font0 = new Font("arial", Font.BOLD, 50);
            g.setFont(font0);
            g.setColor(Color.ORANGE);
            g.drawString("GAME OVER", (int) (Game.WIDTH / 3.5) + 30, 100);
            
            g.drawString("Quit", 345, 350 + 30);

        }
        
        if (HEALTHBOSS == 0) {
            checkMenu = true;
            state = STATE.VICTORY;
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 600);
            g.drawImage(background, 0, 0, this);

            Font font0 = new Font("arial", Font.BOLD, 50);
            g.setFont(font0);
            g.setColor(Color.ORANGE);
            g.drawString("VICTORY", (int) (Game.WIDTH / 3.5) + 60, 100);
            
            g.drawString("Quit", 345, 350 + 30);

        }

        g.dispose();
        bs.show();

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            p.setVelX(-4);
        } else if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(4);
        } else if (key == KeyEvent.VK_UP) {
            p.setVelY(-4);
        } else if (key == KeyEvent.VK_DOWN) {
            p.setVelY(4);
        } else if ((key == KeyEvent.VK_SPACE) && (!isShooting)) {
            isShooting = true;
            if (level == 1) {
                c.addEntity(new Bullet((int) p.getX() + 10, (int) p.getY() - 10, tex, this));
            } else if (level == 2) {
                c.addEntity(new Bullet((int) p.getX() + 20, (int) p.getY() - 10, tex, this));
                c.addEntity(new Bullet((int) p.getX(), (int) p.getY() - 10, tex, this));
            }
            if (level == 3) {
                c.addEntity(new Bullet((int) p.getX() + 10, (int) p.getY() - 10, tex, this));
                c.addEntity(new Bullet((int) p.getX(), (int) p.getY() - 10, tex, this));
                c.addEntity(new Bullet((int) p.getX()+20, (int) p.getY() - 10, tex, this));

            }
            auBullet.play();
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            p.setVelX(0);
        } else if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(0);
        } else if (key == KeyEvent.VK_UP) {
            p.setVelY(0);
        } else if (key == KeyEvent.VK_DOWN) {
            p.setVelY(0);
        } else if ((key == KeyEvent.VK_SPACE) && (isShooting)) {
            isShooting = false;
            auBullet.stop();
        }
    }

    public BufferedImage getPlayer() {
        return player;
    }

    public BufferedImage getEnemy() {
        return enemy;
    }

    public BufferedImage getBullet() {
        return bullet;
    }

    public BufferedImage getBulletBoss() {
        return bulletBoss;
    }
    
    public BufferedImage getBoss() {
        return bossImage;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getEnemyKilled() {
        return enemyKilled;
    }

    public void setEnemyKilled(int enemyKilled) {
        this.enemyKilled = enemyKilled;
    }

}
