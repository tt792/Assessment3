package com.geeselightning.zepr;

import com.badlogic.gdx.math.Vector2;

public class CompSciLevel extends Level {

    private static final String mapLocation = "maps/compScience.tmx"; //update with the compsci level
    private static final Vector2 playerSpawn = new Vector2(300, 300);
    private static final Vector2 powerSpawn = new Vector2(250, 250);

    // Defining the number of zombies to be spawned for each wave
    private static final int[] waves = new int[]{8, 13, 18};
    
    public CompSciLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn, waves, powerSpawn);
    }

    /**
     * @implementation (F1) Controls which level is after this one
     */
    @Override
    public void complete() {
        // Update progress
        if (Zepr.progress == Zepr.COMPSCI) {
            Zepr.progress = Zepr.OUTSIDE;
        }
        // The stage is being replayed
    }
}
