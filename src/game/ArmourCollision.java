package game;

import city.cs.engine.*;

/**
 * Handles collision events between a player and an armour collectible in the game.
 * <p>
 * This class is designed to manage interactions when a player comes into contact with an armour item.
 * If the player's armour count is less than one, it increases the player's armour count and destroys
 * the collectible. This class extends {@link CollectibleCollision} and uses its constructor to initiate.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class ArmourCollision extends CollectibleCollision {

    /**
     * Constructs an ArmourCollision object to handle specific collision events.
     *
     * <p>
     * This constructor initializes the collision handling between a specific player and armour item,
     * utilizing the parent class constructor to set up the necessary properties.
     * </p>
     *
     * @param player The player involved in the collision.
     * @param armour The armour collectible involved in the collision.
     */
    public ArmourCollision(Player player, Armour armour) {
        super(player, armour);
    }

    /**
     * Defines what happens when a collision occurs between a player and an armour.
     *
     * <p>
     * On collision, this method checks if the colliding body is indeed the collectible armour. If the
     * player's armour count is less than one, it increases by one and the collectible is destroyed,
     * removing it from the game.
     * </p>
     *
     * @param e The event details of the collision.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == collectible) {
            if (player.getArmour() < 1) {
                player.increaseArmour();
                collectible.destroy();
            }
        }
    }
}
