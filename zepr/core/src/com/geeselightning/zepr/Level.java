package com.geeselightning.zepr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;
import java.util.Random;


public class Level implements Screen {

    protected Zepr parent;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Player player;
    protected ArrayList<Zombie> aliveZombies = new ArrayList<Zombie>();
    private String mapLocation;
    private Vector2 playerSpawn;
    public ArrayList<Vector2> zombieSpawnPoints;
    private ZeprInputProcessor inputProcessor = new ZeprInputProcessor();
    protected boolean isPaused;
    private Stage stage;
    private Table table;
    private Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private int[] waves;
    private int currentWave = 1;
    protected int zombiesRemaining; // the number of zombies left to kill to complete the wave
    private int zombiesToSpawn; // the number of zombies that are left to be spawned this wave
    private boolean pauseButton = false;
    private boolean bossSpawned = false; 
	Texture shield = new Texture("shield.png");
	Sprite shieldSprite = new Sprite(shield);
	Texture fast = new Texture("fast.png");
	Sprite fastSprite = new Sprite(fast);
    
    //minigame variables
    private Rock[] rockList = new Rock[0]; //create the empty list of rocks
    private float timer = 0f; //time the minigame
    private int minigameHealth = 50; //the players health while in the minigame
    private int rockSpawn = 0; //when to spawn a rock
    
    
    Texture blank;
    Vector2 powerSpawn;
    PowerUp currentPowerUp = null;

    Label progressLabel = new Label("", skin);
    Label healthLabel = new Label("", skin);
    Label pointsLabel = new Label("", skin);
    Label powerupLabel = new Label("", skin);
    //for the regular game
    public Level(Zepr zepr, String mapLocation, Vector2 playerSpawn, ArrayList<Vector2> zombieSpawnPoints, int[] waves, Vector2 powerSpawn) {
        parent = zepr;
        this.mapLocation = mapLocation;
        this.playerSpawn = playerSpawn;
        this.zombieSpawnPoints = zombieSpawnPoints;
        this.isPaused = false;
        this.blank = new Texture("blank.png");
        this.powerSpawn = powerSpawn;

        // Set up data for first wave of zombies
        this.waves = waves;
        this.zombiesRemaining = waves[0];
        this.zombiesToSpawn = zombiesRemaining;

        // Creating a new libgdx stage to contain the pause menu and in game UI
        this.stage = new Stage(new ScreenViewport());

        // Creating a table to hold the UI and pause menu
        this.table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
    }
    
    //for the minigame
    public Level(Zepr zepr, String mapLocation, Vector2 playerSpawn) {
    	parent = zepr;
    	this.mapLocation = mapLocation;
    	this.playerSpawn = playerSpawn;
    	this.zombieSpawnPoints = null;
    	this.isPaused = false;
    	this.stage = new Stage(new ScreenViewport());
    	this.table = new Table();
    	table.setFillParent(true);
    	stage.addActor(table);
    }


    /**
     * Called once the stage is complete to update the game progress
     */
    protected void complete() {}


    /**
     * Called when the player's health <= 0 to end the stage.
     */
    public void gameOver() {
        isPaused = true;
        parent.setScreen(new TextScreen(parent, "You died. \n You had " + player.getPoints() + " points"));
    }


    /**
     * Used for collision detection between characters in Character.update()
     *
     * @return ArrayList containing all the characters currently in the level
     */
    public ArrayList<Character> getCharacters() {
        ArrayList<Character> characters = new ArrayList<Character>();

        for (Zombie zombie : aliveZombies) {
            characters.add(zombie);
        }

        characters.add(player);

        return characters;
    }

    /**
     * Spawns multiple zombies cycling through spawnPoints until the given amount have been spawned.
     *
     * @param spawnPoints locations where zombies should be spawned on this stage
     * @param amount number of zombies to spawn
     *
     * @return the number of zombies that failed to spawn
     */
    public int spawnZombies(int amount, ArrayList<Vector2> spawnPoints) {
        int notSpawned = 0;
        for (int i = 0; i < amount; i++) {
        	Zombie zombie;
        	
        	//Create a new RNG for the different zombie types
        	Random rand = new Random();
        	int n = rand.nextInt(1000);
        	
        	//See if its a boss wave yet
        	/*
        	if (currentWave == 3 && !bossSpawned) { //change to spawn on the 3rd level instead
        		zombie = (new Zombie(new Sprite(new Texture("zombie01.png")),
        				spawnPoints.get(i % spawnPoints.size()), this, Constant.BOSS1DMG, Constant.BOSS1RANGE, Constant.BOSSPOINTS, Constant.BOSS1MAXHP, Constant.BOSS1SPEED, Constant.BOSS1COOLDOWN, "BOSS1"));
        		bossSpawned = true;
        		zombie.scale(2);
        	}
        	*/
        	
        	//If the random number is less than the chance to spawn a special zombie
        	if (n <= Constant.SPECIALCHANCE) { ///change back to else if
        		int m = rand.nextInt(2);
        		
        		if(m == 1) //Create a fast zombie
        			zombie = (new Zombie(new Sprite(new Texture("zombie02.png")),
        					spawnPoints.get(i % spawnPoints.size()), this, Constant.FASTDMG, Constant.FASTRANGE, Constant.SPECIALPOINTS, Constant.FASTMAXHP, Constant.FASTSPEED, Constant.FASTCOOLDOWN, "FAST"));
        		else //Create a tank zombie
        			zombie = (new Zombie(new Sprite(new Texture("zombie03.png")),
        					spawnPoints.get(i % spawnPoints.size()), this, Constant.TANKDMG, Constant.TANKRANGE, Constant.SPECIALPOINTS, Constant.TANKMAXHP, Constant.TANKSPEED, Constant.TANKCOOLDOWN, "TANK"));
        	}
        	else {
        		zombie = (new Zombie(new Sprite(new Texture("zombie01.png")),
                        spawnPoints.get(i % spawnPoints.size()), this, Constant.ZOMBIEDMG, Constant.ZOMBIERANGE, Constant.ZOMBIEPOINTS, Constant.ZOMBIEMAXHP, Constant.ZOMBIESPEED, Constant.ZOMBIEHITCOOLDOWN, "ZOMB"));
        	}
      
            // Check there isn't already a zombie there, or they will be stuck
            boolean collides = false;
            for (Zombie otherZombie : aliveZombies) {
                if (zombie.collidesWith(otherZombie)) {
                    collides = true;
                    //increment? counter as it didn't spawn.
                    notSpawned++;
                }
            }

            if (!collides) {
                aliveZombies.add(zombie);
            }
        }
       
        return notSpawned;
    }
 


    /**
     * Used for collision detection between the player and map
     *
     * @return boolean if the point (x, y) is in a blocked tile
     */
    public boolean isBlocked(float x, float y) {
        TiledMapTileLayer collisionLayer = (TiledMapTileLayer) map.getLayers().get("collisionLayer");
        Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));

        // have to include this in case this cell is transparent on the collisionLayer
        if (cell == null || cell.getTile() == null) {
            return false;
        }

        MapProperties properties = cell.getTile().getProperties();
        // we have to add the property now because the properties don't load from the map file
        properties.put("solid", null);

        return properties.containsKey("solid");
    }


    /**
     * Converts the mousePosition which is a Vector2 representing the coordinates of the mouse within the game window
     * to a Vector2 of the equivalent coordinates in the world.
     *
     * @return Vector2 of the mouse position in the world.
     */
    public Vector2 getMouseWorldCoordinates() {
        // Must first convert to 3D vector as camera.unproject() does not take 2D vectors.
        Vector3 screenCoordinates = new Vector3(inputProcessor.mousePosition.x, inputProcessor.mousePosition.y, 0);
        Vector3 worldCoords3 = camera.unproject(screenCoordinates);

        return new Vector2(worldCoords3.x, worldCoords3.y);
    }

    @Override
    public void show() {
        // Start the stage unpaused.
        isPaused = false;

        // Loads the testmap.tmx file as map.
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load(mapLocation);

        // renderer renders the .tmx map as an orthogonal (top-down) map.
        renderer = new OrthogonalTiledMapRenderer(map);
        // It is only possible to view the render of the map through an orthographic camera.
        camera = new OrthographicCamera();

        //retrieve player instance and reset it
        player = Player.getInstance();
        player.respawn(playerSpawn, this);

        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render(float delta) { //if the game is not in the minigame act normally, if its the minigame then do something else
        if (isPaused) {
            // Clears the screen to black.
            Gdx.gl.glClearColor(0f, 0f, 0f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            TextButton resume = new TextButton("Resume", skin);
            TextButton exit = new TextButton("Exit", skin);

            if (!pauseButton) {

                table.clear();

                table.center();
                table.add(resume).pad(10);
                table.row();
                table.add(exit);
                pauseButton = true;

            }

            // Input processor has to be changed back once unpaused.
            Gdx.input.setInputProcessor(stage);

            // Defining actions for the resume button.
            resume.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    isPaused = false;
                    // Change input processor back
                    Gdx.input.setInputProcessor(inputProcessor);
                    pauseButton = false;
                }
            });

            // Defining actions for the exit button.
            exit.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    parent.changeScreen(Zepr.SELECT);
                }
            });

            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
        } else {
        	if (Zepr.progress != Zepr.MINIGAME) { //if in the non minigame <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	            // Clears the screen to black.
	            Gdx.gl.glClearColor(0f, 0f, 0f, 1);
	            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	            table.clear();
	
	            // Try to spawn all zombie in the stage and update zombiesToSpawn with the amount that failed to spawn
	            //change here to be so that zombies only spawn at the levels: 1, 2, 4, 5
	            //and that the bosses spawn at 3 and 6
	            zombiesToSpawn = spawnZombies(zombiesToSpawn, zombieSpawnPoints);
	
	            // Spawn a power up and the end of a wave, if there isn't already a powerUp on the level
	            if (zombiesRemaining == 0 && currentPowerUp == null) {
	                int random = (int )(5);//Math.random() * 5 + 1);
	                if (random == 1) {
	                    currentPowerUp = new PowerUpHeal(this);
	                } else if (random == 2) {
	                    currentPowerUp = new PowerUpSpeed(this);
	                } else if (random == 3) {
	                    currentPowerUp = new PowerUpImmunity(this);
	                } else if (random == 4) {
	                	currentPowerUp = new PowerUpStrength(this);
	                } else {
	                	currentPowerUp = new PowerUpKnockback(this);
	                }
	            }
	
	            if (zombiesRemaining == 0) {
	                // Wave complete, increment wave number
	                currentWave++;
	                if (currentWave > waves.length) {
	                    // Level completed, back to select screen and complete stage.
	                    // If stage is being replayed complete() will stop progress being incremented.
	                    isPaused = true;
	                    complete(); //what does this do? how to increment the level?
	                    if (parent.progress == parent.COMPLETE) {
	                        parent.setScreen(new TextScreen(parent, "Game completed."));
	                    } else {
	                        parent.setScreen(new TextScreen(parent, "Level completed."));
	                    }
	                } else {
	                    // Update zombiesRemaining with the number of zombies of the new wave
	                    zombiesRemaining = waves[currentWave - 1];
	                    zombiesToSpawn = zombiesRemaining;
	                }
	            }
	
	            // Keep the player central in the screen.
	            camera.position.set(player.getCenter().x, player.getCenter().y, 0);
	            camera.update();
	
	            renderer.setView(camera);
	            renderer.render();
	
	            renderer.getBatch().begin();
	
	            player.draw(renderer.getBatch());
	
	            // Resolve all possible attacks
	            for (int i = 0; i < aliveZombies.size(); i++) {
	                Zombie zombie = aliveZombies.get(i);
	                // Zombies will only attack if they are in range, the attack has cooled down, and they are
	                // facing a player.
	                // Player will only attack in the reverse situation but player.attack must also be true. This is
	                //controlled by the ZeprInputProcessor. So the player will only attack when the user clicks.
	                if (player.attack) {
	                    player.attack(zombie, delta);
	                }
	                zombie.attack(player, delta);
	
	                // Draw zombies
	                zombie.draw(renderer.getBatch());
	
	                // Draw zombie health bars
	                //int fillAmount = (int) ((zombie.getHealth() / 100) * 32);
	                //renderer.getBatch().setColor(Color.BLACK);
                	int temp = (int) (32);
	                //change so that \/\/ is different for the different types of zombies
	                if (zombie.type == "BOSS1") {
		                int fillAmount = (int) ((zombie.getHealth() / Constant.BOSS1MAXHP) * 32);
		                renderer.getBatch().setColor(Color.BLACK);
	                    renderer.getBatch().draw(blank, zombie.getX() - (8), zombie.getY()+32, temp, 3); //draw health bar aligned with the middle of the zombie
	                    renderer.getBatch().setColor(Color.RED);
	                    renderer.getBatch().draw(blank, zombie.getX() - (8), zombie.getY()+33, fillAmount, 1);
	                    renderer.getBatch().setColor(Color.WHITE);
	                } else if (zombie.type == "FAST") {
		                int fillAmount = (int) ((zombie.getHealth() / Constant.FASTMAXHP) * 32);
		                renderer.getBatch().setColor(Color.BLACK);
	                    renderer.getBatch().draw(blank, zombie.getX() - (8), zombie.getY()+32, temp, 3);
	                    renderer.getBatch().setColor(Color.RED);
	                    renderer.getBatch().draw(blank, zombie.getX() - (8), zombie.getY()+33, fillAmount, 1);
	                    renderer.getBatch().setColor(Color.WHITE);
	                } else if (zombie.type == "ZOMB") {
		                int fillAmount = (int) ((zombie.getHealth() / Constant.ZOMBIEMAXHP) * 32);
		                renderer.getBatch().setColor(Color.BLACK);
	                    renderer.getBatch().draw(blank, zombie.getX() - (8), zombie.getY()+32, temp, 3);
	                    renderer.getBatch().setColor(Color.RED);
	                    renderer.getBatch().draw(blank, zombie.getX() - (8), zombie.getY()+33, fillAmount, 1);
	                    renderer.getBatch().setColor(Color.WHITE);
	                } else if (zombie.type == "TANK") {
		                int fillAmount = (int) ((zombie.getHealth() / Constant.TANKMAXHP) * 32);
		                renderer.getBatch().setColor(Color.BLACK);
	                    renderer.getBatch().draw(blank, zombie.getX() - (8), zombie.getY()+32, temp, 3);
	                    renderer.getBatch().setColor(Color.RED);
	                    renderer.getBatch().draw(blank, zombie.getX() - (8), zombie.getY()+33, fillAmount, 1);
	                    renderer.getBatch().setColor(Color.WHITE);
	                } else {
		                int fillAmount = (int) ((zombie.getHealth() / Constant.ZOMBIEMAXHP) * 32);
		                renderer.getBatch().setColor(Color.BLACK);
	                    renderer.getBatch().draw(blank, zombie.getX() - (8), zombie.getY()+32, temp, 3);
	                    renderer.getBatch().setColor(Color.RED);
	                    renderer.getBatch().draw(blank, zombie.getX() - (8), zombie.getY()+33, fillAmount, 1);
	                    renderer.getBatch().setColor(Color.WHITE);
	                }
	            }
	
	            if (currentPowerUp != null) {
	                // Activate the powerup up if the player moves over it and it's not already active
	                if (currentPowerUp.overlapsPlayer() && !currentPowerUp.active) {
	                    currentPowerUp.activate();
	                }
	                // Only render the powerup if it is not active, otherwise it disappears
	                if (!currentPowerUp.active) {
	                    currentPowerUp.draw(renderer.getBatch());
	                }
	                currentPowerUp.update(delta);
	            }
	
	            if (player.isImmune) {
	            	shieldSprite.draw(renderer.getBatch());
	            }
	            
	            if(player.isSanic) {
	            	fastSprite.draw(renderer.getBatch());
	            }
	            
            	shieldSprite.setX(player.getX());
            	shieldSprite.setY(player.getY());
            	fastSprite.setX(player.getX());
            	fastSprite.setY(player.getY());
	            
	            renderer.getBatch().end();
	
	
	            String progressString = ("Wave " + Integer.toString(currentWave) + ", " + Integer.toString(zombiesRemaining) + " zombies remaining.");
	            String healthString = ("Health: " + Integer.toString(player.health) + "HP");
	            String pointsString = ("Points:" + Integer.toString(player.getPoints()) + " points");
	
	            progressLabel.setText(progressString);
	            healthLabel.setText(healthString);
	            pointsLabel.setText(pointsString);
	
	            table.top().left();
	            table.add(progressLabel).pad(10);
	            table.row().pad(10);
	            table.add(healthLabel).pad(10).left();
	            table.row();
	            table.add(pointsLabel).pad(10).left();
	            table.row();
	            table.add(powerupLabel);
	            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
	            stage.draw();
	        } else if (Zepr.progress == Zepr.MINIGAME) { //if its the minigame
	            // Clears the screen to black.
	            Gdx.gl.glClearColor(0f, 0f, 0f, 1);
	            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	            timer += Gdx.graphics.getDeltaTime();
	            table.clear();
	            if(rockSpawn == 4) {
	            	generateRock(); //add a new rock every frame
	            	rockSpawn = 0;
	            }
	            rockSpawn += 1;
	            
	            // Keep the player central in the screen.
	            camera.position.set(player.getCenter().x, player.getCenter().y, 0);
	            camera.update();
	            renderer.setView(camera);
	            renderer.render();
	
	            renderer.getBatch().begin();
	            player.draw(renderer.getBatch());
	            for (int i = 0; i < rockList.length - 1; i++) {
	            	if(rockList[i] != null) {
	            		rockList[i].updateRock();
	            		rockList[i].sprite.draw(renderer.getBatch());
	            		if(rockList[i].rockCollision(player)) {
	            			minigameHealth -= 1; //if a rock is colliding with the player, deduct health
	            		}
	            		if (rockList[i].testRock()) { //remove rocks below the map
	            			rockList[i] = null;
	            		}
	            	}
	            }
	            renderer.getBatch().end();
	            
	            if (timer >= 30) { //after some given time currently 30s
                    complete(); //what does this do? how to increment the level?
                    parent.setScreen(new TextScreen(parent, "Level completed."));
	            }
	            
	            if (minigameHealth <= 0) {
	            	player.currentLevel.gameOver();
	            }
	            
	            String progressString = ("Minigame!!");
	            String healthString = ("Health: " + Integer.toString(minigameHealth) + "HP"); //want players health to count here or not?
	            String pointsString = ("Points:" + Integer.toString(player.getPoints()) + " points");
	
	            progressLabel.setText(progressString);
	            healthLabel.setText(healthString);
	            pointsLabel.setText(pointsString);
	
	            table.top().left();
	            table.add(progressLabel).pad(10);
	            table.row().pad(10);
	            table.add(healthLabel).pad(10).left();
	            table.row();
	            table.add(pointsLabel).pad(10).left();
	            table.row();
	            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
	            stage.draw();
	    	}
    	} 
    }

    private void generateRock() {
    	Random rnd = new Random(); //200 - 600 (bounds of the map)
    	Rock[] temp = rockList;
    	rockList = new Rock[rockList.length + 1]; //adds one to the length of the list
    	for (int i = 0; i < temp.length; i++) { //put the new rock on the end of the list
    		rockList[i] = temp[i];
    	}
    	rockList[rockList.length - 1] = new Rock(new Vector2(rnd.nextInt(400) + 200,550)); //spawn new rock at a random position at the top of the screen
    }
    
    @Override
    public void resize(int width, int height) {
        // Resize the camera depending the size of the window.
        camera.viewportHeight = height;
        camera.viewportWidth = width;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
        map.dispose();
        renderer.dispose();
        if (currentPowerUp != null) {
            currentPowerUp.getTexture().dispose();
        }
        player.getTexture().dispose();
        for (Zombie zombie : aliveZombies) {
            zombie.getTexture().dispose();
        }
    }
}
