package de.game.asylumjam;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.game.asylumjam.Entity.Player;
import de.game.asylumjam.Misc.GLOBAL;
import de.game.asylumjam.World.Map;
import de.game.asylumjam.World.TMXReader;

/**
 * Created with IntelliJ IDEA.
 * User: Novos
 * Date: 11.10.13
 * Time: 00:30
 * To change this template use File | Settings | File Templates.
 */
public class Game1 extends Game implements ApplicationListener {

    private FPSLogger fpsLogger;
    private SpriteBatch spriteBatch;

    public static OrthographicCamera camera;
    private float width = 800;
    private float height = 600;
    private Rectangle glViewport;

    private Map map;
    private Player player;


    @Override
    public void create() {
        fpsLogger = new FPSLogger();
        spriteBatch = new SpriteBatch();

        camera = new OrthographicCamera(width,height);
        camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight() /2, 0);
        //camera.zoom = 0.20f;
        camera.setToOrtho(false);
        glViewport = new Rectangle(0,0,width,height);

        map  = new Map();
        player = new Player(new Vector2(200,200),100,map,new Texture("Texturen/player.png"));

    }

    @Override
    public void resize(int width, int height) {

    }

    private void update() {

        if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom += 0.02;
            camera.zoom = camera.zoom > 20 ? camera.zoom = 1.0f : camera.zoom;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.zoom -= 0.02;

        }

     player.update();
    }

    @Override
    public void render() {
        update();

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport((int)glViewport.x, (int)glViewport.y, (int)glViewport.width, (int)glViewport.height);
        camera.update();

        map.render(spriteBatch);
        player.render(spriteBatch);

        spriteBatch.begin();
        //spriteBatch.setProjectionMatrix(camera.combined);


        CharSequence text = "" + Gdx.input.getX() + " " + Gdx.input.getY();
        BitmapFont font = new BitmapFont();
        font.setColor(255,0,0,1);
        font.draw(spriteBatch, text, Gdx.input.getX() + 20 ,(-Gdx.input.getY()));
        spriteBatch.end();

        fpsLogger.log();

    }
    @Override
    public void pause()  {

    }
    @Override
    public void resume() {

    }
    @Override
    public void dispose() {

    }
}
