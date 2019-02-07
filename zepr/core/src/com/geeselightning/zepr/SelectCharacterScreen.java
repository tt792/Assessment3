package com.geeselightning.zepr;

import javax.swing.JOptionPane;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.Color;

public class SelectCharacterScreen implements Screen {

    private Zepr parent;
    private Stage stage;
    private String currentStage;
    private boolean playerSet = false;
    Player player = Player.getInstance();
    private Label characterDescription;
    
    private int stageLink = -1;
    
    static Image currentChosenCharacterImage;

    public SelectCharacterScreen(Zepr zepr) {

        parent = zepr;

        // The stage is the controller which will react to inputs from the user.
        this.stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        
        // Send any input from the user to the stage.
        Gdx.input.setInputProcessor(stage);

        // Importing the necessary assets for the button textures.
        Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        
       
        
        // Creating menu buttons and label to show player's current stage
        Label currentStageLabel = new Label("Current stage: " + currentStage, skin, "title");
        currentStageLabel.setFontScale(0.75f);
        TextButton load = new TextButton("Load", skin);
        TextButton back = new TextButton("<--", skin);
        
        // displaying menu buttons and current stage label
        Table menuBarTable = new Table();
        menuBarTable.setFillParent(true);
        menuBarTable.top();
        menuBarTable.add(back).pad(5);
        menuBarTable.add(load).pad(5);
        menuBarTable.add(currentStageLabel).padLeft(5);
        stage.addActor(menuBarTable);
        
        
        
        // types of players button creations and 'choose your character' label
        Label chooseYourCharacterLabel = new Label("Choose your character:", skin, "subtitle");
        final TextButton nerdy = new TextButton("Nerd", skin);
        final TextButton jock = new TextButton("Jock", skin);
        final TextButton artsy = new TextButton("Artsy", skin); // not yet operational
        currentChosenCharacterImage = new Image(new Texture("placeholderHAHA.png"));
        
        // displaying player selection buttons
        Table characterSelectTable = new Table();
        characterSelectTable.setFillParent(true);
        stage.addActor(characterSelectTable);
        characterSelectTable.center();
       
        characterSelectTable.row();
        characterSelectTable.add(chooseYourCharacterLabel).colspan(4).padBottom(5);
        characterSelectTable.row();
        characterSelectTable.add(currentChosenCharacterImage).size(375).padRight(5);
        characterSelectTable.add(nerdy).pad(5);
        characterSelectTable.add(jock).pad(5);
        characterSelectTable.add(artsy).pad(5);
        characterSelectTable.row();
        
        // creating the button to begin playing the main game , character descriptions, mini-game button
        TextButton play = new TextButton("Start!", skin);
        TextButton minigame = new TextButton("mini-game", skin);
        
        final String nerdyDescription = "\"build all the things!\"";
        final String sportyDescripton = "\"GOTTA GO FAST\"";
        final String artyDescription = "\"creation flows through me...\"";
        final Label characterDescription = new Label("-", skin);
        
        
        // display the Play and mini-game button
        Table bottomTable = new Table();
        bottomTable.setFillParent(true);
        stage.addActor(bottomTable);
        bottomTable.bottom();
       
        
        bottomTable.row();
        bottomTable.add(minigame).pad(10).left();
        bottomTable.add(characterDescription);
        bottomTable.add(play).pad(10).right();
        
        //set the stage to be the town (1st stage)
      		switch(Zepr.progress) {
      		case Zepr.TOWN:
      	       // stageDescription.setText(townDescription);
      	        stageLink = Zepr.TOWN;
      	        currentStage = "TOWN";
      			break;
      		case Zepr.HALIFAX:
      	       // stageDescription.setText(halifaxDescription);
      	        stageLink = Zepr.HALIFAX;
      	      currentStage = "HALIFAX";
      			break;
      		case Zepr.COURTYARD:
      	       // stageDescription.setText(courtyardDescription);
      	        stageLink = Zepr.COURTYARD;
      	      currentStage = "COURTYARD";
      			break;
      		case Zepr.MINIGAME:
      	        //stageDescription.setText(townDescription);
      	        stageLink = Zepr.MINIGAME;
      	      currentStage = "MINIGAME";
      			break;
      		case Zepr.COMPSCI:
      	        //stageDescription.setText(townDescription);
      	        stageLink = Zepr.COMPSCI;
      	      currentStage = "COMPSCI";
      			break;
      		case Zepr.OUTSIDE:
      	        //stageDescription.setText(townDescription);
      	        stageLink = Zepr.OUTSIDE;
      	      currentStage = "OUTSIDE";
      			break;
      		case Zepr.LAW:
      			//stageDescription.setText(lawDescription);
      			stageLink = Zepr.LAW;
      			currentStage = "LAW";
      			break;
      		}
      	currentStageLabel.setText("Current stage: " + currentStage);
        
        
        
        // < debugging > (highlights table border outlines)
        
        //characterSelectTable.setDebug(true); 
        //bottomTable.setDebug(true);
        
        
        
        
        

        // Adding button logic.

        // Defining actions for the back button.
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(Zepr.MENU);
            }
        });


        
        //Defining actions for the nerdy button.

        nerdy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            		currentChosenCharacterImage.setDrawable((Drawable) new SpriteDrawable(new Sprite(new Texture("player1sprite.png"))));
            		nerdy.setColor(Color.GOLD);
            		jock.setColor(Color.CYAN);
            		artsy.setColor(Color.CYAN);
            		player.setType("nerdy");
            		characterDescription.setText(nerdyDescription);
            		playerSet = true;
            }
        });
        
        
        jock.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            	currentChosenCharacterImage.setDrawable((Drawable) new SpriteDrawable(new Sprite(new Texture("player2sprite.png"))));
                player.setType("sporty");
                nerdy.setColor(Color.CYAN);
        			jock.setColor(Color.GOLD);
        			artsy.setColor(Color.CYAN);
        			characterDescription.setText(sportyDescripton);
                playerSet = true;
            }
        });
        
        artsy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            	currentChosenCharacterImage.setDrawable((Drawable) new SpriteDrawable(new Sprite(new Texture("player3sprite.png"))));
            		player.setType("artsy");
                	nerdy.setColor(Color.CYAN);
        			jock.setColor(Color.CYAN);
        			artsy.setColor(Color.GOLD);
        			characterDescription.setText(artyDescription);
                playerSet = true;
            }
        });
        
        
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (playerSet == true) {
                    parent.changeScreen(Zepr.TOWN);
                }
            }
        });
        
        load.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                infoBox("this isn't coded yet.", "ZEPR");
                }
            
        });
        
        minigame.addListener(new ChangeListener() {
        	@Override
        	public void changed(ChangeEvent event, Actor actor) {
        		if (playerSet == true) {
        			Zepr.progress = Zepr.MINIGAME;
        			parent.changeScreen(Zepr.MINIGAME);
        		}
        	}
        });
        
        
//  //////Working on Load
//        load.addListener(new ChangeListener() {
//        	@Override
//        	public void changed(ChangeEvent event, Actor actor) {
//        		FileHandle file = Gdx.files.internal("data/saveFile.txt");
//        		String text = file.readString();
//        		System.out.println(text.toString());
//        	}
//        	
//        });
//        
//        //////Working on save
//        save.addListener(new ChangeListener() {
//        	@Override
//        	public void changed(ChangeEvent event, Actor actor) {
//        		FileHandle file = Gdx.files.local("data/saveFile.txt");
//        		file.writeString("dat", false);
//        	}
//        	
//        });
        

    }
    

    
    // something we probably don't need and will 
    // remove quite soon, but its staying for now
	public static void infoBox(String infoMessage, String titleBar)
	{
	    JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	

	
	
    @Override
    public void render(float delta) {
        // Clears the screen to black.
    		Gdx.gl.glClearColor(0.11f, 0.29f, 0.004f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draws the stage.
        this.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        this.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Update the screen when the window resolution is changed.
        this.stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        // Dispose of assets when they are no longer needed.
        stage.dispose();
    }

}
