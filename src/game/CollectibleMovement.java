package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Manages the vertical movement of a collectible within specified boundaries in the game world.
 * <p>
 * This class implements {@link StepListener} to update the position of a collectible object at each step of the simulation.
 * It allows the collectible to move vertically between an upper and a lower boundary, reversing direction when a boundary
 * is reached. This behavior can simulate floating or bouncing movements.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class CollectibleMovement implements StepListener {
    private Collectible collectible;
    private float speed;
    private float upperBoundary;
    private float lowerBoundary;

    /**
     * Constructs a new CollectibleMovement to manage the vertical motion of a collectible.
     * <p>
     * This constructor initializes the collectible movement with specified speed and boundary limits.
     * The speed defines the rate of vertical movement and the boundaries define the maximum and minimum
     * vertical extents of movement.
     * </p>
     *
     * @param collectible The collectible whose movement is to be managed.
     * @param speed The vertical speed of the collectible's movement (positive for upwards, negative for downwards).
     * @param upperBoundary The upper vertical boundary which the collectible should not exceed.
     * @param lowerBoundary The lower vertical boundary which the collectible should not fall below.
     */
    public CollectibleMovement(Collectible collectible, float speed, float upperBoundary, float lowerBoundary) {
        this.collectible = collectible;
        this.speed = speed;
        this.upperBoundary = upperBoundary;
        this.lowerBoundary = lowerBoundary;
    }

    /**
     * Invoked before each physics step, with no movement logic implemented in this method.
     * @param e The event object containing details about the current simulation step.
     */
    @Override
    public void preStep(StepEvent e) {
    }

    /**
     * Updates the collectible's position after each physics step.
     * <p>
     * This method adjusts the vertical position of the collectible based on its current speed.
     * If the collectible reaches the upper or lower boundary, the direction of movement is reversed to
     * keep the collectible within the vertical limits.
     * </p>
     *
     * @param e The event object containing details about the current simulation step.
     */
    @Override
    public void postStep(StepEvent e) {
        collectible.setPosition(new Vec2(collectible.getPosition().x, collectible.getPosition().y + speed));

        if (collectible.getPosition().y >= upperBoundary) {
            speed = -Math.abs(speed);
        } else if (collectible.getPosition().y <= lowerBoundary) {
            speed = Math.abs(speed);
        }
    }
}
