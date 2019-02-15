package com.geeselightning.zepr;

import com.badlogic.gdx.math.Vector2;

/**
 * @implementation (F5) Initialises the minigame and controls which level is after the minigame
 */
public class MinigameLevel extends Level {

    private static final String mapLocation = "maps/minigamemap.tmx";
    private static final Vector2 playerSpawn = new Vector2(26 * 16, 20 * 16);
    
    public MinigameLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn);
    }
    
    /**
     * @implementation (F5) Controls which level is after the minigame
     */
    @Override
    public void complete() {
        // Update progress
        if (Zepr.progress == Zepr.MINIGAME) {
            Zepr.progress = Zepr.COMPSCI;
        }
        // The stage is being replayed
    }
}
