package de.game.asylumjam.World;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import de.game.asylumjam.Game1;
import de.game.asylumjam.Misc.GLOBAL;

import java.util.ArrayList;

public class Map {

    private final int width = 15;
    private final int height = 15;

    private TMXReader mapReader;

    private ArrayList<Texture> textures = new ArrayList<Texture>();

    private int[][] map = new int[][]  {
    {0,0,0,0,0,0,0,0,0,0,0,0,0,4,0},
    {0,1,1,0,0,1,1,1,3,1,0,0,1,1,0},
    {0,1,1,1,3,1,1,1,0,1,1,1,1,1,0},
    {0,0,0,0,0,1,1,1,0,0,0,0,0,1,0},
    {0,1,1,1,0,1,1,1,0,1,1,1,1,1,0},
    {0,1,1,1,0,1,1,1,0,1,1,0,0,0,0},
    {0,1,0,0,0,1,1,1,0,0,0,0,1,1,0},
    {0,1,0,1,0,1,1,1,3,1,1,1,1,1,0},
    {0,1,1,1,3,1,1,1,0,1,0,1,1,1,0},
    {0,1,0,1,0,1,1,1,0,1,0,0,0,1,0},
    {0,0,0,0,0,1,1,1,0,1,1,0,0,0,0},
    {0,1,1,1,0,0,0,1,0,0,1,1,1,1,0},
    {0,1,1,1,3,1,1,1,0,0,0,0,1,1,0},
    {0,1,1,1,0,1,1,1,0,1,1,1,1,0,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};


    public Map(){

        mapReader = new TMXReader(Gdx.files.internal(GLOBAL.Map01).toString());

        loadTileData();
    }

    public void render(SpriteBatch spriteBatch){

        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                spriteBatch.begin();

                spriteBatch.enableBlending();
                spriteBatch.setColor(new Color(20,20,20,.5f));

                spriteBatch.setProjectionMatrix(Game1.camera.projection);
                spriteBatch.draw(textures.get(map[y][x]), ((x * 32) + 160) - Game1.camera.position.x , ((y * 32) + 55) - Game1.camera.position.y);
                spriteBatch.setColor(Color.WHITE);

                spriteBatch.end();
            }
        }
    }


    private void createTileSheet()
    {
        TextureRegion tileSetRegion = new TextureRegion(new Texture("Texturen/tileset.png"),128,128);
        textures.add(new Texture("Texturen/black_tile.png"));

        for(int x=0; x < GLOBAL.TilesPerLane; x++)
        {
            for(int y=0; y < GLOBAL.TilesVertical; y++)
            {
                tileSetRegion.setRegion(x*32,y*32,32,32);

                textures.add(tileSetRegion.getTexture());
            }
        }
    }

    public void loadTileData()
    {
        try
        {
            textures.add(GLOBAL.Wall,new Texture("Texturen/wall.png"));
            textures.add(GLOBAL.Floor,new Texture("Texturen/floor.png"));
            textures.add(GLOBAL.Door_Horizontal,new Texture("Texturen/door_horizontal.png"));
            textures.add(GLOBAL.Door_Vertical, new Texture("Texturen/door_vertical.png"));
            textures.add(GLOBAL.Door_Win, new Texture("Texturen/door_win.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public int[][] getMap()
    {
        return this.map;
    }
}
