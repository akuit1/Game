package game;

import city.cs.engine.*;

/**
 * Manages collision events for the gun collectible in the game.
 * <p>
 * This class extends {@link CollectibleCollision} and is specialized to handle interactions between the player and a gun collectible.
 * When a collision with a gun occurs, this class ensures the gun is properly destroyed, typically indicating the player has picked it up.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class GunCollision extends CollectibleCollision {

    /**
     * Constructs a GunCollision object to manage specific collision events between a player and a gun.
     * <p>
     * This constructor initializes the collision handler for interactions involving a gun collectible, using
     * the superclass constructor to set up the necessary properties.
     * </p>
     *
     * @param player The player involved in the collision.
     * @param gun The gun collectible involved in the collision.
     */
    public GunCollision(Player player, Gun gun) {
        super(player, gun);
    }

    /**
     * Defines the specific actions to take when a collision with a gun occurs.
     * <p>
     * If the colliding body is a gun, it triggers the destruction of the gun, typically indicating the player
     * has picked it up. This method overrides the collide method in the superclass to provide specific behavior
     * for gun collisions.
     * </p>
     *
     * @param e The event details of the collision.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Gun) {
            e.getOtherBody().destroy();  // Trigger the gun to be destroyed, representing that the player has collected it.
        }
    }
}
