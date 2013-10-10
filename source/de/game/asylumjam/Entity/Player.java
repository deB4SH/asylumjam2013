package de.game.asylumjam.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {

    public Player(Vector2 startPos, int startHealth)
    {
        super.setPosition(startPos);
        super.setHealth(startHealth);
    }


    @Override
    public void render() {
        //TODO: create render for Player.Class
    }

    @Override
    public void update() {

        getKeyUpdate();

    }


    private void getKeyUpdate()
    {
        //Get Pressed Keys
        boolean wPressed = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean aPressed = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean sPressed = Gdx.input.isKeyPressed(Input.Keys.S);
        boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.D);

        //Update User Position
        if(wPressed)
            super.setPosition(super.getPosition().add(0,-1));
        if(aPressed)
            super.setPosition(super.getPosition().add(-1,0));
        if(sPressed)
            super.setPosition(super.getPosition().add(0,+1));
        if(dPressed)
            super.setPosition(super.getPosition().add(+1,0));
    }


   private void getMouseUpdate()
   {

   }
}
