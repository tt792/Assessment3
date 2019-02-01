package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Zombie extends Character {

    private Player player = Player.getInstance();
    public final float hitCooldown = Constant.ZOMBIEHITCOOLDOWN;
    int attackDamage;
    public int hitRange;

    public Zombie(Sprite sprite, Vector2 zombieSpawn, Level currentLevel, int attackDamage, int hitRange) {
        super(sprite, zombieSpawn, currentLevel);
        this.attackDamage = attackDamage;
        this.hitRange = hitRange;
        this.speed = Constant.ZOMBIESPEED;
        this.health = Constant.ZOMBIEMAXHP;
    }

    public void attack(Player player, float delta) {
        if (canHitGlobal(player, hitRange) && hitRefresh > hitCooldown) {
            player.takeDamage(attackDamage);
            hitRefresh = 0;
        } else {
            hitRefresh += delta;
        }
    }

    @Override
    public void update(float delta) {
        //move according to velocity
        super.update(delta);

        // update velocity to move towards player
        // Vector2.scl scales the vector
        velocity = getDirNormVector(player.getCenter()).scl(speed);

        // update direction to face the player
        direction = getDirectionTo(player.getCenter());

        if (health <= 0) {
            currentLevel.zombiesRemaining--;
            currentLevel.aliveZombies.remove(this);
            this.getTexture().dispose();
            player.addPoints(Constant.ZOMBIEPOINTS);
        }
    }
}
