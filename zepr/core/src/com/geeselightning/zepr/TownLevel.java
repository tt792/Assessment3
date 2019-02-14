package com.geeselightning.zepr;

import com.badlogic.gdx.math.Vector2;

public class TownLevel extends Level {

    private static final String mapLocation = "maps/townmap.tmx";
    private static final Vector2 playerSpawn = new Vector2(530, 430);
    private static final Vector2 powerSpawn = new Vector2(300, 300);

    // Defining the number of zombies to be spawned for each wave
    private static final int[] waves = new int[]{0, 0, 1}; //{5, 10, 15};

    public TownLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn, waves, powerSpawn);
    }

    @Override
    public void complete() {
        if (Zepr.progress == Zepr.TOWN) {
            Zepr.progress = Zepr.HALIFAX;
        }
    }

}
