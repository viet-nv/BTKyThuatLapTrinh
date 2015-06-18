/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Entity.Entity1;
import Entity.Entity2;

/**
 *
 * @author Dell
 */
public class Physics {

    public static boolean Collision(Entity1 ent1, Entity2 ent2) {
        if (ent1.getBounds().intersects(ent2.getBounds())) {
            return true;
        }

        return false;
    }

    public static boolean Collision(Entity2 ent2, Entity1 ent1) {
        if (ent2.getBounds().intersects(ent1.getBounds())) {
            return true;
        }

        return false;
    }
}
