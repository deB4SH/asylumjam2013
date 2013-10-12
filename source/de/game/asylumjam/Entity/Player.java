package de.game.asylumjam.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
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

        spriteBatch.setBlendFunction(GL20.GL_BLEND_SRC_ALPHA, GL10.GL_ONE);
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(Game1.camera.projection);
        spriteBatch.enableBlending();
        spriteBatch.setColor(Color.WHITE);


        double v1 = (- Gdx.input.getY() + 600 ) - (getPosition().y + 55 );
        double v2 = (Gdx.input.getX()) - (getPosition().x + 160 );
        float rotation = (float)Math.toDegrees(Math.atan2( v1 ,v2 ));

        spriteBatch.draw(super.getTexture(), getPosition().x - Game1.camera.position.x + 160, getPosition().y - Game1.camera.position.y + 55,
                16,16,32,32,1,1,rotation-90,0,0,32,32,false,false);

        spriteBatch.end();

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
        if(sPressed && !collisionTest(new Vector2(super.getPosition().x + 1, super.getPosition().y -1)) &&
                !collisionTest(new Vector2(super.getPosition().x + super.getTexture().getWidth()-1, super.getPosition().y -1)))
        {
            super.setPosition(super.getPosition().add(0,-1));
            Game1.camera.translate(0,-1);
        }

        if(aPressed && !collisionTest(new Vector2(super.getPosition().x-1, super.getPosition().y )) &&
                !collisionTest(new Vector2(super.getPosition().x-1, super.getPosition().y + super.getTexture().getHeight())))
        {
           super.setPosition(super.getPosition().add(-1,0));
            Game1.camera.translate(-1,0);
        }

        if(wPressed && !collisionTest(new Vector2(super.getPosition().x +1, super.getPosition().y + super.getTexture().getHeight() +1)) &&
                !collisionTest(new Vector2(super.getPosition().x + super.getTexture().getWidth() -1, super.getPosition().y + super.getTexture().getHeight() +1)))
        {
            super.setPosition(super.getPosition().add(0,+1));
            Game1.camera.translate(0,1);
        }

        if(dPressed && !collisionTest(new Vector2(super.getPosition().x + super.getTexture().getWidth() + 1, super.getPosition().y )) &&
                !collisionTest(new Vector2(super.getPosition().x + super.getTexture().getWidth() + 1, super.getPosition().y + super.getTexture().getHeight())))
        {
            super.setPosition(super.getPosition().add(+1,0));
            Game1.camera.translate(1,0);
        }
    }

    private boolean collisionTest(Vector2 tile)
    {
        //System.out.println("prüf den dreck " + tile.x + " " + tile.y);
        //System.out.println("hier steht der scheiß " + super.getPosition().x + " " + super.getPosition().y);

        /*
        if(this.world.getMap()[(int)calcTile(tile).y][(int)calcTile(tile).x] == 0)
            return true;   //player is allowed to move on this tile
        else
            return false;    //player can not move on this tile
            */

        return false;
    }

    private Vector2 calcTile(Vector2 position)
    {
        return new Vector2 (position.x / GLOBAL.Tilesize, position.y / GLOBAL.Tilesize);
    }
}
