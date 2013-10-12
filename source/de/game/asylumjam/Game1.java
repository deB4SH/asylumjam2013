package de.game.asylumjam;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.game.asylumjam.Entity.Player;
import de.game.asylumjam.World.Map;

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
        camera.position.set(width / 2, height / 2, 0);
        glViewport = new Rectangle(0,0,width,height);

        map  = new Map();
        player = new Player(new Vector2(100,100),100,map,new Texture("Texturen/wall.png"));

    }

    @Override
    public void resize(int width, int height) {

    }

    private void update() {

        if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom += 0.02;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.zoom -= 0.02;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.translate(0,2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.translate(-2,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.translate(0,-2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.translate(2,0);
        }

        player.update();
    }

    @Override
    public void render() {
        update();

        GL10 gl = Gdx.graphics.getGL10();
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport((int)glViewport.x, (int)glViewport.y, (int)glViewport.width, (int)glViewport.height);
        camera.update();
        camera.apply(gl);

        spriteBatch.enableBlending();

        map.render(spriteBatch);
        player.render(spriteBatch);


        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(camera.projection);

        CharSequence text = "" + Gdx.input.getX() + " " + Gdx.input.getY();
        BitmapFont font = new BitmapFont();
        font.setColor(255,0,0,1);
        font.draw(spriteBatch, text, Gdx.input.getX() + 20 - camera.position.x,(-Gdx.input.getY())+ camera.position.y);
        spriteBatch.end();

        fpsLogger.log();

    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }
    @Override
    public void dispose() {

    }
}
