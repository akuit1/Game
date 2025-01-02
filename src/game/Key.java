package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Represents a key in the game world that players can collect.
 * <p>
 * This class creates a key as a dynamic body with no gravitational effects, allowing it to float or be placed
 * without falling due to gravity. It includes a visual representation and plays a sound effect when the key is
 * collected and destroyed. The shape and image of the key are set to specific dimensions and visual styles.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class Key extends DynamicBody {

    private static final Shape keyShape = new BoxShape(3, 0.1f);
    private static final BodyImage keyImage =
            new BodyImage("data/key.png", 6f);
    private static SoundClip keySound;

    /**
     * Constructs a key within the specified game world.
     * <p>
     * This constructor initializes the key with a box shape and an associated image.
     * It sets the gravity scale to zero to prevent the key from being affected by gravity,
     * and initializes the sound clip to be played when the key is destroyed.
     * </p>
     *
     * @param world The game world where this key will exist.
     */
    public Key(World world) {
        super(world, keyShape);
        addImage(keyImage);
        setGravityScale(0);
    }

    // Static initializer to load the sound effect for the key.
    static {
        try {
            keySound = new SoundClip("data/Key.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Plays a sound effect and destroys the key when this method is called.
     * <p>
     * Overrides the destroy method of the superclass to include playing a sound effect when the key is
     * destroyed. This method enhances the interaction feedback for the player by providing an auditory
     * cue that a key has been collected.
     * </p>
     */
    @Override
    public void destroy() {
        keySound.play();
        super.destroy();
    }
}
