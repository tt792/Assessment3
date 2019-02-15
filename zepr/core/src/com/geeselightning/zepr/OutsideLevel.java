package com.geeselightning.zepr;

import com.badlogic.gdx.math.Vector2;

public class OutsideLevel extends Level {

    private static final String mapLocation = "maps/courtyard.tmx"; //update with the Outside level
    private static final Vector2 playerSpawn = new Vector2(300, 300);
    private static final Vector2 powerSpawn = new Vector2(250, 250);

    // Defining the number of zombies to be spawned for each wave
    private static final int[] waves = new int[]{0, 0, 1}; //{7, 12, 17};

    public OutsideLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn, waves, powerSpawn);
    }

    /**
     * @implementation (F1) Controls which level is after this one
     */
    @Override
    public void complete() {
        // Update progress
        if (Zepr.progress == Zepr.OUTSIDE) {
            Zepr.progress = Zepr.LAW;
        }
        // The stage is being replayed
    }
}
