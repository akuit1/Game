package game;

import city.cs.engine.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Controls a {@link Player} object in response to user inputs from the keyboard and mouse. This controller
 * handles directional movement, jumping, and shooting based on key presses and mouse clicks.
 * It also updates the player's state such as possessing a gun and controls the visual representation
 * of the player depending on the current action (walking left/right, idling, jumping, and shooting).
 *
 * <p>
 * This class implements {@link KeyListener} for handling keyboard events and {@link MouseListener}
 * for handling mouse events to control player actions like movement and shooting.
 * </p>
 *
 * @author Idrees Nasar-ullah, Idrees.nasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class PlayerController implements StepListener, KeyListener, MouseListener {
    private Player player;
    private float speed, jumpImpulse;

    public static boolean right;
    public static boolean hasGun;

    /**
     * Constructs a PlayerController for the specified player with defined speed and jump impulse.
     *
     * @param player The player object to control.
     * @param speed The horizontal movement speed of the player.
     * @param jumpImpulse The vertical impulse for jumping.
     */
    public PlayerController(Player player, float speed, float jumpImpulse) {
        this.player = player;
        this.jumpImpulse = jumpImpulse;
        this.speed = speed;
    }

    /**
     * Checks and updates the gun possession status of the player based on the player's position.
     */
    public void updateHasGun() {
        if (player.getPosition().x >= -3 && player.getPosition().x <= 3 && player.getPosition().y >= 12 && player.getPosition().y <= 14) {
            hasGun = true;
        }
    }

    /**
     * Sets the gun possession status to true.
     */
    public void setHasGun() {
        hasGun = true;
    }

    /**
     * Invoked before each physics step.
     * This method is used to update the state of the game or player before physics calculations.
     *
     * @param stepEvent Provides context about the current step event, not currently used.
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        updateHasGun();
    }

    /**
     * Invoked after each physics step.
     * Can be used to implement any necessary post-step processing, currently does nothing.
     *
     * @param stepEvent Provides context about the current step event, not currently used.
     */
    @Override
    public void postStep(StepEvent stepEvent) {
    }

    /**
     * Not used, implemented method from the KeyListener interface.
     *
     * @param e The KeyEvent triggered when a key is typed.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Responds to key press events to control the player.
     * Handles the logic for starting the player's walking, jumping, and the direction facing based on key presses.
     *
     * @param e The KeyEvent containing the information about the key pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            player.startWalking(-speed);
            player.flipImageleft(); // Flip the image when moving left
            right = false;
        } else if (key == KeyEvent.VK_D) {
            player.startWalking(speed);
            player.flipImageright(); // Flip the image when moving right
            right = true;
        } else if (key == KeyEvent.VK_W) {
            player.jump(jumpImpulse);
        }
    }

    /**
     * Responds to key release events to stop the player's movement or update the player's idle state.
     * This method controls what happens when the player stops moving, such as stopping walking or
     * updating the visual state to show the player holding a gun or idling.
     *
     * @param e The KeyEvent containing the information about the key released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        player.startWalking(0);
        if (!hasGun) {
            if (right) {
                player.idleright();
            } else if (!right) {
                player.idleleft();
            }
        } else if (hasGun) {
            if (right) {
                player.gunright();
            } else if (!right) {
                player.gunleft();
            }
        }
    }

    /**
     * Handles mouse click events to shoot bullets if the player has a gun.
     * Creates and shoots a bullet in the direction the player is facing when the mouse button is clicked.
     *
     * @param e The MouseEvent containing details about the mouse click.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (hasGun) {
            if (right) {
                Bullets bullet = new Bullets(player.getWorld());
                bullet.setPosition(new Vec2(player.getPosition().x + 2, player.getPosition().y));
                bullet.setLinearVelocity(new Vec2(20, 0));
                bullet.addCollisionListener(new BulletCollision(bullet));
            } else {
                Bullets bullet = new Bullets(player.getWorld());
                bullet.setPosition(new Vec2(player.getPosition().x - 2, player.getPosition().y));
                bullet.setLinearVelocity(new Vec2(-20, 0));
                bullet.addCollisionListener(new BulletCollision(bullet));
            }
        }
    }

    /**
     * Not used, implemented method from the MouseListener interface.
     *
     * @param e The MouseEvent triggered when a mouse button is pressed.
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Not used, implemented method from the MouseListener interface.
     *
     * @param e The MouseEvent triggered when a mouse button is released.
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Not used, implemented method from the MouseListener interface.
     *
     * @param e The MouseEvent triggered when the mouse enters the component.
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Not used, implemented method from the MouseListener interface.
     *
     * @param e The MouseEvent triggered when the mouse exits the component.
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Updates the {@link Player} object being controlled.
     * This method allows for changing the player object controlled by the controller,
     * useful when changing levels or players in the game.
     *
     * @param newPlayer The new player object to control.
     */
    public void updatePlayer(Player newPlayer) {
        player = newPlayer;
    }
}
