package game;

import city.cs.engine.*;

/**
 * Handles collision events between a player and enemies in the game.
 * <p>
 * This class implements {@link CollisionListener} to define the behavior when a player collides with an enemy.
 * Depending on the player's armour status, the collision either decreases the player's armour or health.
 * This ensures that armour provides a protective mechanism before health is impacted, reflecting typical
 * gameplay mechanics where armour shields players from immediate health loss.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class EnemyCollision implements CollisionListener {
    private Player player;

    /**
     * Constructs an EnemyCollision listener associated with a specific player.
     * <p>
     * This constructor initializes the collision handler with a reference to the player,
     * allowing it to modify the player's health or armour during collision events with enemies.
     * </p>
     *
     * @param player The player involved in collisions with enemies.
     */
    public EnemyCollision(Player player) {
        this.player = player;
    }

    /**
     * Responds to collision events where the colliding body is an enemy.
     * <p>
     * This method checks if the colliding body is an enemy. If it is, it then checks the player's armour status.
     * If the player has armour (i.e., armour value is 1), the armour is decreased. If no armour is present,
     * the player's health is decreased. This mechanism prioritizes armour depletion before affecting health,
     * adding a layer of strategy and protection for the player.
     * </p>
     *
     * @param e The event details of the collision.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Enemy) {
            if (player.getArmour() == 1) {
                player.decreaseArmour();
            } else {
                player.decreaseHealth();
            }
        }
    }
}
