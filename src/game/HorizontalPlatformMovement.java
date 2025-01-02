package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Handles horizontal movement of a platform within specified boundaries.
 * <p>
 * This class implements {@link StepListener} to move a platform back and forth between two horizontal boundaries.
 * The movement is controlled by adjusting the platform's position on each simulation step based on a specified speed.
 * When the platform reaches one of the boundaries, its direction of movement is reversed.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class HorizontalPlatformMovement implements StepListener {
    private StaticBody platform;
    private float speed;
    private float rightBoundary;
    private float leftBoundary;

    /**
     * Constructs a new HorizontalPlatformMovement with specified parameters.
     * <p>
     * Initializes the movement control for a platform with defined speed and boundaries.
     * The boundaries are adjusted based on the initial position of the platform to maintain consistent limits
     * relative to its starting point.
     * </p>
     *
     * @param platform The platform whose movement is to be controlled.
     * @param speed The speed at which the platform moves. Positive values move the platform to the right; negative values move it to the left.
     * @param rightBoundary The rightmost boundary the platform should move to before reversing direction.
     * @param leftBoundary The leftmost boundary the platform should move to before reversing direction.
     */
    public HorizontalPlatformMovement(StaticBody platform, float speed, float rightBoundary, float leftBoundary) {
        this.platform = platform;
        this.speed = speed;
        this.rightBoundary = rightBoundary + platform.getPosition().x;
        this.leftBoundary = leftBoundary + platform.getPosition().x;
    }

    /**
     * Performs no action before the physics step.
     *
     * @param e The event details of the pre-step phase.
     */
    @Override
    public void preStep(StepEvent e) {}

    /**
     * Updates the platform's position after each physics step.
     * <p>
     * This method calculates the new position of the platform based on its current speed.
     * If the platform reaches either boundary, the direction of the speed is reversed to change the movement direction,
     * and the position is recalculated accordingly.
     * </p>
     *
     * @param e The event details of the post-step phase.
     */
    @Override
    public void postStep(StepEvent e) {
        Vec2 position = platform.getPosition();
        float newX = position.x + speed;
        if (newX >= rightBoundary || newX <= leftBoundary) {
            speed = -speed; // Reverse direction
            newX = position.x + speed; // Recalculate new position with reversed speed
        }
        platform.setPosition(new Vec2(newX, position.y));
    }
}
