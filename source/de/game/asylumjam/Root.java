package de.game.asylumjam;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class Root {

     public static void main(String[] args)
     {
         LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
         cfg.title = "LetsJam";
         cfg.useGL20 = true;
         cfg.width = 800;
         cfg.height = 600;
         cfg.resizable = false;
         new LwjglApplication(new Game1(),cfg);
     }
}
