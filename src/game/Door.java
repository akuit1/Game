package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Represents a door in the game that can be interacted with by the player.
 * <p>
 * This class extends {@link Doorway} and configures a specific type of door using a box shape.
 * It includes visual representation through an image and has associated sound effects that play
 * when the door is destroyed. The sound effect signifies the door opening or being interacted with.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class Door extends Doorway {
    public static SoundClip door;

    /**
     * Constructs a new door object within the specified game world.
     * <p>
     * This constructor initializes the door with a rectangular box shape and an associated image.
     * The door's physical and visual properties are set here, including its size and the image scale.
     * </p>
     *
     * @param world The game world where this door will exist.
     */
    public Door(World world) {
        super(world, new BoxShape(1.5f, 8), "data/door.png", 11f);
    }

    // Static initializer to load the door sound effect.
    static {
        try {
            door = new SoundClip("data/Door.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Destroys the door and plays a sound effect.
     * <p>
     * Overrides the destroy method to include playing a sound effect when the door is destroyed,
     * which can be used to signify the door being opened or broken down.
     * </p>
     */
    @Override
    public void destroy() {
        door.play();
        super.destroy();
    }
}
