package com.geeselightning.zepr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Rock {
	Sprite sprite = new Sprite(new Texture("rock-01"));
	public Rock(Vector2 xy) {
		sprite.setPosition(xy.x, xy.y);
	}
	
	public boolean updateRock() {
		sprite.setPosition(sprite.getX(), sprite.getY() + 1);
		if (sprite.getY() == Gdx.graphics.getHeight()) {
			return true;
		} else {
			return false;
		}
	}
}
