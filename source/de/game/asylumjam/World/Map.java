package de.game.asylumjam.World;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;

public class Map {

    private final int width = 15;
    private final int height = 15;

    private ArrayList<Texture> textures = new ArrayList<Texture>();

    private int[][] map = new int[][]  {
    {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
    {0,0,0,0,0,1,1,1,0,0,0,0,0,1,0},
    {0,0,0,0,0,1,1,1,0,0,0,0,0,1,0},
    {0,0,0,0,0,1,1,1,0,0,0,0,0,1,0},
    {0,0,0,0,0,1,1,1,0,0,0,0,0,1,0},
    {0,0,0,0,0,1,1,1,0,0,0,0,0,1,0},
    {0,0,0,0,0,1,1,1,0,0,0,0,0,1,0},
    {0,0,0,0,0,1,1,1,0,0,0,0,0,1,0},
    {0,0,0,1,1,1,1,1,0,0,0,0,0,0,0},
    {0,0,0,1,0,1,1,1,0,0,0,0,0,0,0},
    {0,0,0,0,0,1,1,1,0,0,0,0,0,0,0},
    {0,1,1,1,0,0,0,1,0,0,0,0,0,0,0},
    {0,1,1,1,1,1,1,1,0,0,0,0,0,0,0},
    {0,1,1,1,0,1,1,1,0,0,0,0,0,0,0},
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
                spriteBatch.draw(textures.get(map[y][x]), (x * 32) + 160, (y * 32) + 55);
                spriteBatch.end();
            }
        }
    }

    public void loadTileData()
    {
        try
        {
            textures.add(new Texture("Texturen/wall.png"));
            textures.add(new Texture("Texturen/floor.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
