package de.game.asylumjam.World;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Element;


public class TMXReader {

    private ArrayList<Layer> mapLayer;
    private Document doc;

    public TMXReader(String map) {

        initTask(map);

    }

    private void initTask(String map)
    {
        try
        {
            this.mapLayer = new ArrayList<Layer>();
            this.doc = new SAXBuilder().build(map);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void readXML()
    {
        Element element = doc.getRootElement();

        int a = 0;
    }

    public ArrayList<Layer> getMapLayer() {
        return mapLayer;
    }
}
