package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Represents an enemy character in the game, specifically a zombie.
 * <p>
 * This class extends {@link Walker}, providing movement capabilities suited for an enemy in a platform game.
 * It includes health management, images for different orientations, and sound effects for specific actions like death.
 * The enemy has an initial health value, which when depleted to zero, triggers a sound effect and potentially
 * influences the game state by checking if all enemies are defeated.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class Enemy extends Walker {

    private static final Shape enemyShape = new BoxShape(1, 2);

    private static final BodyImage Right =
            new BodyImage("data/ZombieRight.gif", 4f);

    private static final BodyImage Left =
            new BodyImage("data/ZombieLeft.gif", 4f);

    private int health;
    private static int enemyCount = 0;
    private static SoundClip enemyDeath;

    /**
     * Constructs an enemy with specific attributes and adds it to the game world.
     * <p>
     * This constructor initializes the enemy with a specific shape, sets an initial image,
     * applies a gravity scale to simulate more realistic movements, and sets initial health.
     * </p>
     *
     * @param world The game world where this enemy exists.
     */
    public Enemy(World world) {
        super(world, enemyShape);
        addImage(Right);
        setGravityScale(2);
        health = 3;
    }

    /**
     * Switches the enemy's image to the right-facing orientation.
     */
    public void flipRight() {
        removeAllImages();
        addImage(Right);
    }

    /**
     * Switches the enemy's image to the left-facing orientation.
     */
    public void flipLeft() {
        removeAllImages();
        addImage(Left);
    }

    /**
     * Decreases the health of the enemy by one.
     */
    public void decreaseHealth() {
        health--;
    }

    /**
     * Checks if the enemy is still alive based on its health.
     *
     * @return true if health is greater than zero, false otherwise.
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Sets the global enemy count, typically used at the start of the level.
     *
     * @param count The total number of enemies at the start of the level.
     */
    public static void setEnemyCount(int count) {
        enemyCount = count;
    }

    /**
     * Decreases the global enemy count and checks if the level should be marked as won.
     */
    public static void decreaseEnemyCount() {
        enemyCount--;
        if (enemyCount <= 0) {
            GameState.setlevelWon(true);
        }
    }

    /**
     * Retrieves the current count of enemies in the game.
     *
     * @return The number of active enemies.
     */
    public static int getEnemyCount() {
        return enemyCount;
    }

    // Static initializer to load the death sound effect for the enemy.
    static {
        try {
            enemyDeath = new SoundClip("data/ZombieDeath.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Plays a death sound effect and destroys the enemy object when called.
     */
    @Override
    public void destroy() {
        enemyDeath.play();
        super.destroy();
    }
}
