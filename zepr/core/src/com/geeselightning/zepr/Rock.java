package com.geeselightning.zepr;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Rock {
	Sprite sprite;
	public Rock(Vector2 xy) {
		Random random = new Random();
		int rnd = random.nextInt(4);
		if (rnd == 0) {
			sprite = new Sprite(new Texture(Gdx.files.local("rocks/rock-01.png")));
		} else if (rnd == 1) {
			sprite = new Sprite(new Texture(Gdx.files.local("rocks/rock-02.png")));
		} else if (rnd == 2) {
			sprite = new Sprite(new Texture(Gdx.files.local("rocks/rock-03.png")));
		} else if (rnd == 3) {
			sprite = new Sprite(new Texture(Gdx.files.local("rocks/rock-04.png")));
		}
		sprite.setPosition(xy.x, xy.y);
	}
	
	public boolean updateRock() {
		sprite.setPosition(sprite.getX(), sprite.getY() + 1);
		if (sprite.getY() < 0) { //return true to destroy this rock once its off the world
			return true;
		} else {
			return false;
		}
	}
	
	/*
	private double getDirTo(Vector2 coordinate) {
		Vector2 charCenter = new Vector2(sprite.getX() + (sprite.getWidth()/ 2),
                sprite.getY()+ (sprite.getHeight() / 2));

        // atan2 is uses the signs of both variables the determine the correct quadrant (relative to the character) of the
        // result.
        // Modulus 2pi of the angle must be taken as the angle is negative for the -x quadrants.
        // The angle must first be displaced by 2pi because the Java modulus function can return a -ve value.

        return(Math.atan2((coordinate.x - charCenter.x), (coordinate.y - charCenter.y)) + (2 * Math.PI))
                % (2 * Math.PI);
	}
	*/
}
