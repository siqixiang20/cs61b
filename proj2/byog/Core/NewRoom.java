package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/* Create rectangular room filled with FLOOR and surrounded by WALL. */
public class NewRoom {
    //Static Setting
    private static final int maxRoomWidth = 7;
    private static final int maxRoomHeight = 7;
    Position leftBottom;
    int width;
    int height;

    NewRoom(Position leftBottom, int width, int height) {
        leftBottom = leftBottom;
        width = width;
        height = height;
    }

    /* Create new room; without entrance and exit door. */
    public void createRoom(TETile[][] world, Position leftBottom, int width, int height) {
        int leftBotX = leftBottom.x;
        int leftBotY = leftBottom.y;
        int rightUppX = leftBotX + width;
        int rightUppY = leftBotY + height;

        for (int x = leftBotX; x <= rightUppX; x +=1 ) {
            for (int y = leftBotY; y <= rightUppY; y += 1) {
                if (x > leftBotX &&  rightUppX > x && y > leftBotY && rightUppY > y) {
                    world[x][y] = Tileset.FLOOR;
                } else {
                    world[x][y] = Tileset.WALL;
                }
            }
        }
    }

    public void entrance(TETile[][] world, Position entrance) {
        world[entrance.x][entrance.y] = Tileset.FLOOR;
    }

    public void exit(TETile[][] world, Position exit) {
        world[exit.x][exit.y] = Tileset.FLOOR;
    }

    public boolean checkRoomSpace(TETile[][] world, Position leftBottom, int width, int height,
                                  int worldWidth, int worldHeight) {
        int leftBotX = leftBottom.x;
        int leftBotY = leftBottom.y;
        int rightUppX = leftBotX + width;
        int rightUppY = leftBotY + height;

        if (leftBotX < 0 || leftBotY < 0 || rightUppX < 0 || rightUppY < 0 ||
        worldWidth < leftBotX || worldWidth < rightUppX ||
                worldHeight < leftBotY || worldHeight < rightUppY) {
            return false;
        }

        for (int x = leftBotX; x <= rightUppX; x +=1 ) {
            for (int y = leftBotY; y <= rightUppY; y += 1) {
                TETile currentTile = world[x][y];
                if (currentTile != Tileset.NOTHING) {
                    return false;
                }
            }
        }

        return true;
    }


}
