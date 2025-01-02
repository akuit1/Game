package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Represents a player in the game with various states and behaviors such as moving left and right,
 * idle states, and attacking mechanisms. The player can also manage health and armor states,
 * responding to game events like damage or recovery. The class interacts with game physics and
 * audio systems to provide visual and auditory feedback based on player actions.
 *
 * <p>
 * Usage includes creating a player instance, updating its state through game events, and
 * rendering the player with appropriate game assets based on the player's actions and state.
 * </p>
 *
 * @author Idrees Nasar-ullah, Idrees.nasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class Player extends Walker {
    private static final Shape playerShape =
            new BoxShape(1, 2);
    private static final BodyImage imageright =
            new BodyImage("data/PlayerRunningRight.gif", 4f);
    private static final BodyImage imageLeft =
            new BodyImage("data/PlayerRunningLeft.gif", 4f);

    private static final BodyImage idleright =
            new BodyImage("data/Player.gif", 4f);

    private static final BodyImage idleleft =
            new BodyImage("data/PlayerLeft.gif", 4f);

    private static final BodyImage gunright =
            new BodyImage("data/PlayerwithGun.gif", 4f);
    private static final BodyImage gunleft =
            new BodyImage("data/PlayerwithGunLeft.gif", 4f);
    private static int health, armour;
    private static SoundClip playerdeath, gameover;

    /**
     * Constructs a Player object with initial settings.
     *
     * @param world The game world in which the player resides.
     */
    public Player(World world) {
        super(world, playerShape);
        addImage(idleright);
        world.setGravity(25);
        health = 3;
        armour = 0;
    }

    /**
     * Flips the player's image to the right.
     */
    public void flipImageright() {
        removeAllImages();
        addImage(imageright);
    }

    /**
     * Flips the player's image to the left.
     */
    public void flipImageleft() {
        removeAllImages();
        addImage(imageLeft);
    }

    /**
     * Sets the player to idle facing right.
     */
    public void idleright() {
        removeAllImages();
        addImage(idleright);
    }

    /**
     * Sets the player to idle facing left.
     */
    public void idleleft() {
        removeAllImages();
        addImage(idleleft);
    }

    /**
     * Sets the player to a gun-holding position facing left.
     */
    public void gunleft() {
        removeAllImages();
        addImage(gunleft);
    }

    /**
     * Sets the player to a gun-holding position facing right.
     */
    public void gunright() {
        removeAllImages();
        addImage(gunright);
    }

    /**
     * Increases the player's health by one point, not exceeding the maximum.
     */
    public void increaseHealth() {
        health++;
        if (health >= 3) {
            health = 3;
        }
    }

    /**
     * Decreases the player's health by one point. Triggers game over if health falls to zero.
     */
    public void decreaseHealth() {
        health--;
        if (health <= 0) {
            destroy();
            GameState.setGameOver(true);
        }
    }

    /**
     * Returns the current health of the player.
     *
     * @return The current health value.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the player's health to the specified value.
     *
     * @param health The new health value.
     */
    public static void setHealth(int health) {
        Player.health = health;
    }

    /**
     * Returns the current armour of the player.
     *
     * @return The current armour value.
     */
    public int getArmour() {
        return armour;
    }

    /**
     * Increases the player's armour by one point, not exceeding the maximum.
     */
    public void increaseArmour() {
        armour++;
        if (armour >= 1) {
            armour = 1;
        }
    }

    /**
     * Decreases the player's armour by one point.
     */
    public void decreaseArmour() {
        armour--;
    }

    /**
     * Sets the player's armour to the specified value.
     *
     * @param armour The new armour value.
     */
    public static void setArmour(int armour) {
        Player.armour = armour;
    }

    // Static initializer blocks to load sound clips.
    static {
        try {
            playerdeath = new SoundClip("data/PlayerDeath.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    static {
        try {
            gameover = new SoundClip("data/Gameover.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Plays sound effects and destroys the player object when health reaches zero.
     */
    @Override
    public void destroy()
    {
        playerdeath.play();
        gameover.play();
        super.destroy();
    }

    public void destroy2()
    {
        super.destroy();
    }
}
