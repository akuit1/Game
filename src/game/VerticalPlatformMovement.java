package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Handles the vertical movement of a static platform in a game. This class implements {@link StepListener} to
 * enable continuous updates to the platform's position within specified boundaries. The platform moves vertically
 * between an upper and a lower boundary, reversing direction when either boundary is reached.
 *
 * <p>
 * This class is designed to be used in physics-based games where platforms need to move vertically to create
 * dynamic obstacles or environments.
 * </p>
 *
 * @author Idrees Nasar-ullah, Idrees.nasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class VerticalPlatformMovement implements StepListener {
    private StaticBody platform;
    private float speed;
    private float upperBoundary;
    private float lowerBoundary;

    /**
     * Constructs a new VerticalPlatformMovement with specified parameters.
     *
     * @param platform The static body that represents the platform to be moved.
     * @param speed The speed of vertical movement (positive for up, negative for down).
     * @param upperBoundary The upper boundary of the platform's movement relative to its initial position.
     * @param lowerBoundary The lower boundary of the platform's movement relative to its initial position.
     */
    public VerticalPlatformMovement(StaticBody platform, float speed, float upperBoundary, float lowerBoundary) {
        this.platform = platform;
        this.speed = speed;
        this.upperBoundary = upperBoundary + platform.getPosition().y;
        this.lowerBoundary = lowerBoundary + platform.getPosition().y;
    }

    /**
     * Called before each physics step.
     * This method does not manipulate the platform directly but can be used for setup or checks before the step occurs.
     *
     * @param e The event object representing the current step event, not used in this implementation.
     */
    @Override
    public void preStep(StepEvent e) {}

    /**
     * Called after each physics step.
     * This method updates the position of the platform based on its current position, speed, and defined boundaries.
     * It adjusts the platform's vertical position and reverses its movement direction if it reaches the defined boundaries.
     *
     * @param e The event object representing the current step event.
     */
    @Override
    public void postStep(StepEvent e) {
        Vec2 position = platform.getPosition();
        float newY = position.y + speed;
        if (newY >= upperBoundary || newY <= lowerBoundary) {
            speed = -speed; // Reverse direction
            newY = position.y + speed; // Recalculate new position with reversed speed
        }
        platform.setPosition(new Vec2(position.x, newY));
    }
}
