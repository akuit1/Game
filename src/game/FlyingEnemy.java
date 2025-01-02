package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Represents a flying enemy in the game that actively follows the player.
 * <p>
 * This class extends {@link Walker}, although it simulates flying behavior through zero gravity and
 * direct manipulation of linear velocities towards the player. It includes features such as health management,
 * sound effects for death, and methods to follow the player dynamically based on their position.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class FlyingEnemy extends Walker {

    private static final Shape enemyShape = new CircleShape(1f);
    private static final BodyImage image = new BodyImage("data/FlyingEnemy.gif", 10f);

    private Player player;
    private float speed;
    private int health;
    private static int enemyCount = 0;
    private static SoundClip FlyingEnemyDeath;

    /**
     * Constructs a FlyingEnemy with specified attributes and adds it to the game world.
     * <p>
     * This constructor initializes the flying enemy with a circular shape, sets the image, zero gravity scaling,
     * and initial health. It sets up the enemy to actively pursue the player based on the provided speed.
     * </p>
     *
     * @param world The game world where this flying enemy exists.
     * @param player The player that the flying enemy will target and follow.
     * @param speed The speed at which the flying enemy moves towards the player.
     */
    public FlyingEnemy(World world, Player player, float speed) {
        super(world, enemyShape);
        this.player = player;
        this.speed = speed;
        addImage(image);
        setGravityScale(0);
        health = 10;
    }

    /**
     * Directs the flying enemy to follow the player using a direct approach path.
     * <p>
     * This method calculates the vector from the flying enemy to the player and sets the
     * enemy's velocity to move towards the player at the defined speed, allowing for dynamic
     * following and engagement.
     * </p>
     */
    public void followPlayer() {
        Vec2 playerPosition = player.getPosition();
        Vec2 enemyPosition = getPosition();
        float deltaY = playerPosition.y - enemyPosition.y;
        float deltaX = playerPosition.x - enemyPosition.x;

        float angle = (float) Math.atan2(deltaY, deltaX);
        float vx = speed * (float) Math.cos(angle);
        float vy = speed * (float) Math.sin(angle);

        setLinearVelocity(new Vec2(vx, vy));
    }

    /**
     * Updates the enemy's state, such as moving towards the player.
     */
    public void update() {
        followPlayer();
    }

    /**
     * Reduces the flying enemy's health by one.
     */
    public void decreaseHealth() {
        health--;
    }

    /**
     * Checks if the flying enemy is still alive based on its health.
     *
     * @return true if the enemy has more than zero health, false otherwise.
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Sets the initial count of flying enemies for game tracking purposes.
     *
     * @param count Total number of flying enemies at the start of the level.
     */
    public static void setEnemyCount(int count) {
        enemyCount = count;
    }

    /**
     * Decreases the count of active flying enemies and checks if any are left.
     */
    public static void decreaseEnemyCount() {
        enemyCount--;
        if (enemyCount <= 0) {
            GameState.setlevelWon(true);
        }
    }

    /**
     * Retrieves the current count of active flying enemies.
     *
     * @return The number of flying enemies remaining.
     */
    public static int getEnemyCount() {
        return enemyCount;
    }

    // Static initializer to load the sound effect for the flying enemy's death.
    static {
        try {
            FlyingEnemyDeath = new SoundClip("data/FlyingEnemyDeath.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Destroys the flying enemy, playing a sound effect upon death.
     */
    @Override
    public void destroy() {
        FlyingEnemyDeath.play();
        super.destroy();
    }
}
