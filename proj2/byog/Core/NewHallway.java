package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class NewHallway {
    public Position start;
    public int length;

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

}
