package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PowerUpStrength extends PowerUp {

    public float timeRemaining = Constant.STRENGTHTIME;

    public PowerUpStrength(Level currentLevel) {
        super(4, new Texture("strengthTemp.png"), currentLevel);
    }

    @Override
    public void activate() {
        super.activate();
        super.player.attackDamage *= Constant.STRENGTHMULT;
        this.getTexture().dispose();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        super.player.attackDamage /= Constant.STRENGTHMULT;
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
