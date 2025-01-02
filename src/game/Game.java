package game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.*;

import city.cs.engine.*;

import java.awt.*;
import java.io.IOException;

/**
 * The main game setup class that initializes and controls game flow, UI components, and sound.
 * <p>
 * This class manages the entire game by setting up levels, handling transitions between them, and managing game states.
 * It creates the game environment, initializes UI components like controls and instructions, and handles the player's
 * interactions and movements. Additionally, it controls the background music and sound effects based on game events.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */

public class Game {
    private GameLevel currentLevel;
    private Player player;
    private PlayerController playerController;
    private GameView view;
    private Integer currentArmour, currentHealth;
    private SoundClip gametheme1, gametheme2, gametheme3, gamewon;
    private JFrame frame;
    private JList<String> controlsList, instructionsList;

    /**
     * Constructs the main game setup, initializes the game environment, levels, player, and UI components.
     */
    public Game() {
        // Create the game world
        currentLevel = new Level1(this);

        player = currentLevel.getPlayer();

        playerController = new PlayerController(player, 10, 19);

        // Create a view to look into the game world
        view = new GameView(currentLevel, player, 1200, 800);

        // Create a JFrame and add the game view to it
        frame = new JFrame("City Game");
        frame.add(view);
        frame.addKeyListener(playerController);
        frame.addMouseListener(playerController);

        // Set up the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        // Initialize UI components
        initializeUI();

        // Start background music
        //try {
        //    gametheme1 = new SoundClip("data/GameTheme1.wav");
        //    gametheme1.setVolume(0.3);
        //    gametheme1.loop();
        //} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        //    System.out.println(e);
        //}

        //try {
        //    gametheme2 = new SoundClip("data/GameTheme2.wav");
        //    gametheme2.setVolume(1);
        //} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        //    System.out.println(e);
        //}

        //try {
        //    gametheme3 = new SoundClip("data/GameTheme3.wav");
        //    gametheme3.setVolume(0.3);
        //} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        //    System.out.println(e);
        //}

        // Start the game world simulation
        currentLevel.start();
    }

    /**
     * Transitions the game to the next level based on the current level instance.
     */
    public void goToNextLevel(){
        if (currentLevel instanceof Level1){
            ((Level1) currentLevel).destroyLevelObjects();
            currentLevel.stop();

            //gametheme1.stop();
            //gametheme2.loop();

            currentArmour = player.getArmour();
            currentHealth = player.getHealth();
            GameState.setlevelWon(false);

            currentLevel = new Level2(this);

            Image background2 = new ImageIcon("data/factory1.jpg").getImage();
            view.updateBackground(background2);

            Player newplayer = currentLevel.getPlayer();

            view.setWorld(currentLevel);
            playerController.updatePlayer(currentLevel.getPlayer());

            Player.setHealth(currentHealth);
            Player.setArmour(currentArmour);
            player.gunright();
            currentLevel.start();
        }
        else if (currentLevel instanceof Level2){
            ((Level2) currentLevel).destroyLevelObjects();
            currentLevel.stop();

            //gametheme2.stop();
            //gametheme3.loop();

            currentArmour = player.getArmour();
            currentHealth = player.getHealth();

            currentLevel = new Level3(this);
            GameState.setlevelWon(false);

            Image background3 = new ImageIcon("data/factory2.jpeg").getImage();
            view.updateBackground(background3);

            Player newplayer = currentLevel.getPlayer();

            view.setWorld(currentLevel);
            playerController.updatePlayer(currentLevel.getPlayer());

            Player.setHealth(currentHealth);
            Player.setArmour(currentArmour);
            player.gunright();
            currentLevel.start();
        }
    }

    /**
     * Handles the game winning scenario, stopping the current game level, playing winning sounds, and updating the background.
     */
    public void GameWon(){
        ((Level3) currentLevel).destroyLevelObjects();
        ((GameLevel) currentLevel).destroyLevelallObjects();

        //gametheme3.stop();

        try {
            gamewon = new SoundClip("data/Gamewon.wav");
            gamewon.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        Image background4 = new ImageIcon("data/black.jpg").getImage();
        view.updateBackground(background4);

        currentLevel.stop();
        GameState.setGameWon(true);
    }

    /**
     * Initializes the UI components used within the game's main frame.
     */
    private void initializeUI() {
        // Data for the controls list
        String[] controls = {
                "W - Jump",
                "A - Move Left",
                "D - Move Right",
                "Mouse Click - Shoot"
        };

        // Data for the instructions list
        String[] instructions = {
                "Level 1: Eliminate all enemies and enter the portal.",
                "Level 2: Avoid enemies or eliminate them and find the key to unlock the door.",
                "Level 3: Defeat the boss and get the key to open door and steal the diamond to win the game."
        };

        // Creating the controls list
        controlsList = new JList<>(controls);
        controlsList.setBorder(BorderFactory.createTitledBorder("Controls"));

        // Creating the instructions list
        instructionsList = new JList<>(instructions);
        instructionsList.setBorder(BorderFactory.createTitledBorder("Level Instructions"));

        // Adding to frame
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(1, 2));
        listPanel.add(new JScrollPane(controlsList));
        listPanel.add(new JScrollPane(instructionsList));
        listPanel.setPreferredSize(new Dimension(1200, 98));

        frame.add(listPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }


    /**
     * The main entry point for the game application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Game();

    }
}
