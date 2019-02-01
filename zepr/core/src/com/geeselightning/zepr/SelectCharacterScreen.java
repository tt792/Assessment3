package com.geeselightning.zepr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.Color;

public class SelectCharacterScreen implements Screen {

    private Zepr parent;
    private Stage stage;
    //private Label stageDescription;
    private String currentStage = "Stage 1";
    //private Label characterDescription;
    //private int stageLink = -1;
    private boolean playerSet = false;
    Player player = Player.getInstance();

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
        
        
        
        
        

        // Creating stage buttons.
        //final TextButton town = new TextButton("Town", skin);
        
        
        
        
        

        // Creating character buttons.
        TextButton nerdy = new TextButton("Nerd",skin);
        TextButton jock = new TextButton("Jock",skin);
        
        

        // Creating other buttons.
        TextButton play = new TextButton("Start game!", skin);
        TextButton load = new TextButton("Load", skin);
        TextButton back = new TextButton("<-", skin);

        // Creating current stage label.
        Label currentStageLabel = new Label("Current stage: " + currentStage, skin, "subtitle");

        // Adding menu bar for the 'other buttons' and current stage label.
        Table menuBar = new Table();
        menuBar.setFillParent(true);
        
        menuBar.setDebug(true); // Adds borders for the table.
        stage.addActor(menuBar);

        menuBar.top();
        //menuBar.row();
        menuBar.add(back).pad(5);
        menuBar.add(load).pad(5);
        menuBar.add(currentStageLabel).pad(5).right();

        // Adding stage selector buttons.
        Table stageSelect = new Table();
        stageSelect.setFillParent(true);
        
        stageSelect.setDebug(true); // Adds borders for the table.
        stage.addActor(stageSelect);

        stageSelect.right();

        //stageSelect.row();

        //stageSelect.row().pad(50,0,100,0);
        //stageSelect.add(town).pad(10);
        
        
        //removed the other stage buttons
        //stageSelect.add(halifax).pad(10);
        //stageSelect.add(courtyard).pad(10);
        //stageSelect.row();
        //stageSelect.add(stageDescription).width(1000f).colspan(3);

        
        
        // Adding select character Buttons
        stageSelect.row().right();
        stageSelect.add(nerdy);
        stageSelect.add(jock);

        //stageSelect.row().right();
        //stageSelect.add(characterDescription).width(500f).colspan(3);

        // Adding play button at the bottom.
        Table bottomTable = new Table();
        bottomTable.setFillParent(true);
        // bottomTable.setDebug(true); // Adds borders for the table.
        stage.addActor(bottomTable);

        bottomTable.bottom();
        bottomTable.add(play).pad(10).center();

        // Adding button logic.

        // Defining actions for the back button.
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(Zepr.MENU);
            }
        });

        /* Defining actions for the town button.
        town.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stageDescription.setText(townDescription);
                stageLink = Zepr.TOWN;
            }
        });
        
        */
        
        //removed the other buttons from the game
        /*
        if (parent.progress <= parent.TOWN) {
            halifax.setColor(Color.DARK_GRAY);
            halifax.getLabel().setColor(Color.DARK_GRAY);
        } else {
            // Defining actions for the halifax button.
            halifax.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    stageDescription.setText(halifaxDescription);
                    stageLink = Zepr.HALIFAX;
                }
            });
        }
		
        if (parent.progress <= parent.HALIFAX) {
            courtyard.setColor(Color.DARK_GRAY);
            courtyard.getLabel().setColor(Color.DARK_GRAY);
        } else {
            // Defining actions for the courtyard button.
            courtyard.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    stageDescription.setText(courtyardDescription);
                    stageLink = Zepr.COURTYARD;
                }
            });
        }
		*/
        
        //Defining actions for the nerdy button.

        nerdy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //characterDescription.setText(nerdyDescription);
                player.setType("nerdy");
                playerSet = true;
            }
        });
        jock.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //characterDescription.setText(jockDescripton);
                player.setType("jock");
                playerSet = true;
            }
        });

        // Defining actions for the play button.
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (playerSet == true) {
                    parent.changeScreen(Zepr.TOWN);
                }
            }
        });

    }

    @Override
    public void render(float delta) {
        // Clears the screen to black.
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
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
