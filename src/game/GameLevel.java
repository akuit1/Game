package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * An abstract base class representing a game level in the platform game.
 * <p>
 * This class provides a common structure for all game levels, including initializing the game world,
 * creating the player, and setting up the environment like ground and walls. It also establishes basic
 * collision listeners for the player to interact with different types of enemies. Each specific level
 * derived from this class will implement its own complete condition to progress through the game.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public abstract class GameLevel extends World {
    private Player player;
    private StaticBody ground, wall1, wall2;

    /**
     * Constructs a GameLevel instance which sets up the physical environment and player.
     *
     * @param game Reference to the main game control object, used to manage game states and interactions.
     */
    public GameLevel(Game game) {
        super(60);

        // Create ground
        Shape shape = new BoxShape(40, 0.5f);
        ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -11.5f));

        // Initialize the player
        player = new Player(this);
        this.addStepListener(new PlayerController(player, 10, 19));

        // Setup player-enemy collisions
        player.addCollisionListener(new EnemyCollision(player));
        player.addCollisionListener(new FlyingEnemyCollision(player));

        // Create boundary walls
        Shape Wall = new BoxShape(0.5f, 15);
        wall1 = new StaticBody(this, Wall);
        wall1.setPosition(new Vec2(-30, 4));
        wall2 = new StaticBody(this, Wall);
        wall2.setPosition(new Vec2(30, 4));
    }

    /**
     * Provides the player object of the current level for external use, such as controlling or querying player state.
     *
     * @return The player object of this level.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Abstract method to determine if the level completion conditions are met.
     *
     * @return true if the level is complete, false otherwise.
     */
    public abstract boolean isComplete();

    /**
     * Destroys all major objects in the level, including ground, walls, and player.
     * This method is called when transitioning levels or restarting.
     */
    public void destroyLevelallObjects() {
        if (ground != null) ground.destroy();
        if (player != null) player.destroy2();
        if (wall1 != null) wall1.destroy();
        if (wall2 != null) wall2.destroy();
    }
}
