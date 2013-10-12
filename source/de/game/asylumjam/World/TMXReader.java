package de.game.asylumjam.World;

import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
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

        int a = 0;

        for(Element e: Layer)
        {
            mapLayer.add(new Layer(Integer.parseInt(e.getAttribute("height").toString()),Integer.parseInt(e.getAttribute("width").toString()),e.getAttribute("name")));

        }

    }

    private int[][] generateMapArray(Element e)
    {



        return new int[5][5];
    }


    public ArrayList<Layer> getMapLayer() {
        return mapLayer;
    }
}
