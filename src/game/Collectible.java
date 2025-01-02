package game;

import city.cs.engine.*;

/**
 * Represents a generic collectible item within the game.
 * <p>
 * This abstract class serves as a base for various types of collectibles in the game. It extends
 * {@link DynamicBody}, allowing it to participate in the physics world with custom properties.
 * Each collectible is associated with an image and can be scaled according to its intended visual size.
 * Collectibles have no gravity effect applied to them by default, making them static in the air or
 * on the ground until interacted with.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public abstract class Collectible extends DynamicBody {
    private BodyImage image;

    /**
     * Constructs a new collectible object within the specified game world.
     * <p>
     * This constructor initializes the collectible with a specific shape and image. The image is scaled
     * to fit the visual representation of the collectible. The collectible is also set to have no effect
     * from gravity.
     * </p>
     *
     * @param world The game world where this collectible exists.
     * @param shape The physical shape of the collectible for collision detection.
     * @param imagePath The path to the image file for the visual representation of the collectible.
     * @param scale The scale factor for resizing the image relative to its original size.
     */
    public Collectible(World world, Shape shape, String imagePath, float scale) {
        super(world, shape);
        this.image = new BodyImage(imagePath, scale);
        addImage(image);
        setGravityScale(0);
    }
}
