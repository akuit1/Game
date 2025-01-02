package game;

import city.cs.engine.*;

/**
 * Represents a diamond collectible that functions as a doorway in the game.
 * <p>
 * This class extends the {@link Doorway} class, configuring it with specific properties suitable for a diamond.
 * The diamond is represented as a circle in the game's physics environment and is associated with a visual image.
 * It also has a larger scale factor, indicating its significance or visual prominence in the game.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class Diamond extends Doorway {

    /**
     * Constructs a new Diamond object within the specified game world.
     * <p>
     * This constructor initializes the diamond with a circular shape and associates it with an image.
     * The diamond's physical and visual properties are set here, including its size and the image scale.
     * </p>
     *
     * @param world The game world where this diamond will exist.
     */
    public Diamond(World world) {
        super(world, new CircleShape(3f), "data/Diamond.gif", 15f);
    }
}
