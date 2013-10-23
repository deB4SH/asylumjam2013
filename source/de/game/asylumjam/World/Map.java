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

    private final int width = 10;
    private final int height = 10;

    private TMXReader mapReader;

    private ArrayList<Texture> textures = new ArrayList<Texture>();

    public Map(){

        mapReader = new TMXReader(Gdx.files.internal(GLOBAL.MapTest).toString());

        //createTileSheet();
        loadTileData();
    }

    public void render(SpriteBatch spriteBatch){

        for(Layer e: mapReader.getMapLayer())
        {
            //System.out.println(e.getLayerName());

            /*
            for(int x = 0; x < width; x++)
            {
                for(int y = 0; y < height; y++)
                {
                    spriteBatch.begin();

                    //spriteBatch.enableBlending();
                    //spriteBatch.setColor(new Color(20,20,20,1f));

                    spriteBatch.setProjectionMatrix(Game1.camera.projection);
                    System.out.println(e.getSingleTile(x,y));
                    spriteBatch.draw(textures.get(e.getSingleTile(x,y)), ((x * 32) + 160) - Game1.camera.position.x , ((y * 32) + 55) - Game1.camera.position.y);
                    spriteBatch.setColor(Color.WHITE);

                    spriteBatch.end();
                }	
            }
            */


            for(int i=0; i < width; i++)
            {
                for(int j=0; j < height; j++)
                {
                    spriteBatch.begin();
                    spriteBatch.setProjectionMatrix(Game1.camera.projection);
                    spriteBatch.draw(textures.get(mapReader.getMapLayer().get(0).getSingleTile(i,j)),i*32-Game1.camera.position.x +160,j*32-Game1.camera.position.y + 55);
                    spriteBatch.end();
                }
            }
        }
    }


    /*private void createTileSheet()
    {
        TextureRegion tileSetRegion = new TextureRegion(new Texture("Texturen/tileset.png"),128,128);

        textures.add(new Texture("Texturen/black_tile.png"));

        TextureRegion[][] textureRegions = tileSetRegion.split(32,32);

        for(int x=3; x >= 0; x--)
        {
            for(int y=3; y >= 0; y--)
            {
                //tileSetRegion.setRegion(x/32,y/32,32,32);

                textures.add(textureRegions[x][y].getTexture());
            }
        }
        int i = 0;
    } */

    public void loadTileData()
    {
        try
        {   // 0
            textures.add(new Texture("Texturen/black_tile.png"));

            //1 +
            textures.add(new Texture("Texturen/floor.png"));
            textures.add(new Texture("Texturen/door_vertical.png"));
            textures.add(new Texture("Texturen/wall.png"));
            textures.add(new Texture("Texturen/black_tile.png"));

            textures.add(new Texture("Texturen/wall.png"));
            textures.add(new Texture("Texturen/door_horizontal.png"));
            textures.add(new Texture("Texturen/black_tile.png"));
            textures.add(new Texture("Texturen/black_tile.png"));

            textures.add(new Texture("Texturen/wall.png"));
            textures.add(new Texture("Texturen/black_tile.png"));
            textures.add(new Texture("Texturen/black_tile.png"));
            textures.add(new Texture("Texturen/black_tile.png"));

            textures.add(new Texture("Texturen/floor.png"));
            textures.add(new Texture("Texturen/black_tile.png"));
            textures.add(new Texture("Texturen/black_tile.png"));
            textures.add(new Texture("Texturen/black_tile.png"));

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Layer> getMap()
    {
        return this.mapReader.getMapLayer();
    }
}
