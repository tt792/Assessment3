package com.geeselightning.zepr;

import com.badlogic.gdx.math.Vector2;

public class HalifaxLevel extends Level {

    private static final String mapLocation = "maps/halifaxmap.tmx";
    private static final Vector2 playerSpawn = new Vector2(300, 300);
    private static final Vector2 powerSpawn = new Vector2(200, 200);
    
    // Defining the number of zombies to be spawned for each wave
    private static final int[] waves = new int[]{6, 11, 16};

    public HalifaxLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn, waves, powerSpawn);
    }

    /**
     * @implementation (F1) Controls which level is after this one
     */
    @Override
    public void complete() {
        if (Zepr.progress == Zepr.HALIFAX) {
            Zepr.progress = Zepr.COURTYARD;
        }
        // The stage is being replayed
    }
}
