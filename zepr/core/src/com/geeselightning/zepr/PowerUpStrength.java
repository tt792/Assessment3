package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;

public class PowerUpStrength extends PowerUp {

    public float timeRemaining = Constant.STRENGTHTIME;

    public PowerUpStrength(Level currentLevel) {
        super(4, new Texture("strength.png"), currentLevel);
    }

    @Override
    public void activate() {
        super.activate();
        super.player.attackDamage *= Constant.STRENGTHMULT;
        super.player.isStronk = true;
        this.getTexture().dispose();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        super.player.attackDamage /= Constant.STRENGTHMULT;
        super.player.isStronk = false;
    }

    @Override
    public void update(float delta) {
        if (active) {
            timeRemaining -= delta;
        }
        if (timeRemaining <= 0) {
            deactivate();
        }
    }
}
