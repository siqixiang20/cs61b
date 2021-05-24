package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class RandomWorldGenerator {
    private TETile[][] world;

    //World Setting
    private int worldWidth;
    private int worldHeight;
    private Position doorPos;
    private Random random;

    //Constructor
    /**
     *
     * @param width       world width
     * @param height      world height
     * @param xStartPos   x coordinate of initial LOCKED_DOOR
     * @param yStartPos   y coordinate of initial LOCKED_DOOR
     * @param seed        random seed
     */
    public RandomWorldGenerator(int width, int height, int xStartPos,
                                int yStartPos, int seed) {
        if ((Integer)seed == null) {
            random = new Random();
        } else {
            random = new Random(seed);
        }
        worldWidth = width;
        worldHeight = height;
        doorPos = new Position(xStartPos, yStartPos);
    }

    /* Initialize a world with NOTHING */
    private void initialize() {
        TETile[][] world = new TETile[worldWidth][worldHeight];
        for (int x = 0; x < worldWidth; x += 1) {
            for (int y = 0; y < worldHeight; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    private void createRoom(int ) {}


}
