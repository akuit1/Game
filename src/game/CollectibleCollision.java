package game;

import city.cs.engine.*;

/**
 * Provides a base for handling collisions between a player and various collectibles in the game.
 * <p>
 * This abstract class implements {@link CollisionListener} to define common functionality for collision
 * events involving collectible items. Specific types of collectibles can extend this class to implement
 * their unique collision behaviors. It stores references to the player and the collectible involved in
 * the collision, which are essential for determining the outcomes of collision events.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public abstract class CollectibleCollision implements CollisionListener {
    protected Player player;
    protected Collectible collectible;

    /**
     * Constructs a new collision handler for interactions between a player and a collectible.
     * <p>
     * This constructor initializes the collision handler with references to the player and the specific
     * collectible involved. These references are used to manage and resolve the collision events according
     * to the game's rules and mechanics.
     * </p>
     *
     * @param player The player involved in the collision.
     * @param collectible The collectible involved in the collision.
     */
    public CollectibleCollision(Player player, Collectible collectible) {
        this.player = player;
        this.collectible = collectible;
    }
}
