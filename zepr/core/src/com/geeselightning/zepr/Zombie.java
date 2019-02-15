package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Zombie extends Character {

    private Player player = Player.getInstance();
    
    //Combat variables
    float hitCooldown;
    int attackDamage;
    int points;
    public int hitRange;

    //Type of Zombie (0=Normal, 1=Fast, 2=Tank)
    public String type;
    
    boolean hit = false;

    /**
     * Constructor for the Zombie Class
     * @implementation (F4) creates the zombies for the start of a level
     * @implementation (F7) creates the bosses for the last wave of levels 3 and 6
     */
    public Zombie(Sprite sprite, Vector2 zombieSpawn, Level currentLevel, int attackDamage, int hitRange, int points, int health, float speed, float hitCooldown, String type) {
        super(sprite, zombieSpawn, currentLevel);
        this.attackDamage = attackDamage;
        this.hitRange = hitRange;
        this.points = points;
        this.speed = speed;
        this.health = health;
        this.hitCooldown = hitCooldown;
        this.type = type;
    }

    public void attack(Player player, float delta) {
        if (canHitGlobal(player, hitRange) && hitRefresh > hitCooldown) {
            player.takeDamage(attackDamage);
            hitRefresh = 0;
        } else {
            hitRefresh += delta;
        }
    }
    
    public void hit() {
    	hit = true;
    }

    @Override
    public void update(float delta) {
        //move according to velocity
        super.update(delta);
       
        // update velocity to move towards player
        // Vector2.scl scales the vector
        if (!hit)
        	velocity = getDirNormVector(player.getCenter()).scl(speed);
        else {
        	velocity = getDirNormVector(player.getCenter()).scl(-(speed * player.knockback));
        	hit = false;
        }
        
        // update direction to face the player
        direction = getDirectionTo(player.getCenter());

        if (health <= 0) {
            currentLevel.zombiesRemaining--;
            currentLevel.aliveZombies.remove(this);
            this.getTexture().dispose();
            player.addPoints(points);
        }
    }
}
