package game;

import city.cs.engine.*;

/**
 * Implements periodic updates for a flying enemy's behavior within the game.
 * <p>
 * This class implements {@link StepListener} to consistently apply updates to a {@link FlyingEnemy}'s
 * behavior each time the physics world steps forward. This is particularly important for dynamic
 * behaviors such as pursuing the player or reacting to game events. The updates are applied during
 * the post-step phase to ensure that all physics calculations for the step are complete.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class FlyingUpdater implements StepListener {
    private FlyingEnemy flyingEnemy;

    /**
     * Constructs an updater for a specific flying enemy.
     * <p>
     * This constructor initializes the updater with a reference to the flying enemy it will manage.
     * This setup allows the updater to directly invoke behavior-modifying methods on the flying enemy
     * after each physics step, ensuring the enemy's actions remain consistent with the game's state.
     * </p>
     *
     * @param flyingEnemy The flying enemy to be updated.
     */
    public FlyingUpdater(FlyingEnemy flyingEnemy) {
        this.flyingEnemy = flyingEnemy;
    }

    /**
     * No actions are taken before the physics step.
     * @param e The event details of the pre-step phase.
     */
    @Override
    public void preStep(StepEvent e) {
    }

    /**
     * Updates the flying enemy's behavior after each physics step.
     * <p>
     * This method is called after the physics world has processed a step, ensuring that any changes to
     * the flying enemy's behavior (like movement or AI decisions) are applied based on the latest game
     * state. It specifically invokes the update method of the {@link FlyingEnemy} class, which might
     * handle complex behaviors like adjusting trajectory towards the player.
     * </p>
     *
     * @param e The event details of the post-step phase.
     */
    @Override
    public void postStep(StepEvent e) {
        flyingEnemy.update();
    }
}
