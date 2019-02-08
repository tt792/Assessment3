package com.geeselightning.zepr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PowerUpKnockback extends PowerUp {

    public float timeRemaining = Constant.KNOCKBACKTIME;

    public PowerUpKnockback(Level currentLevel) {
        super(5, new Texture("knockbackTemp.png"), currentLevel);
    }

    @Override
    public void activate() {
        super.activate();
        super.player.knockback *= Constant.KNOCKBACKMULT;
        this.getTexture().dispose();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        super.player.knockback /= Constant.KNOCKBACKMULT;
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
