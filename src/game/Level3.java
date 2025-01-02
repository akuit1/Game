package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Represents the third level of the game, designed with various interactive and dynamic elements.
 * <p>
 * This class extends {@link GameLevel} to construct a level that includes moving platforms, a flying enemy,
 * a key-door mechanism, and a final collectible diamond that completes the level. This level introduces
 * more complex challenges, including vertical and horizontal moving platforms and enhanced enemy interactions.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class Level3 extends GameLevel{
    private FlyingEnemy flyingEnemy1;
    private Door door;
    private Key key;
    private Diamond diamond;
    private StaticBody floor1, wall1, wall2, movingPlatformHorizontal, movingPlatformVertical;

    /**
     * Constructs Level 3 of the game, setting up the environment, platforms, enemies, and collectibles.
     *
     * @param game The main game control object used to manage states and interactions across levels.
     */
    public Level3(Game game){
        super(game);

        // Set up static structures and moving platforms
        Shape Wall = new BoxShape(0.5f, 7.5f);

        wall1 = new StaticBody(this, Wall);
        wall1.setPosition(new Vec2(-20, 2));

        Shape Wall2 = new BoxShape(0.5f, 10.5f);

        wall2 = new StaticBody(this, Wall2);
        wall2.setPosition(new Vec2(10, -1));

        Shape floor = new BoxShape(15.5f,0.5f);

        floor1 = new StaticBody(this, floor);
        floor1.setPosition(new Vec2(-5,9));

        Shape MovingPlatform = new BoxShape(3f, 0.3f);

        movingPlatformHorizontal = new StaticBody(this, MovingPlatform);
        movingPlatformHorizontal.setPosition(new Vec2(22,-5));
        this.addStepListener(new HorizontalPlatformMovement(movingPlatformHorizontal, 0.1f, 4, -8));

        movingPlatformVertical = new StaticBody(this, MovingPlatform);
        movingPlatformVertical.setPosition(new Vec2(15,2));
        this.addStepListener(new VerticalPlatformMovement(movingPlatformVertical, 0.05f, 5, -5));

        // Initialize and position player
        getPlayer().setPosition(new Vec2(27f, -10f));

        flyingEnemy1 = new FlyingEnemy(this, getPlayer(), 8.0f);
        flyingEnemy1.setPosition(new Vec2(-24,0));
        this.addStepListener(new FlyingUpdater(flyingEnemy1));

        door = new Door(this);
        door.setPosition(new Vec2(-20,-9));
        getPlayer().addCollisionListener(new DoorwayCollision(this, game));

        key = new Key(this);
        key.setPosition(new Vec2(-27, -8));
        this.addStepListener(new KeyMovement(key,-0.015f, -7,-9));
        getPlayer().addCollisionListener(new KeyCollision(getPlayer(), key));

        diamond = new Diamond(this);
        diamond.setPosition(new Vec2(0,0));
        getPlayer().addCollisionListener(new DiamondCollision(game));

        // Set enemy count for level completion logic
        FlyingEnemy.setEnemyCount(1);
    }

    /**
     * Checks if the level is complete, based on the winning condition of collecting the diamond.
     *
     * @return true if the diamond has been collected and the level is won, false otherwise.
     */
    @Override
    public boolean isComplete() {
        if (GameState.islevelWon()) {
            return true;
        }
        else return false;
    }

    /**
     * Destroys all level-specific objects such as platforms and structures when the level is reset or completed.
     */
    public void destroyLevelObjects() {
        // Destroy platforms and floor
        if (movingPlatformHorizontal != null) movingPlatformHorizontal.destroy();
        if (movingPlatformVertical != null) movingPlatformVertical.destroy();
        if (floor1 != null) floor1.destroy();
        if (wall1 != null) wall1.destroy();
        if (wall2 != null) wall2.destroy();
    }
}
