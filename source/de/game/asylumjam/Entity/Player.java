package de.game.asylumjam.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.game.asylumjam.Game1;
import de.game.asylumjam.Misc.GLOBAL;
import de.game.asylumjam.World.Map;

public class Player extends Entity {

    private Map world;

    public Player(Vector2 startPos, int startHealth, Map map, Texture texture)
    {
        super.setPosition(startPos);
        super.setHealth(startHealth);
        this.world = map;
        super.setTexture(texture);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(Game1.camera.projection);

        CharSequence text = "" + Game1.camera.position.x + Game1.camera.position.y;

        BitmapFont font = new BitmapFont();
        font.setColor(255,0,0,1);
        font.draw(spriteBatch, text, getPosition().x - Game1.camera.position.x + 32 , getPosition().y - Game1.camera.position.y);

        double v1 = (- Gdx.input.getY() + 600) - (getPosition().y );
        double v2 = (Gdx.input.getX()) - (getPosition().x );
        float rotation = (float)Math.toDegrees(Math.atan2( v1 ,v2 ));

        spriteBatch.draw(super.getTexture(), getPosition().x - Game1.camera.position.x, getPosition().y - Game1.camera.position.y,
                16,16,32,32,1,1,rotation,0,0,32,32,false,false);

        spriteBatch.end();
    }

    @Override
    public void update() {

    //    getKeyUpdate();

    }
    /*

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

        if(aPressed && !collisionTest(super.getPosition().add(+1,0)))
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
        Vector2 standardVector = new Vector2(0,1);
        Vector2 playerFacing = super.getFacing();






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
    } */
}
