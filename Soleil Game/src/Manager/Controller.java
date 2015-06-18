/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;


import Entity.Entity1;
import Entity.Entity2;
import java.awt.Graphics;
import java.util.LinkedList;
import Entity.Enemy;
import Entity.Enemy1;
import Entity.Enemylv3;
import Main.Game;

public class Controller {

    private LinkedList<Entity1> e1 = new LinkedList<Entity1>();
    private LinkedList<Entity2> e2 = new LinkedList<Entity2>();

    Entity1 ent1;
    Entity2 ent2;

    Game game;
    Textures tex;
        
    public Controller(Game game, Textures tex) {
        this.game = game;
        this.tex = tex;

        
    }

     public void createEnemy(int enemyCount) {
        int a;
        int b=100*(14+enemyCount)/14;
        for (int i = 0; i < enemyCount; i++)
            for(int j=0;j<6;j++)
            {
                a=(Game.WIDTH-2*b-100)/(enemyCount-1);
            addEntity(new Enemy(a*i+16+75*2, -50*j-100, tex, this, game, (14+enemyCount)/14,(int)(2+3*(1.0/14)*enemyCount)+0.1*i));
            }
    }
   
     public void createEnemy1(int enemyCount1) {
        int a=(game.getWidth()-75)/(enemyCount1+1);
        for (int i = 0; i < enemyCount1; i++)
            for(int j=0;j<4;j++)
            addEntity(new Enemy1(a*i+16,- 50*j, tex, this, game));
    }
   
      public void createEnemy3(int enemyCount3) {
            addEntity(new Enemylv3(400, 100, tex, this, game,1,0));
            addEntity(new Enemylv3(400, 100 , tex, this, game,1,1));
            addEntity(new Enemylv3(400, 100, tex, this, game,1,-1));
            addEntity(new Enemylv3(400, 100, tex, this, game,2,1));
            addEntity(new Enemylv3(400, 100 , tex, this, game,2,-1));
            addEntity(new Enemylv3(300, 200, tex, this, game,1,-1));
            addEntity(new Enemylv3(500, 200 , tex, this, game,1,1));
            addEntity(new Enemylv3(350, 200, tex, this, game,2,-1));
            addEntity(new Enemylv3(450, 200, tex, this, game,2,1));
            addEntity(new Enemylv3(400, 200 , tex, this, game,1,0));
            addEntity(new Enemylv3(200, 300, tex, this, game,1,-1));
            addEntity(new Enemylv3(600, 300 , tex, this, game,1,1));
            addEntity(new Enemylv3(300, 300, tex, this, game,2,-1));
            addEntity(new Enemylv3(500, 300, tex, this, game,2,1));
            addEntity(new Enemylv3(400, 300 , tex, this, game,2,0));
           
            
          
    }
   
    public void tick() {
        //Entity1
        for (int i = 0; i < e1.size(); i++) {
            ent1 = e1.get(i);
            
            ent1.tick();
        }
        //Entity2
        for (int i = 0; i < e2.size(); i++) {
            ent2 = e2.get(i);
            
            ent2.tick();
        }
    }
    
    public void render(Graphics g){
        for (int i = 0; i < e1.size(); i++) {
            ent1 = e1.get(i);
            
            ent1.render(g);
        }
        
        for (int i = 0; i < e2.size(); i++) {
            ent2 = e2.get(i);
            
            ent2.render(g);
        }
    }

    public void addEntity(Entity1 block){
        e1.add(block);
    }
    
    public void removeEntity(Entity1 block) {
        e1.remove(block);
    }
    
    public void addEntity(Entity2 block){
        e2.add(block);
    }
    
    public void removeEntity(Entity2 block) {
        e2.remove(block);
    }
    
    public LinkedList<Entity2> getEntity2(){
        return e2;
    }

    public LinkedList<Entity1> getEntity1() {
        return e1;
    }

    
}
