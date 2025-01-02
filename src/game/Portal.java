package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Represents a portal in a game world, which is a special type of doorway that emits sound when destroyed.
 * This class extends {@link Doorway} to include a sound effect that plays upon the portal's destruction.
 * The portal is visually represented by an animated GIF and has a circular physical shape.
 *
 * <p>
 * This class is part of a game package, leveraging the game engine's capabilities to manage
 * interactive game elements with sound and physics.
 * </p>
 *
 * @author Idrees Nasar-ullah, Idrees.nasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class Portal extends Doorway{
    private static SoundClip portal;

    /**
     * Constructs a Portal object with a specific appearance and behavior defined by its parent class.
     * The portal uses an animated GIF for its appearance and a circular shape for its physical presence.
     *
     * @param world The game world in which this portal exists. It must be a valid instance of {@link World}.
     */
    public Portal(World world) {
        super(world, new CircleShape(1.4f),"data/200w.gif",4f);
    }

    // Static initializer block to load the sound file used by all instances of this class.
    static {
        try {
            portal = new SoundClip("data/Portal.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     * Destroys the portal and plays a sound effect. This method overrides the destroy method of the
     * parent class {@link Doorway} to add functionality for playing a sound clip when the portal is destroyed.
     */
    @Override
    public void destroy()
    {
        portal.play();
        super.destroy();
    }
}
