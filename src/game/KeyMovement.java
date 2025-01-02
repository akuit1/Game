package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Controls the vertical floating movement of a key within designated upper and lower boundaries.
 * <p>
 * This class implements {@link StepListener} to adjust the position of a key dynamically each simulation step,
 * simulating a floating effect. The movement direction reverses upon reaching either the upper or lower boundary,
 * creating a continuous oscillation between the two limits.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class KeyMovement implements StepListener {

    private Key key;
    private float speed;
    private float upperBoundary;
    private float lowerBoundary;

    /**
     * Constructs a KeyMovement controller for a key with specified vertical movement parameters.
     * <p>
     * Initializes the movement behavior with a given key, speed, and vertical boundaries. The key will
     * move vertically at the specified speed between the defined upper and lower limits.
     * </p>
     *
     * @param key The key whose movement is to be controlled.
     * @param speed The vertical speed at which the key moves. Positive values move the key upwards, negative values move it downwards.
     * @param upperBoundary The maximum vertical position (y-value) the key can reach before reversing direction.
     * @param lowerBoundary The minimum vertical position (y-value) the key can reach before reversing direction.
     */
    public KeyMovement(Key key, float speed, float upperBoundary, float lowerBoundary) {
        this.key = key;
        this.speed = speed;
        this.upperBoundary = upperBoundary;
        this.lowerBoundary = lowerBoundary;
    }

    /**
     * No action is taken before the physics step.
     * @param stepEvent Details of the pre-step phase of the simulation step.
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        // This method is intentionally left blank.
    }

    /**
     * Updates the key's vertical position post physics step, reversing its direction at boundaries.
     * <p>
     * This method adjusts the key's position based on its speed and checks for boundary collisions.
     * If a boundary is hit, the speed's sign is reversed to change the movement direction, ensuring
     * the key stays within the specified vertical limits.
     * </p>
     *
     * @param stepEvent Details of the post-step phase of the simulation step.
     */
    @Override
    public void postStep(StepEvent stepEvent) {
        key.move(new Vec2(0, speed));

        // Reverse direction at boundaries
        if (key.getPosition().y > upperBoundary) {
            speed = -Math.abs(speed); // Move downward
        } else if (key.getPosition().y < lowerBoundary) {
            speed = Math.abs(speed); // Move upward
        }
    }
}
