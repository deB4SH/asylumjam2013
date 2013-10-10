package de.game.asylumjam;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    private OrthographicCamera camera;
    private float width = 800;
    private float height = 600;

    @Override
    public void create() {
        fpsLogger = new FPSLogger();
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera(width,height);
        camera.lookAt(-(height / 2.0f),-(width / 2.0f),0.0f);

    }

    @Override
    public void resize(int width, int height) {

    }

    private void update() {

    }

    @Override
    public void render() {
        update();

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.enableBlending();

        spriteBatch.begin();

        CharSequence text = "" + Gdx.input.getX() + " " + Gdx.input.getY();
        BitmapFont font = new BitmapFont();
        font.setColor(255,0,0,1);
        font.draw(spriteBatch, text, Gdx.input.getX() + 20, Gdx.input.getY());

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
