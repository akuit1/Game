package game;

import city.cs.engine.*;

/**
 * Manages collision events for health potion collectibles in the game.
 * <p>
 * This class extends {@link CollectibleCollision} to handle specific interactions between a player and a health potion.
 * When a player collides with a health potion, this class ensures that the player's health is increased accordingly,
 * and the health potion is then destroyed, simulating the potion being used.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class HealthPotionCollision extends CollectibleCollision {

    /**
     * Constructs a HealthPotionCollision object to manage specific collision events between a player and a health potion.
     * <p>
     * This constructor initializes the collision handler with the player and the health potion involved in the collision.
     * It ensures that when these two objects collide, the health potion's effect (increasing health) is applied correctly.
     * </p>
     *
     * @param player The player involved in the collision.
     * @param healthPotion The health potion collectible involved in the collision.
     */
    public HealthPotionCollision(Player player, HealthPotion healthPotion) {
        super(player, healthPotion);
    }

    /**
     * Defines the specific actions to take when a collision with a health potion occurs.
     * <p>
     * This method checks if the colliding body is indeed the health potion collectible. If it is, it increases
     * the player's health and then destroys the potion, effectively simulating the player using the potion.
     * </p>
     *
     * @param e The event details of the collision.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == collectible) {
            player.increaseHealth();
            collectible.destroy();
        }
    }
}
