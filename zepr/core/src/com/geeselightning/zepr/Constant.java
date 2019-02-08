package com.geeselightning.zepr;

import com.badlogic.gdx.math.Vector2;

public final class Constant {
    public static final Vector2 ORIGIN = new Vector2(0, 0);
    
    //Player constants
    public static final float PLAYERSPEED = 120; //Base player speed
    public static final int PLAYERMAXHP = 100; //Base player health
    public static final int PLAYERDMG = 10; //Base player damage
    public static final int PLAYERRANGE = 50; //Base player range
    public static final float PLAYERKNOCKBACK = 20;
    public static final float PLAYERHITCOOLDOWN = 0.2f; //Base player hit cooldown
    public static final float NERDYHPMULT = 1.5f; //Nerd characters multiplier of base health
    public static final float NERDYDMGMULT = 1; //Nerd characters multiplier of base damage
    public static final float NERDYSPEEDMULT = 1; //Nerd characters multiplier of base speed
    public static final float ARTYHPMULT = 1.25f; //Arty characters multiplier of base health
    public static final float ARTYDMGMULT = 1.25f; //Arty characters multiplier of base damage
    public static final float ARTYSPEEDMULT = 1.25f; //Arty characters multiplier of base speed
    public static final float SPORTYHPMULT = 1; //Sporty characters multiplier of base health
    public static final float SPORTYDMGMULT = 1.5f; //Sporty characters multiplier of base damage
    public static final float SPORTYSPEEDMULT = 1.5f; //Sporty characters multiplier of base speed

    //Zombie constants
    public static final float ZOMBIESPEED = 80; //Normal zombie speed
    public static final int ZOMBIEMAXHP = 100; //Normal zombie max HP
    public static final int ZOMBIEDMG = 10; //Normal zombie damage
    public static final int ZOMBIERANGE = 20; //Normal zombie range
    public static final float ZOMBIEHITCOOLDOWN = 1;//Normal zombie hit cooldown
    public static final int ZOMBIEPOINTS = 10; //Normal zombie points awarded
    public static final int SPECIALCHANCE = 100; //Chance out of 1000 to spawn a special
    public static final int SPECIALPOINTS = 25; //Special zombie points awarded
    public static final int FASTDMG = 5; //Fast zombie damage
    public static final int FASTRANGE = 30; //Fast zombie range
    public static final int FASTMAXHP = 75; //Fast zombie max hp
    public static final float FASTSPEED = 120; //Fast zombie speed
    public static final float FASTCOOLDOWN = 0.75f; //Fast zombie hit cooldown
    public static final int TANKDMG = 30; //Tank zombie damage
    public static final int TANKRANGE = 15; //Tank zombie range
    public static final int TANKMAXHP = 150; //Tank zombie max hp
    public static final float TANKSPEED = 60; //Tank zombie speed
    public static final float TANKCOOLDOWN = 1.5f; //Tank zombie hit cooldown
    
    //Boss constants
    public static final int BOSSPOINTS = 100; //Boss points awarded
    public static final int BOSS1MAXHP = 1000; //First boss' max hp
    public static final int BOSS1DMG = 50; //First boss' dmg
    public static final int BOSS1RANGE = 30; //First boss' attack range
    public static final float BOSS1SPEED = 50; //First boss' speed
    public static final float BOSS1COOLDOWN = 2.0f; //First boss' attack cooldown
    
    //Powerup constants
    public static final int HEALUP = 30; //Heal powerup amount
    public static final int SPEEDUP = 50; //Speed powerup amount
    public static final float SPEEDUPTIME = 10; //Speed powerup duration
    public static final float STRENGTHMULT = 2.0f; //Strength powerup multiplier
    public static final float STRENGTHTIME = 10; //Strength powerup duration
    public static final float KNOCKBACKMULT = 3.0f; //Knockback powerup multiplier
    public static final float KNOCKBACKTIME = 30; //Knockback powerup duration
    public static final float IMMUNITYTIME = 5; //Immunity powerup duration
}
