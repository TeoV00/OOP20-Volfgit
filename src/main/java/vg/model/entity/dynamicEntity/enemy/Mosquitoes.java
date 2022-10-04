package vg.model.entity.dynamicEntity.enemy;

import vg.utils.MassTier;
import vg.utils.Shape;
import vg.utils.V2D;

public class Mosquitoes extends MinorEnemy {

    public Mosquitoes(V2D position, V2D speed, int radius, Shape shape, MassTier massTier) {
        super(position, speed, radius, shape, massTier);
    }

    public void accelerate(){
        //TODO use Speed
    }
}
