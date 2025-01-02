package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Represents a collectible gun in the game that players can pick up.
 * <p>
 * This class extends {@link Collectible} and is used to create a gun object within the game world.
 * It is visually represented by an image and includes a sound clip that plays when the gun is destroyed
 * or interacted with. The gun's shape is circular, and it has a specific size defined by its scale factor.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class Gun extends Collectible {
    public static SoundClip gun;

    /**
     * Constructs a new Gun object within the specified game world.
     * <p>
     * This constructor initializes the gun with a circular shape and sets its visual and auditory
     * properties. The sound clip is loaded and will be played when the gun is destroyed.
     * </p>
     *
     * @param world The game world where this gun will exist.
     */
    public Gun(World world) {
        super(world, new CircleShape(1.5f), "data/gun.png", 2f);
    }

    // Static initializer block to load the gun sound effect.
    static {
        try {
            gun = new SoundClip("data/Gun.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Plays a sound effect and destroys the gun when this method is called.
     * <p>
     * Overrides the destroy method of the superclass to include playing a sound effect when the gun is
     * destroyed. This enhances the interaction feedback for the player.
     * </p>
     */
    @Override
    public void destroy() {
        gun.play();
        super.destroy();
    }
}
