package game;

import city.cs.engine.*;

/**
 * Handles collision events involving diamonds in the game.
 * <p>
 * This class implements {@link CollisionListener} to define the behavior when a game entity collides with a diamond.
 * Specifically, if the player collides with a diamond, this class triggers the game-winning sequence and then
 * destroys the diamond, effectively removing it from the game world.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class DiamondCollision implements CollisionListener {
    private Game game;

    /**
     * Creates a DiamondCollision listener associated with the given game context.
     * <p>
     * This constructor stores a reference to the game object which can be used to trigger game-specific methods,
     * such as winning the game, in response to collision events.
     * </p>
     *
     * @param game The game instance in which the collision events are to be handled.
     */
    public DiamondCollision(Game game) {
        this.game = game;
    }

    /**
     * Handles the collision event when an entity collides with a diamond.
     * <p>
     * This method checks if the colliding body is a diamond. If it is, it calls the game's win method
     * and then destroys the diamond to signify the game-winning condition.
     * </p>
     *
     * @param e The collision event details.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Diamond) {
            game.GameWon();  // Assumes GameWon is a method that handles winning the game.
            e.getOtherBody().destroy();
        }
    }
}
