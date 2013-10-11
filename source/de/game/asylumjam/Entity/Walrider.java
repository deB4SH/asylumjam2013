package de.game.asylumjam.Entity;

/*
    The Walrider is a ghost that steals your energy to move. He will kill you if you are out of energy.
    Jummy he loves energy!

    Credit to Oustlast Creators - this game is amazing.
 */

import com.badlogic.gdx.math.Vector2;

public class Walrider extends Entity {

    private Player playerObj;

    public Walrider(Vector2 startPos, int startHealth, Player player)
    {
        super.setPosition(startPos);
        this.playerObj = player;
    }


    @Override
    void render() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void update() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
