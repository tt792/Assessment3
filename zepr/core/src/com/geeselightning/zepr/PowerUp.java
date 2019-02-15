package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * @implementation (F6) Initialises the powerups and used as parent of the individual powerup classes
 */
public class PowerUp extends Sprite {

    Player player = Player.getInstance();
    public int type;
    Level currentLevel;
    public boolean active;

    public PowerUp(int type, Texture texture, Level currentLevel) {
        super(new Sprite(texture));
        this.type = type;
        this.currentLevel = currentLevel;
        if (currentLevel != null) {
            // Tests pass a null currentLevel
            setPosition(currentLevel.powerSpawn.x, currentLevel.powerSpawn.y);
        }
    }

    public void activate(){
        active = true;
    }

    public void deactivate(){
        active = false;
        if (currentLevel != null) {
            // Tests pass a null currentLevel
            currentLevel.currentPowerUp = null;
        }
    }
    
    /**
     * @implementation (F6) Controls when the player gets a powerup
     */
    public boolean overlapsPlayer(){
        Rectangle rectanglePlayer = player.getBoundingRectangle();
        Rectangle rectanglePower = this.getBoundingRectangle();
        return rectanglePlayer.overlaps(rectanglePower);
    }

    public void update(float delta) {}

}
