package game;
import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Represents bullet collectibles in the game world.
 *
 * <p>
 * This class extends {@link Collectible} and is responsible for creating bullet items in the game.
 * Each bullet has a small circular shape and an associated image. Upon creation, the bullet
 * immediately plays a sound effect, indicating it has been fired or activated.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class Bullets extends Collectible{
    private static SoundClip bullet;
    private static final Shape bulletShape = new CircleShape(0.25f);

    /**
     * Constructs a new bullet collectible in the specified game world.
     *
     * <p>
     * This constructor initializes the bullet with a predefined shape, an image, and sets its
     * properties as a bullet object in the physics engine. It also plays a sound upon instantiation.
     * </p>
     *
     * @param world The game world to which this bullet belongs.
     */
    public Bullets(World world) {
        super(world, bulletShape,"data/Bullet.png",4f);
        setBullet(true);
        bullet.play();
    }

    // Static initializer to load the bullet sound effect.
    static {
        try {
            bullet = new SoundClip("data/Bullet.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
}