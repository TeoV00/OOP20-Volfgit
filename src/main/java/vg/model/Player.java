package vg.model;

import vg.utils.V2D;

public class Player extends DynamicEntity {
    static final int PLAYER_MAX_LIFE = 5;
    private int life;
    //private Shield shield;

    public static Player newPlayer(final V2D position){
        return new Player(position,PLAYER_MAX_LIFE);
    };

    public static Player newPlayer(final V2D position, final int life) {
        return new Player(position,life);
    };

    private Player(final V2D position, final int life) {
        super(position,new V2D(2,2));
        this.life = life;

        //TODO: init shield
        //this.shield = new Shield();
    }

}
