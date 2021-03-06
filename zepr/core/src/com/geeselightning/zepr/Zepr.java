package com.geeselightning.zepr;

import com.badlogic.gdx.Game;

/**
 * @implementation (F1) Controls flow of the game, changing levels when the player completes one
 */
public class Zepr extends Game {

	private LoadingScreen loadingScreen;
	private MenuScreen menuScreen;
	private Level level;
	private SelectCharacterScreen selectCharacterScreen;

	// The progress is the integer representing the last level completed. i.e. 1 for Town
	public static int progress = 1;

	//the different screens in the game
	public final static int MENU = -1;
	public final static int SELECT = 0;
	public final static int TOWN = 1; //level 1
	public final static int HALIFAX = 2; //level 2
	public final static int COURTYARD = 3; //level 3 - BOSS
	public final static int MINIGAME = 4; //The minigame
	public final static int COMPSCI= 5; //level 4
	public final static int OUTSIDE = 6; //level 5
	public final static int LAW = 7; //level 6 - BOSS2
	public final static int COMPLETE = 8; //the completed level screen

	public void changeScreen(int screen) {
		switch(screen) {
			case MENU:
				if (menuScreen == null) menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				break;
			case SELECT:
				selectCharacterScreen = new SelectCharacterScreen(this); //this is the line which creates the problem
				this.setScreen(selectCharacterScreen);
				break;
			case TOWN:
				level = null;
				level = new TownLevel(this);
				this.setScreen(level);
				break;
			case HALIFAX:
				level = null;
				level = new HalifaxLevel(this);
				this.setScreen(level);
				break;
			case COURTYARD: //make sure this only spawns the boss
				level = null;
				level = new CourtyardLevel(this);
				this.setScreen(level);
				break;
			case MINIGAME:
				level = null;
				level = new MinigameLevel(this);
				this.setScreen(level);
				break;
			case COMPSCI:
				level = null;
				level = new CompSciLevel(this);
				this.setScreen(level);
				break;
			case OUTSIDE:
				level = null;
				level = new OutsideLevel(this);
				this.setScreen(level);
				break;
			case LAW: //make sure this only spawns the boss also
				level = null;
				level =  new LawLevel(this);
				this.setScreen(level);
				break;
		}
	}

	@Override
	public void create() {
		loadingScreen = new LoadingScreen(this);
		selectCharacterScreen = new SelectCharacterScreen(this);
		level = new TownLevel(this);
		setScreen(loadingScreen);
	}
}