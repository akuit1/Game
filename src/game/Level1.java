package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Represents the first level of the game, containing specific game objects like platforms, enemies, and collectibles.
 * <p>
 * This class is a specific implementation of a game level within the platformer game, featuring unique platforms,
 * enemies with patrolling behaviors, collectible items such as guns and health potions, and a portal to complete the level.
 * It sets up the environment, positions game entities, and adds dynamic behaviors to them. The completion of the level
 * is determined by the player's interactions within the level.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class Level1 extends GameLevel{
    private Gun gun;
    private HealthPotion healthPotion;
    private Enemy enemy1, enemy2, enemy3, enemy4;
    private Portal portal;
    private StaticBody platform1, platform2, platform3, ledge1, ledge2, MiddlePlatform1;

    /**
     * Constructs Level 1 of the game, initializing game objects and setting up the environment.
     *
     * @param game The main game control object used to manage states and interactions across levels.
     */
    public Level1(Game game) {
        super(game);
        // makes suspended platforms
        Shape platformShape = new BoxShape(4, 0.3f);

        platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-20, -5f));

        platform2 = new StaticBody(this, platformShape);
        platform2.setPosition(new Vec2(0, 10f));

        platform3 = new StaticBody(this, platformShape);
        platform3.setPosition(new Vec2(20, -5f));

        //makes ledges

        Shape ledge = new BoxShape(8, 0.5f);

        ledge1 = new StaticBody(this, ledge);
        ledge1.setPosition(new Vec2(-22, 7));

        ledge2 = new StaticBody(this, ledge);
        ledge2.setPosition(new Vec2(22, 7));

        //makes middle platform

        Shape MiddlePlatform = new BoxShape(13, 0.5f);

        MiddlePlatform1 = new StaticBody(this, MiddlePlatform);
        MiddlePlatform1.setPosition(new Vec2(0, 1f));

        //sets player spawn point

        getPlayer().setPosition(new Vec2(-20f, -10f));

        // Creates the Enemies, sets spawn point and movement
        enemy1 = new Enemy(this);
        enemy1.setPosition(new Vec2(-20, 9));
        this.addStepListener(new EnemyPatrolling(enemy1, 0.1f, -17, -28));

        enemy2 = new Enemy(this);
        enemy2.setPosition(new Vec2(20, 9));
        this.addStepListener(new EnemyPatrolling(enemy2, 0.1f, 28, 17));

        enemy3 = new Enemy(this);
        enemy3.setPosition(new Vec2(23, -10));
        this.addStepListener(new EnemyPatrolling(enemy3, 0.2f, 25, -25));

        enemy4 = new Enemy(this);
        enemy4.setPosition(new Vec2(0, 3));
        this.addStepListener(new EnemyPatrolling(enemy4, 0.1f, 10, -10));

        //creates instance of gun, sets spawn point, adds movement and collision
        gun = new Gun(this);
        gun.setPosition(new Vec2(0, 15));
        this.addStepListener(new CollectibleMovement(gun, -0.03f, 14, 12));
        getPlayer().addCollisionListener(new GunCollision(getPlayer(), gun));

        //creates instance of health potion, sets spawn point, adds movement and collision
        healthPotion = new HealthPotion(this);
        healthPotion.setPosition(new Vec2(27, 16));
        this.addStepListener(new CollectibleMovement(healthPotion, -0.03f, 15, 13));
        getPlayer().addCollisionListener(new HealthPotionCollision(getPlayer(), healthPotion));

        //creates instance of Portal, sets spawn point and adds collision
        portal = new Portal(this);
        portal.setPosition(new Vec2(0, -3));
        getPlayer().addCollisionListener(new DoorwayCollision(this, game));

        //sets count of enemies

        Enemy.setEnemyCount(4);
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
     * Destroys all level-specific objects such as platforms and ledges when the level is reset or completed.
     */
    public void destroyLevelObjects() {
        // Destroy platforms and ledges
        if (platform1 != null) platform1.destroy();
        if (platform2 != null) platform2.destroy();
        if (platform3 != null) platform3.destroy();
        if (ledge1 != null) ledge1.destroy();
        if (ledge2 != null) ledge2.destroy();
        if (MiddlePlatform1 != null) MiddlePlatform1.destroy();
    }
}
