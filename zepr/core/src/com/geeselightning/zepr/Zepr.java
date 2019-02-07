package com.geeselightning.zepr;

import com.badlogic.gdx.Game;

public class Zepr extends Game {

	private LoadingScreen loadingScreen;
	private MenuScreen menuScreen;
	private Level level;
	private SelectCharacterScreen selectCharacterScreen;

	// The progress is the integer representing the last level completed. i.e. 3 for Town
	public static int progress = 3;

	//the different screens in the game
	public final static int MENU = 0;
	public final static int SELECT = 2;
	public final static int TOWN = 3; //level 1
	public final static int HALIFAX = 4; //level 2
	public final static int COURTYARD = 5; //level 3 - BOSS
	public final static int MINIGAME = 6; //The minigame
	public final static int COMPSCI= 7; //level 4
	public final static int OUTSIDE = 8; //level 5
	public final static int LAW = 9; //level 6 - BOSS2
	public final static int COMPLETE = 10; //the completed level screen


	public void changeScreen(int screen) {
		switch(screen) {
			case MENU:
				if (menuScreen == null) menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				break;
			case SELECT:
				selectCharacterScreen = new SelectCharacterScreen(this);
				this.setScreen(selectCharacterScreen);
				break;
			case TOWN:
				level = new TownLevel(this);
				this.setScreen(level);
				break;
			case HALIFAX:
				level = new HalifaxLevel(this);
				this.setScreen(level);
				break;
			case COURTYARD: //make sure this only spawns the boss
				level = new CourtyardLevel(this);
				this.setScreen(level);
				break;
			case MINIGAME:
				level = new MinigameLevel(this);
				this.setScreen(level);
				break;
			case COMPSCI:
				level = new CompSciLevel(this);
				this.setScreen(level);
				break;
			case OUTSIDE:
				level = new OutsideLevel(this);
				this.setScreen(level);
				break;
			case LAW: //make sure this only spawns the boss also
				level =  new LawLevel(this);
				this.setScreen(level);
				break;
		}
	}

	@Override
	public void create() {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}
}