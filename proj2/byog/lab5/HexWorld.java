package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 999;
    private static final Random RANDOM = new Random(SEED);

    private static final int side = 2;
    // Random Tiles
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(7);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.PLAYER;
            case 3: return Tileset.FLOOR;
            case 4: return Tileset.GRASS;
            case 5: return Tileset.SAND;
            case 6: return Tileset.WATER;
            default: return Tileset.WALL;
        }
    }

    public static void halfHexagon(TETile[][] tiles, int xPos, int yPos, int s,
                                  int currentLine, TETile randomTile){
        for (int i = 0; i < s + 2 * currentLine; i += 1) {
            tiles[xPos + i][yPos] = randomTile;
        }
    }

    // Drawing A Single Hexagon
    public static void addHexagon(TETile[][] tiles, int xPos, int yPos, int side){
        TETile randomTile = randomTile();
        int currentLine = 0;

        for (int i = 0; i < side; i += 1){
            halfHexagon(tiles, xPos - i, yPos - i, side, currentLine, randomTile);
            currentLine += 1;
        }

        for (int i = 0; i < side; i += 1){
            currentLine -= 1;
            halfHexagon(tiles, xPos - side + i+1, yPos - (i+side), side, currentLine, randomTile);
        }
    }

    // Drawing A Tesselation of Hexagons for one column.
    public static void HexColumn(TETile[][] tiles, int xPos, int yPos, int amount, int side){
        for (int i = 0; i < amount; i += 1){
            addHexagon(tiles, xPos, yPos - i*2*side, side);
        }
    }

    // Drawing A Tesselation of Hexagons consisting of 19 total hexagons.
    public static void HexTesselation(TETile[][] tiles, int xPos, int yPos, int side){
        int amount = 3;
        int xStart = xPos;
        int yStart = yPos;
        for (int i = 0; i < 5; i += 1){
            HexColumn(tiles, xStart, yStart, amount,side);
            if (i < 2){
                amount += 1;
                yStart += side;
            }else {
                amount -= 1;
                yStart -= side;
            }
            xStart += 2*side - 1;
        }
    }
    public static void main(String[] args){
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        HexTesselation(world, 5, 35,side);
        ter.renderFrame(world);
    }
}
