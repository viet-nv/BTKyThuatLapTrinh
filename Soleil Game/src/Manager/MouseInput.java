/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Main.Game;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Dell
 */
public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        int mx = me.getX();
        int my = me.getY();
        //play 
        if (Game.checkMenu) {
            if (mx >= (Game.WIDTH / 2 - 50) && mx <= (Game.WIDTH / 2) + 50) {
                if (my <= 300 && my >= 250) {
                    Game.state = Game.STATE.GAME;
                }
            }

            //quit
            if (mx >= (Game.WIDTH / 2 - 50) && mx <= (Game.WIDTH / 2) + 50) {
                if (my <= 400 && my >= 350) {
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

}
