package com.geeselightning.zepr;

import com.badlogic.gdx.math.Vector2;

public class MinigameLevel extends Level {

    private static final String mapLocation = "maps/minigamemap.tmx";
    private static final Vector2 playerSpawn = new Vector2(300, 300);
    
    public MinigameLevel(Zepr zepr) {
        super(zepr, mapLocation, playerSpawn);
    }
    
    @Override 
    public void show(){
    	System.out.println("Things");
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
