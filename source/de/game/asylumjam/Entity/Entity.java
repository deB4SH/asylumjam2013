package de.game.asylumjam.Entity;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    //Position Data
    private Vector2 position;
    private Vector2 facing;

    //Alive Entity Related
    private int health;
    private int batteryLife;
    private int stamina;

    //Game Functional
    abstract void render(SpriteBatch spriteBatch);
    abstract void update();

    private Texture texture;

    //Get and Set
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getFacing() {
        return facing;
    }

    public void setFacing(Vector2 facing) {
        this.facing = facing;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
