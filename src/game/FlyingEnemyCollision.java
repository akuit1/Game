package game;

import city.cs.engine.*;

/**
 * Handles collision events between a player and flying enemies in the game.
 * <p>
 * This class implements {@link CollisionListener} to define the behavior when a player collides with a flying enemy.
 * It checks the player's armour status during the collision; if the player has armour, it decreases the armour,
 * otherwise, it directly affects the player's health. This class helps manage the game's dynamics by adjusting the
 * player's stats based on these encounters, providing a layer of complexity in the game's survival mechanics.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class FlyingEnemyCollision implements CollisionListener {
    private Player player;

    /**
     * Constructs a FlyingEnemyCollision listener associated with a specific player.
     * <p>
     * This constructor sets up a collision handler focused on interactions between the player and flying enemies,
     * with access to the player's current status to appropriately adjust their health or armour in response to
     * collision events.
     * </p>
     *
     * @param player The player involved in collisions with flying enemies.
     */
    public FlyingEnemyCollision(Player player) {
        this.player = player;
    }

    /**
     * Responds to collisions involving flying enemies.
     * <p>
     * Upon a collision with a flying enemy, this method checks if the player has any armour. If the player
     * has armour, it reduces the armour by one. If the player does not have any armour, it reduces the player's
     * health instead. This method is crucial for implementing the defensive game mechanics involving armour and
     * health management.
     * </p>
     *
     * @param e The event details of the collision.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof FlyingEnemy) {
            if (player.getArmour() == 1) {
                player.decreaseArmour();
            } else {
                player.decreaseHealth();
            }
        }
    }
}
