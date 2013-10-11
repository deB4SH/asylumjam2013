package de.game.asylumjam.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import de.game.asylumjam.Misc.GLOBAL;
import de.game.asylumjam.World.Map;

public class Player extends Entity {

    private Map world;

    public Player(Vector2 startPos, int startHealth, Map map)
    {
        super.setPosition(startPos);
        super.setHealth(startHealth);
        this.world = map;
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
        if(wPressed && !collisionTest(super.getPosition().add(0,+1)))
        {
            super.setPosition(super.getPosition().add(0,+1));
        }

        if(aPressed && !collisionTest(super.getPosition().add(+1,0))
        {
           super.setPosition(super.getPosition().add(+1,0));
        }

        if(sPressed && !collisionTest(super.getPosition().add(0,-1)))
        {
            super.setPosition(super.getPosition().add(0,-1));
        }

        if(dPressed && !collisionTest(super.getPosition().add(-1,0)))
        {
            super.setPosition(super.getPosition().add(-1,0));
        }
    }


    private void getMouseUpdate()
    {
        //Updating Facing Vector
       super.setFacing(new Vector2(Gdx.input.getX(),Gdx.input.getY()));

        //Calc Rotation-Angle


    }

    private boolean collisionTest(Vector2 tile)
    {
        if(this.world.getMap()[(int)tile.x][(int)tile.y] == 0)
            return false;   //player is not allowed to move on this tile
        else
            return true;    //player can move on this tile
    }

    private void calcTile()
    {
        Vector2 playerOnTile = new Vector2(super.getPosition().x / GLOBAL.Tilesize, super.getPosition().y / GLOBAL.Tilesize);
    }
}
