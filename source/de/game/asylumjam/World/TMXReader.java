package de.game.asylumjam.World;

import java.util.ArrayList;

public class TMXReader {

    private ArrayList<Layer> mapLayer;

    public TMXReader() {

        this.mapLayer = new ArrayList<Layer>();

    }

    public void readXML()
    {
        //Recreate XML-Reader
        //the old one was to crappy
    }

    public ArrayList<Layer> getMapLayer() {
        return mapLayer;
    }
}
