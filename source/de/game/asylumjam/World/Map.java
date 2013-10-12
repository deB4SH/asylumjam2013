package de.game.asylumjam.World;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.game.asylumjam.Game1;
import de.game.asylumjam.Misc.GLOBAL;

import java.util.ArrayList;

public class Map {

    private final int width = 15;
    private final int height = 15;

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

        loadTileData();
    }

    public void render(SpriteBatch spriteBatch){

        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                spriteBatch.begin();

                spriteBatch.enableBlending();

                spriteBatch.setProjectionMatrix(Game1.camera.projection);
                spriteBatch.draw(textures.get(map[y][x]), ((x * 32) + 160) - Game1.camera.position.x , ((y * 32) + 55) - Game1.camera.position.y);

                spriteBatch.end();
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
