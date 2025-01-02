package game;

import city.cs.engine.*;
import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Provides the visual rendering context for the game, handling the drawing of the game background,
 * player status bars, and other game state information.
 * <p>
 * This class extends {@link UserView} and is responsible for drawing the game's visual elements
 * on the screen. It includes methods for rendering the game's background image, health bars,
 * armour bars, and textual information such as the number of enemies remaining. It also provides
 * functionality for dynamically updating graphics based on game state changes.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class GameView extends UserView {
    private Image backgroundImage;
    private Image healthBar;
    private Player player;
    private Image armourbar;

    /**
     * Constructs a GameView with a specified width and height for the viewport.
     *
     * @param world The game level to be displayed.
     * @param player The player whose health and armour status are to be displayed.
     * @param width The width of the viewport.
     * @param height The height of the viewport.
     */
    public GameView(GameLevel world, Player player, int width, int height) {
        super(world, width, height);
        this.player = player;
        // Load the background image
        backgroundImage = new ImageIcon("data/factory background (1).webp").getImage();
        healthBar = new ImageIcon("data/2Bars.png").getImage();
        armourbar = new ImageIcon("data/ArmourEmpty.png").getImage();
        }

    @Override
    protected void paintBackground(Graphics2D g) {
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getHeight()*8/3, getHeight(), null);
    }

    @Override
    protected void paintForeground(Graphics2D g){
        // Dynamic updates to health and armour bar graphics
        int health = player.getHealth();
        if (health >= 3) {
            healthBar = new ImageIcon("data/3Bars.png").getImage();
        } else if (health == 2) {
            healthBar = new ImageIcon("data/2Bars.png").getImage();
        } else if (health == 1) {
            healthBar = new ImageIcon("data/1Bars.png").getImage();
        } else if (health == 0){
            healthBar = new ImageIcon("data/0Bars.png").getImage();
        }
        g.drawImage(healthBar,-90,-160,400,400,null);

        int armour = player.getArmour();
        if (armour >= 1){
            armourbar = new ImageIcon("data/ArmourFull.png").getImage();
        } else if (armour == 0){
            armourbar = new ImageIcon("data/ArmourEmpty.png").getImage();
        }
        g.drawImage(armourbar,80,-112,300,300,null);

        super.paintForeground(g);

        // Display enemy count and game state messages
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.setColor(Color.BLACK);
        g.drawString("Enemies Left: " + Enemy.getEnemyCount(), 20, 70);
        g.drawString("Flying Enemies Left: " + FlyingEnemy.getEnemyCount(), 20, 95);

        if (GameState.isGameOver()) {
            drawGameOverScreen(g);
        }
        if (GameState.isGameWon()) {
            drawGameWinScreen(g);
        }
    }
    private void drawGameOverScreen(Graphics2D g) {
        // Draws the Game Over screen
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.drawString("Game Over!", 450, 300);
    }

    private void drawGameWinScreen(Graphics2D g) {
        // Draws the Game Win screen
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.drawString("You Win!", 450, 300);
    }

    /**
     * Gets the current background image of the game view.
     *
     * @return The current background image.
     */
    public Image getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Updates the background image of the game view.
     *
     * @param newBackground The new image to be set as the background.
     */
    public void updateBackground(Image newBackground){
        this.backgroundImage = newBackground;
    }
}
