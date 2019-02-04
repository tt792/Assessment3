package com.geeselightning.zepr;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Arrays;

public class MinigameLevel extends Level {

    private static final String mapLocation = "maps/halifaxmap.tmx";
    private static final Vector2 playerSpawn = new Vector2(300, 300);
    private static final Vector2 powerSpawn = new Vector2(250, 250);
    
    public MinigameLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn, zombieSpawnPoints, waves, powerSpawn);
    }

    @Override
    public void complete() {
        // Update progress
        if (parent.progress == parent.MINIGAME) {
            parent.progress = parent.COMPSCI;
        }
        // The stage is being replayed
    }
}
