package game;
import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Represents an armour collectible in the game.
 * <p>
 * This class extends {@link Collectible} and includes functionalities specific to the armour,
 * such as playing a sound when the armour is destroyed. It is visually represented by an image
 * and has a circular shape in the game's physics world.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */

public class Armour extends Collectible{
    private static SoundClip armour;
    /**
     * Constructor for creating an instance of Armour.
     *
     * <p>
     * This constructor initializes the armour collectible with a circle shape and an associated image.
     * It also loads a sound clip that plays when the armour is destroyed.
     *</p>
     *
     * @param world The game world to which this armour belongs.
     */
    public Armour(World world) {
        super(world, new CircleShape(1.3f), "data/Armour.png", 6f);
    }

    // Static initializer for loading the armour sound.
    static {
        try {
            armour = new SoundClip("data/Armour.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Destroys the armour and plays a sound.
     *
     * <p>
     * This method overrides the destroy method of the superclass. It plays a sound before
     * proceeding with the superclass' destroy functionality.
     * </p>
     *
     */
    @Override
    public void destroy()
    {
        armour.play();
        super.destroy();
    }

}
