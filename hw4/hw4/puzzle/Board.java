package hw4.puzzle;

import edu.princeton.cs.algs4.Stack;

public class Board {
    private int N;
    private int[][] block;

    private int hamming = -1;
    private int manhattan = -1;

    /** Constructs a board from an N-by-N array of tiles where
      * tiles[i][j] = tile at row i, column j. */
    public Board(int[][] tiles) {
        N = tiles.length;
        block = new int[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                block[i][j] = tiles[i][j];
            }
        }
    }

    /** Returns value of tile at row i, column j (or 0 if blank). */
    public int tileAt(int i, int j) {
        if (i < 0 | i > N - 1 | j < 0 | j > N - 1) {
            throw new IndexOutOfBoundsException("Board i, j out of bound.");
        }
        return block[i][j];
    }

    /** Returns the board size N.*/
    public int size() {
        return N;
    }

    /** Returns the neighbors of the current board.*/
    public Iterable<WorldState> neighbors() {
        Stack<WorldState> neighbor = new Stack<>();
        int row = -1;
        int col = -1;

        int[][] newTile = new int[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                newTile[i][j] = tileAt(i, j);
                if (newTile[i][j] == 0) {
                    row = i;
                    col = j;
                }
            }
        }

        for (int i = Math.max(0,row - 1); i <= Math.min(N-1, row + 1); i += 1) {
            for (int j = Math.max(0,col - 1); j <= Math.min(N-1, col + 1); j += 1) {
                if (Math.abs(i - row) + Math.abs(j - col) == 1) {
                    newTile[row][col] = block[i][j];
                    newTile[i][j] = 0;
                    neighbor.push((WorldState) new Board(newTile));
                    newTile[row][col] = 0;
                    newTile[i][j] = block[i][j];
                }
            }
        }
        return neighbor;
    }

    /** Hamming estimate described below.*/
    public int hamming() {
        if (hamming >= 0) { return hamming; };
        hamming = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (block[i][j] != i * N + j + 1
                        && i * N + j + 1 != N * N) {
                    hamming += 1;
                }
            }
        }
        return hamming;
    }

    /** Manhattan estimate described below.*/
    public int manhattan() {
        if (manhattan >= 0) {return manhattan;}
        manhattan = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (block[i][j] != 0) {
                    int x = (block[i][j] - 1) / N;
                    int y = block[i][j] % N - 1;

                    int dx = Math.abs(x - i);
                    int dy = Math.abs(y - i);

                    manhattan += dx + dy;
                }
            }

        }
        return manhattan;
    }

    /** Estimated distance to goal. This method should
      * simply return the results of manhattan() when submitted to
      * Gradescope.*/
    public int estimatedDistanceToGoal() {
        return manhattan;
    }

    /** Returns true if this board's tile values are the same
      * position as y's.*/
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        int[][] current = ((Board) y).block;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (current[i][j] != block[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
