package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Represents a health potion collectible that players can pick up to restore health.
 * <p>
 * This class extends {@link Collectible} and creates a health potion with a circular shape
 * and an associated image. A sound clip is played when the potion is destroyed, typically indicating
 * that the player has used it to regain health.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class HealthPotion extends Collectible {
    private static SoundClip healthPotion;

    /**
     * Constructs a new HealthPotion object within the specified game world.
     * <p>
     * This constructor initializes the health potion with a circular shape and sets its visual and auditory
     * properties. The sound clip is loaded and will be played when the potion is destroyed, enhancing
     * the user interaction by providing audio feedback when a potion is used.
     * </p>
     *
     * @param world The game world where this health potion will exist.
     */
    public HealthPotion(World world) {
        super(world, new CircleShape(1.3f), "data/health_potion.png", 4f);
    }

    // Static initializer block to load the health potion sound effect.
    static {
        try {
            healthPotion = new SoundClip("data/HealthPotion.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Plays a sound effect and destroys the health potion when this method is called.
     * <p>
     * Overrides the destroy method of the superclass to include playing a sound effect when the health potion
     * is destroyed. This method typically indicates the player has used the potion to regain health.
     * </p>
     */
    @Override
    public void destroy() {
        healthPotion.play();
        super.destroy();
    }
}
