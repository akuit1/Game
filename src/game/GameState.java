package game;

import city.cs.engine.*;

/**
 * Manages and tracks the state of the game, including game over, level won, and game won conditions.
 * <p>
 * This class provides static methods and variables to globally track and modify the state of the game.
 * It ensures that information regarding the game's progression and completion is centrally managed and accessible,
 * facilitating control over game flow and state transitions across different components of the game.
 * </p>
 *
 * @author Idrees Nasar-ullah, idreesnasar-ullah@city.ac.uk
 * @version 1.0
 * @since 1.0
 */
public class GameState {
    private static boolean gameOver = false;
    private static boolean levelWon = false;
    private static boolean gameWon = false;

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise.
     */
    public static boolean isGameOver() {
        return gameOver;
    }

    /**
     * Checks if the current level has been won.
     *
     * @return true if the current level is won, false otherwise.
     */
    public static boolean islevelWon() {
        return levelWon;
    }

    /**
     * Checks if the game has been won.
     *
     * @return true if the game has been won, false otherwise.
     */
    public static boolean isGameWon() {
        return gameWon;
    }

    /**
     * Sets the game over state.
     *
     * @param state A boolean value representing if the game should be set to over or not.
     */
    public static void setGameOver(boolean state) {
        gameOver = state;
    }

    /**
     * Sets the state of the current level being won.
     *
     * @param state A boolean value representing if the current level is won.
     */
    public static void setlevelWon(boolean state) {
        levelWon = state;
    }

    /**
     * Sets the game won state.
     *
     * @param gameWon A boolean value representing if the game is won.
     */
    public static void setGameWon(boolean gameWon) {
        GameState.gameWon = gameWon;
    }
}
