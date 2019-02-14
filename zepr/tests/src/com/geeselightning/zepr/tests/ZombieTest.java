package com.geeselightning.zepr.tests;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.geeselightning.zepr.Constant;
import com.geeselightning.zepr.Player;
import com.geeselightning.zepr.Zombie;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class ZombieTest {

    Player player = Player.getInstance();

    @Test
    // Test 3.1.1
    public void zombieDoesNoDamageToPlayerWhenAtMaxRange() {
        player.respawn(Constant.ORIGIN, null);

        Zombie zombie = new Zombie(new Sprite(), new Vector2(player.getCenter().x, player.getCenter().y - Constant.ZOMBIERANGE), null, Constant.ZOMBIEDMG, Constant.ZOMBIERANGE, Constant.ZOMBIEPOINTS, Constant.ZOMBIEMAXHP, Constant.ZOMBIESPEED,Constant.ZOMBIEHITCOOLDOWN, "Normal");
        double originalHealth = player.getHealth();
        zombie.attack(player, 0);

        assertEquals("Player on the edge of range should not take damage when the zombie attacks.",
                player.getHealth(), originalHealth, 0.1);
    }

    @Test
    // Test 3.1.2
    public void zombieDoesDamageToPlayerWhenInRange() {
        player.respawn(Constant.ORIGIN, null);

        Zombie zombie = new Zombie(new Sprite(), new Vector2(player.getCenter().x, player.getCenter().y - Constant.ZOMBIERANGE + 5), null, Constant.ZOMBIEDMG, Constant.ZOMBIERANGE, Constant.ZOMBIEPOINTS, Constant.ZOMBIEMAXHP, Constant.ZOMBIESPEED,Constant.ZOMBIEHITCOOLDOWN, "Normal");
        double originalHealth = player.getHealth();
        zombie.attack(player, 0);

        assertNotEquals("Player within range should take damage when the zombie attacks.",
                player.getHealth(), originalHealth, 0.1);
    }


    @Test
    // Test 3.1.3
    public void zombieDoesNoDamageToPlayerOutOfRange() {
        player.respawn(Constant.ORIGIN, null);

        Zombie zombie = new Zombie(new Sprite(), new Vector2(player.getCenter().x, player.getCenter().y - Constant.ZOMBIERANGE - 5), null, Constant.ZOMBIEDMG, Constant.ZOMBIERANGE, Constant.ZOMBIEPOINTS, Constant.ZOMBIEMAXHP, Constant.ZOMBIESPEED,Constant.ZOMBIEHITCOOLDOWN, "Normal");
        double originalHealth = player.getHealth();
        zombie.attack(player, 0);

        assertEquals("Player outside of range should not take damage when the zombie attacks.",
                player.getHealth(), originalHealth, 0.1);
    }
    
    @Test
    //V.2 Test
    //Test 3.1.4    
    public void differentZombiesDoDifferentDamage() {
    	player.respawn(Constant.ORIGIN, null);
    	
	   	////create the three different zombies
	   	Zombie normalZ = new Zombie(new Sprite(), new Vector2(player.getCenter().x, player.getCenter().y ), null, Constant.ZOMBIEDMG, Constant.ZOMBIERANGE, Constant.ZOMBIEPOINTS, Constant.ZOMBIEMAXHP, Constant.ZOMBIESPEED,Constant.ZOMBIEHITCOOLDOWN, "Normal");
	   	Zombie fastZ = new Zombie(new Sprite(), new Vector2(player.getCenter().x, player.getCenter().y ), null, Constant.FASTDMG, Constant.FASTRANGE, Constant.ZOMBIEPOINTS, Constant.FASTMAXHP, Constant.FASTSPEED,Constant.FASTCOOLDOWN, "FAST");
	   	Zombie tankZ = new Zombie(new Sprite(), new Vector2(player.getCenter().x, player.getCenter().y ), null, Constant.TANKDMG, Constant.TANKRANGE, Constant.ZOMBIEPOINTS, Constant.TANKMAXHP, Constant.TANKSPEED,Constant.TANKCOOLDOWN, "TANK");
	    	
	    	
	    ///the damage must be different  	 
	   	 
	   	double currentHealth = player.getHealth();    	 
	   	normalZ.attack(player,0); 
	   	double normalDamage = currentHealth - player.getHealth();
	   	 
	   	currentHealth = player.getHealth();
	   	fastZ.attack(player, 0);
	   	double fastDamage = currentHealth - player.getHealth();
	   	 
	   	currentHealth = player.getHealth();
	   	tankZ.attack(player, 0);
	   	double tankDamage = currentHealth - player.getHealth();
	   	 
	   	assertNotEquals("Damage should be different", normalDamage, fastDamage);
	   	assertNotEquals("Damage should be different", tankDamage, fastDamage);
	   	assertNotEquals("Damage should be different", normalDamage, tankDamage);
    	
    }




    @Test
    // Test 3.2.1
    public void zombieCannotAttackBeforeCooldownComplete() {
        player.respawn(Constant.ORIGIN, null);

        Zombie zombie = new Zombie(new Sprite(), new Vector2(player.getCenter().x, player.getCenter().y - Constant.ZOMBIERANGE + 5), null, Constant.ZOMBIEDMG, Constant.ZOMBIERANGE, Constant.ZOMBIEPOINTS, Constant.ZOMBIEMAXHP, Constant.ZOMBIESPEED,Constant.ZOMBIEHITCOOLDOWN, "Normal");
        double originalHealth = player.getHealth();
        zombie.attack(player, 0);
        zombie.attack(player, 0);

        assertEquals("Player should only have taken one hit if attacked again before cooldown complete.",
                originalHealth - Constant.ZOMBIEDMG, player.getHealth(), 0.1);
    }

    @Test
    // Test 3.2.2
    public void zombieCanAttackAfterCooldownComplete() {
        player.respawn(Constant.ORIGIN, null);

        Zombie zombie = new Zombie(new Sprite(), new Vector2(player.getCenter().x, player.getCenter().y - Constant.ZOMBIERANGE + 5), null, Constant.ZOMBIEDMG, Constant.ZOMBIERANGE, Constant.ZOMBIEPOINTS, Constant.ZOMBIEMAXHP, Constant.ZOMBIESPEED,Constant.ZOMBIEHITCOOLDOWN, "Normal");
        double originalHealth = player.getHealth();
        zombie.attack(player, 0);
        // zombie will not attack this go so has to be called a third time
        zombie.attack(player, Constant.ZOMBIEHITCOOLDOWN + 1);
        zombie.attack(player, 0);

        assertEquals("Player should have taken two hits if attacked again after cooldown complete.",
                originalHealth - (2 * Constant.ZOMBIEDMG), player.getHealth(), 0.1);
    }
    
    @Test
    //V.2 Test
    //Test 3.3.1 
    public void differentZombieStats() {
    	
    	player.respawn(Constant.ORIGIN, null);
    	
    	 ////create the three different zombies
    	 Zombie normalZ = new Zombie(new Sprite(), new Vector2(player.getCenter().x, player.getCenter().y ), null, Constant.ZOMBIEDMG, Constant.ZOMBIERANGE, Constant.ZOMBIEPOINTS, Constant.ZOMBIEMAXHP, Constant.ZOMBIESPEED,Constant.ZOMBIEHITCOOLDOWN, "Normal");
    	 Zombie fastZ = new Zombie(new Sprite(), new Vector2(player.getCenter().x, player.getCenter().y ), null, Constant.FASTDMG, Constant.FASTRANGE, Constant.ZOMBIEPOINTS, Constant.FASTMAXHP, Constant.FASTSPEED,Constant.FASTCOOLDOWN, "FAST");
    	 Zombie tankZ = new Zombie(new Sprite(), new Vector2(player.getCenter().x, player.getCenter().y ), null, Constant.TANKDMG, Constant.TANKRANGE, Constant.ZOMBIEPOINTS, Constant.TANKMAXHP, Constant.TANKSPEED,Constant.TANKCOOLDOWN, "TANK");
         
    	 ///The speeds are different in the different types of Zombies    	 
    	 assertNotEquals("Speeds of Zombies should be different", normalZ.speed, fastZ.speed);
    	 assertNotEquals("Speeds of Zombies should be different", normalZ.speed, tankZ.speed);
    	 assertNotEquals("Speeds of Zombies should be different", tankZ.speed, fastZ.speed);
    	 
    	 ///the starting health is different in the different types of Zombies
    	 assertNotEquals("starting health of zombies should be different", normalZ.getHealth(),tankZ.getHealth());
    	 assertNotEquals("starting health of zombies should be different", fastZ.getHealth(),tankZ.getHealth());
    	 assertNotEquals("starting health of zombies should be different", normalZ.getHealth(),fastZ.getHealth());
    	 
    	 ///hit range should be different
    	 assertNotEquals("hit range should be different", normalZ.hitRange,tankZ.hitRange);
    	 assertNotEquals("hit range should be different", fastZ.hitRange,tankZ.hitRange);
    	 assertNotEquals("hit range should be different", normalZ.hitRange,fastZ.hitRange);
    	 
    	 ///different cooldown (the values are the same as these constants)
    	 assertNotEquals("cool downs should be different", Constant.ZOMBIEHITCOOLDOWN,Constant.FASTCOOLDOWN );
    	 assertNotEquals("cool downs should be different", Constant.TANKCOOLDOWN,Constant.FASTCOOLDOWN );
    	 assertNotEquals("cool downs should be different", Constant.ZOMBIEHITCOOLDOWN,Constant.TANKCOOLDOWN );
    	 
    	 ///the damage must be different      	 
    	
    	 
    	 assertNotEquals("Damage should be different", Constant.ZOMBIEDMG, Constant.FASTDMG);
    	 assertNotEquals("Damage should be different", Constant.TANKDMG, Constant.FASTDMG);
    	 assertNotEquals("Damage should be different", Constant.ZOMBIEDMG, Constant.TANKDMG);
    }


}
