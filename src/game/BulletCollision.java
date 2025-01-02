package game;

import city.cs.engine.*;

/**
 * Manages collision events for bullets within the game.
 *
 * <p>
 * This class implements {@link CollisionListener} to handle collisions involving bullet objects.
 * When a bullet collides with an object that is not another bullet, it will destroy itself.
 * If the collision is with an enemy or a flying enemy, it additionally triggers damage processes
 * on the enemy, potentially destroying the enemy if its health is depleted.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class BulletCollision implements CollisionListener {
    private Bullets bullet;

    /**
     * Constructs a BulletCollision instance associated with a specific bullet.
     * This constructor stores a reference to the bullet object that will be checked
     * in collision events.
     *
     * @param bullet The bullet involved in this collision.
     */
    public BulletCollision(Bullets bullet) {
        this.bullet = bullet;
    }

    /**
     * Handles the collision event for the bullet.
     * When a collision occurs, the bullet will destroy itself unless it collides with another bullet.
     * If the colliding body is an instance of Enemy or FlyingEnemy, it triggers health reduction
     * on the enemy. If the enemy's health reaches zero, it will be destroyed and the enemy count
     * is decremented.
     *
     * @param e Details about the collision event.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (!(e.getOtherBody() instanceof Bullets)) {
            bullet.destroy();
            if (e.getOtherBody() instanceof Enemy) {
                Enemy enemy = (Enemy) e.getOtherBody();
                enemy.decreaseHealth();
                if (!enemy.isAlive()) {
                    e.getOtherBody().destroy();
                    Enemy.decreaseEnemyCount();
                }
            }
            if (e.getOtherBody() instanceof FlyingEnemy) {
                FlyingEnemy flyingenemy = (FlyingEnemy) e.getOtherBody();
                flyingenemy.decreaseHealth();
                if (!flyingenemy.isAlive()) {
                    e.getOtherBody().destroy();
                    FlyingEnemy.decreaseEnemyCount();
                }
            }
        }
    }
}
