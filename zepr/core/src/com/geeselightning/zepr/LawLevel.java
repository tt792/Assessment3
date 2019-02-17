package com.geeselightning.zepr;

import com.badlogic.gdx.math.Vector2;

public class LawLevel extends Level {

    private static final String mapLocation = "maps/lawbldg.tmx"; //update with the Law level
    private static final Vector2 playerSpawn = new Vector2(350, 350);
    private static final Vector2 powerSpawn = new Vector2(250, 250);

    // Defining the number of zombies to be spawned for each wave
    private static final int[] waves = new int[]{10, 15, 20};

    public LawLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn, waves, powerSpawn);
    }

    /**
     * @implementation (F1) Controls which level is after this one
     */
    @Override
    public void complete() {
        // Update progress
        if (Zepr.progress == Zepr.LAW) {
            Zepr.progress = Zepr.COMPLETE;
        }
        // The stage is being replayed
    }
}
