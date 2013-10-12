package de.game.asylumjam.World;

import com.badlogic.gdx.math.Vector2;
import org.jdom2.Attribute;

public class Layer {

    private int width,height;
    private int[][] tileData;
    private String layerName;

    public Layer(int height, int width, String layerName)
    {
        this.height = height;
        this.width = width;
        this.layerName = layerName;

        this.tileData = new int[width][height];
    }

    public void fillTileData(int[] data)
    {
        int count = 0;

        for(int y=0; y < height; y++)
        {
            for(int x=0; x < width; x++)
            {
                tileData[x][y] = data[count];
                count++;
            }
        }
    }

    public void fillTileData(int[][] data)
    {
        this.tileData = data;
    }

    public int getSingleTile(int x, int y)
    {
        return this.tileData[x][y];
    }

    public int getSingleTile(Vector2 vec)
    {
        return this.tileData[(int)vec.x][(int)vec.y];
    }

    public String getLayerName()
    {
        return this.layerName;
    }
}
