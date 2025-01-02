package game;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

/**
 * Represents the second level of the game, containing specialized setups for platforms, enemies, and collectibles.
 * <p>
 * This class extends {@link GameLevel} to implement a specific game level scenario. It includes various platforms and floors,
 * multiple enemies with different behaviors, and special collectibles such as armour and a key which are crucial for level
 * progression. The level also includes a door that requires a key to unlock, simulating a more complex game objective.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class Level2 extends GameLevel {
    private Enemy enemy1, enemy2, enemy3;
    private Armour armour;
    private Key key;
    private Door door;
    private StaticBody platform1, platform2, platform3, floor1, floor2;

    /**
     * Constructs Level 2 of the game, setting up the environment, platforms, enemies, and collectibles.
     *
     * @param game The main game control object used to manage states and interactions across levels.
     */
    public Level2(Game game) {
        super(game);

        // creates platforms

        Shape platformShape = new BoxShape(6, 0.3f);

        platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-17, -5f));

        platform2 = new StaticBody(this, platformShape);
        platform2.setPosition(new Vec2(-33, 1f));

        platform3 = new StaticBody(this, platformShape);
        platform3.setPosition(new Vec2(-17, 7f));

        //creates floors

        Shape floor = new BoxShape(18,0.3f);

        floor1 = new StaticBody(this, floor);
        floor1.setPosition(new Vec2(12,1f));

        floor2 = new StaticBody(this, floor);
        floor2.setPosition(new Vec2(12,13f));

        // sets player spawn point

        getPlayer().setPosition(new Vec2(-27f, -10f));

        Player player = getPlayer();

        // Creates the Enemies, sets spawn point and movement

        enemy1 = new Enemy(this);
        enemy1.setPosition(new Vec2(8, 14f));
        this.addStepListener(new EnemyPatrolling(enemy1, -0.3f, 20, -3));

        enemy2 = new Enemy(this);
        enemy2.setPosition(new Vec2(14, 2f));
        this.addStepListener(new EnemyPatrolling(enemy2, -0.3f, 25, -3));

        enemy3 = new Enemy(this);
        enemy3.setPosition(new Vec2(23, -10));
        this.addStepListener(new EnemyFollowsPlayer(enemy3, player, 8));

        //creates instance of armour, sets spawn point, adds movement and collision

        armour = new Armour(this);
        armour.setPosition(new Vec2(29, 7));
        this.addStepListener(new CollectibleMovement(armour, -0.03f, 8, 6));
        getPlayer().addCollisionListener(new ArmourCollision(getPlayer(), armour));

        //creates instance of key, sets spawn point, adds movement and collision

        key = new Key(this);
        key.setPosition(new Vec2(29,16));
        this.addStepListener(new KeyMovement(key,-0.015f,16,15));
        getPlayer().addCollisionListener(new KeyCollision(getPlayer(), key));

        //creates instance of Door, sets spawn point and adds collision

        door = new Door(this);
        door.setPosition(new Vec2(28,-9));
        getPlayer().addCollisionListener(new DoorwayCollision(this, game));

        //sets count of enemies

        Enemy.setEnemyCount(3);
    }

    /**
     * Checks if the level is complete, based on whether the level winning condition is met.
     *
     * @return true if the level is won (level winning condition), false otherwise.
     */
    @Override
    public boolean isComplete() {
        if (GameState.islevelWon()) {
            return true;
        }
        else return false;
    }

    /**
     * Destroys all level-specific objects such as platforms and floors when the level is reset or completed.
     */
    public void destroyLevelObjects() {
        // Destroy platforms and floor
        if (platform1 != null) platform1.destroy();
        if (platform2 != null) platform2.destroy();
        if (platform3 != null) platform3.destroy();
        if (floor1 != null) floor1.destroy();
        if (floor2 != null) floor2.destroy();
        }
}
