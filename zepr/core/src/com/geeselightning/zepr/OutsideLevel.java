package com.geeselightning.zepr;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Arrays;

public class OutsideLevel extends Level {

    private static final String mapLocation = "maps/courtyard.tmx"; //update with the Outside level
    private static final Vector2 playerSpawn = new Vector2(300, 300);
    private static final Vector2 powerSpawn = new Vector2(250, 250);

    // Defining the number of zombies to be spawned for each wave
    private static final int[] waves = new int[]{1, 1, 1}; //{7, 12, 17};

    public OutsideLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn, waves, powerSpawn);
    }

    @Override
    public void complete() {
        // Update progress
        if (parent.progress == parent.OUTSIDE) {
            parent.progress = parent.LAW;
        }
        // The stage is being replayed
    }
}
