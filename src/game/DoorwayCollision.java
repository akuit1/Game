package game;

import city.cs.engine.*;

/**
 * Handles collision events with doorways to trigger level transitions in the game.
 * <p>
 * This class implements {@link CollisionListener} to respond to collisions between a player and a doorway object.
 * It checks if the current level is complete and if so, triggers the transition to the next level in the game.
 * This mechanism is vital for progressing through different stages or levels in the game environment.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class DoorwayCollision implements CollisionListener {
    private GameLevel level;
    private Game game;

    /**
     * Constructs a DoorwayCollision listener associated with specific game level and game context.
     * <p>
     * This constructor initializes the collision handler with references to the current game level and
     * the main game object, which allows it to check level completion status and control game flow,
     * such as moving to the next level.
     * </p>
     *
     * @param level The current level of the game where the doorway exists.
     * @param game The main game object that controls the game states and levels.
     */
    public DoorwayCollision(GameLevel level, Game game) {
        this.level = level;
        this.game = game;
    }

    /**
     * Responds to collisions involving doorways when a level is complete.
     * <p>
     * Upon a collision with a doorway, this method checks if the current level is complete. If the level is complete,
     * it initiates a transition to the next level by calling the game's method to move forward and destroys the doorway,
     * preventing re-entry or repeated triggers.
     * </p>
     *
     * @param e The event details of the collision.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Doorway && level.isComplete()) {
            game.goToNextLevel();
            e.getOtherBody().destroy();
        }
    }
}
