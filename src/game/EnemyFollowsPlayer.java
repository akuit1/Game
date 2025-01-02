package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Implements behavior for an enemy to follow a player within the game world.
 * <p>
 * This class implements {@link StepListener} to enable an enemy character to actively follow a player's movements
 * horizontally across the game world. The enemy adjusts its direction and velocity each step based on the relative
 * position of the player, ensuring that it consistently moves toward the player.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class EnemyFollowsPlayer implements StepListener {

    private Enemy enemy;
    private Player player;
    private float speed;

    /**
     * Constructs an instance to manage the following behavior of an enemy towards a player.
     * <p>
     * This constructor initializes the behavior with a specific enemy, player, and movement speed,
     * setting the framework for the enemy's movement towards the player.
     * </p>
     *
     * @param enemy The enemy that will follow the player.
     * @param player The player that the enemy will follow.
     * @param speed The speed at which the enemy will move towards the player.
     */
    public EnemyFollowsPlayer(Enemy enemy, Player player, float speed) {
        this.enemy = enemy;
        this.player = player;
        this.speed = speed;
    }

    /**
     * No actions taken before the physics step.
     * @param e The event details of the pre-step phase.
     */
    @Override
    public void preStep(StepEvent e) {
    }

    /**
     * Adjusts the enemy's position and orientation post physics step based on the player's position.
     * <p>
     * This method calculates the direction towards the player and sets the enemy's velocity accordingly,
     * ensuring the enemy moves horizontally towards the player. The enemy's graphical orientation is also
     * adjusted to face the player, either to the left or to the right.
     * </p>
     *
     * @param e The event details of the post-step phase.
     */
    @Override
    public void postStep(StepEvent e) {
        Vec2 enemyPosition = enemy.getPosition();
        Vec2 playerPosition = player.getPosition();

        float directionX = playerPosition.x - enemyPosition.x;
        directionX = Math.signum(directionX);

        enemy.setLinearVelocity(new Vec2(directionX * speed, 0));

        if (directionX > 0) {
            enemy.flipRight();
        } else {
            enemy.flipLeft();
        }
    }
}
