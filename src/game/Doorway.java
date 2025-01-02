package game;

import city.cs.engine.*;

/**
 * Serves as an abstract base class for doorway-like structures in the game.
 * <p>
 * This class extends {@link StaticBody}, allowing it to represent non-moving, interactable objects within the game world,
 * such as doors or portals. It is designed to be extended by more specific doorway classes, which might have additional
 * functionality or different visual representations. The doorway is initialized with a specific shape and an image that
 * can be scaled to adjust its size visually.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public abstract class Doorway extends StaticBody {
    private BodyImage image;

    /**
     * Constructs a new Doorway object within the specified game world.
     * <p>
     * This constructor initializes the doorway with a physical shape defined by subclasses and associates it with an image,
     * scaled according to the provided parameter. This setup allows the doorway to have a distinct visual appearance in the game.
     * </p>
     *
     * @param world The game world where this doorway exists.
     * @param shape The physical shape of the doorway for collision detection and physical presence in the game world.
     * @param imagePath The path to the image file for the visual representation of the doorway.
     * @param scale The scale factor for resizing the image relative to its original size, affecting its display size in the game.
     */
    public Doorway(World world, Shape shape, String imagePath, float scale) {
        super(world, shape);
        this.image = new BodyImage(imagePath, scale);
        addImage(image);
    }
}
