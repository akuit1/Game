package game;

import city.cs.engine.*;

/**
 * Manages collision events for a key collectible in the game.
 * <p>
 * This class implements {@link CollisionListener} to handle specific interactions between a player and a key.
 * When a player collides with a key, this class ensures that the game state is updated to reflect the level
 * completion (assuming the key is a level-completing item) and the key is destroyed, typically indicating that
 * the player has picked it up.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class KeyCollision implements CollisionListener {
    private Player player;
    private Key key;

    /**
     * Constructs a KeyCollision object to manage specific collision events between a player and a key.
     * <p>
     * This constructor initializes the collision handler with the player and the key involved in the collision.
     * It ensures that when these two objects collide, the key's effect (completing a level) is applied correctly.
     * </p>
     *
     * @param player The player involved in the collision.
     * @param key The key collectible involved in the collision.
     */
    public KeyCollision(Player player, Key key) {
        this.player = player;
        this.key = key;
    }

    /**
     * Defines the specific actions to take when a collision with a key occurs.
     * <p>
     * This method checks if the colliding body is indeed the key collectible. If it is, it sets the level
     * completion state to true and then destroys the key, effectively simulating the player picking up
     * the key and completing the required task for the level.
     * </p>
     *
     * @param e The event details of the collision.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Key) {
            GameState.setlevelWon(true);
            e.getOtherBody().destroy();
        }
    }
}
