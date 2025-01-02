package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Implements a patrolling behavior for an enemy within the game world.
 * <p>
 * This class implements {@link StepListener} to control an enemy's movement between two set points
 * (left and right boundaries). The enemy will automatically reverse direction upon reaching either
 * boundary and adjust its facing direction accordingly. This behavior is typical for platformer games
 * where enemies patrol a fixed area.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class EnemyPatrolling implements StepListener {

    private Enemy enemy;
    private float speed;
    private float rightBoundary;
    private float leftBoundary;
    private boolean right;

    /**
     * Constructs an EnemyPatrolling behavior with defined boundaries and movement speed.
     * <p>
     * This constructor initializes the patrolling behavior with an enemy, movement speed, and boundary limits.
     * The enemy will move at the specified speed between the two boundaries, changing direction as necessary.
     * </p>
     *
     * @param enemy The enemy that will be patrolling.
     * @param speed The horizontal speed of the enemy's movement.
     * @param rightBoundary The maximum rightward position the enemy can move to before turning back.
     * @param leftBoundary The maximum leftward position the enemy can move to before turning back.
     */
    public EnemyPatrolling(Enemy enemy, float speed, float rightBoundary, float leftBoundary) {
        this.enemy = enemy;
        this.speed = speed;
        this.rightBoundary = rightBoundary;
        this.leftBoundary = leftBoundary;
        this.right = true; // Initialize moving right by default
    }

    /**
     * No action taken before the physics step.
     * @param e The event details of the pre-step phase.
     */
    @Override
    public void preStep(StepEvent e) {
    }

    /**
     * Manages the enemy's horizontal movement and direction changes after each physics step.
     * <p>
     * This method updates the enemy's position based on its speed and the defined boundaries.
     * If the enemy reaches either boundary, it reverses direction and its speed. The enemy's
     * graphical orientation is also updated to face the direction of movement.
     * </p>
     *
     * @param e The event details of the post-step phase.
     */
    @Override
    public void postStep(StepEvent e) {
        enemy.move(new Vec2(speed, 0));

        // Check if the enemy has hit a boundary on the right
        if (enemy.getPosition().x > rightBoundary) {
            speed = -Math.abs(speed);
            right = false;
        }

        // Check if the enemy has hit a boundary on the left
        if (enemy.getPosition().x < leftBoundary) {
            speed = Math.abs(speed);
            right = true;
        }

        // Update enemy's orientation based on the direction
        if (right) {
            enemy.flipRight();
        } else {
            enemy.flipLeft();
        }
    }
}
