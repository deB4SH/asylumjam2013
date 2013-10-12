package de.game.asylumjam.World;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Content;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.filter.ContentFilter;
import org.jdom2.filter.Filter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Element;


public class TMXReader {

    private ArrayList<Layer> mapLayer;
    private Document doc;

    public TMXReader(String map) {

        initTask(map);
        readXML();

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

    private void readXML()
    {
        //Get all Layers in the Tiled-XML-Export
        List<Element> Layer = doc.getRootElement().getChildren("layer");
        int listPTR = 0;

        try{
            for(Element e: Layer)
            {
                mapLayer.add(new Layer(e.getAttribute("height").getIntValue(),e.getAttribute("width").getIntValue(),e.getAttribute("name").toString()));
                mapLayer.get(listPTR).fillTileData(generateMapArray(e));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        int a = 0;

    }

    private int[] generateMapArray(Element e) throws DataConversionException {

        Element Data = (Element)e.getContent(1);
        int[] storage = new int[e.getAttribute("height").getIntValue()*e.getAttribute("width").getIntValue()];
        int storagePtr = 0;

        for(int i=0; i < Data.getContentSize(); i++)
        {
            if(Data.getContent(i).getValue().contains("\n"))
            {
                //new line (why does the SAXBuilder reads a newline every time. O,o)
            }
            else
            {
                Element tile = (Element)Data.getContent(i);
                storage[storagePtr] = tile.getAttribute("gid").getIntValue();
                storagePtr++;
            }
        }

        //Clean UP
        Data = null;
        storagePtr = 0;

        return storage;
    }


    public ArrayList<Layer> getMapLayer() {
        return mapLayer;
    }
}
