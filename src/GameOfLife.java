import java.util.Arrays;

public class GameOfLife {

    public static void main(String[] args) {
        int[][] init = { 
            { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }};


        GameOfLife game = new GameOfLife(init, 10);
        game.start();
    }

    private int[][] grid;

    private int size;

    public GameOfLife(int[][] intitialGrid, int size) {
        this.size = size;
        grid = intitialGrid;
    }

    public void start() {
        int i = 0;
        while (true) {
            System.out.println("Generation " + i + ":");
            System.out.println(toString());
            
            nextGeneration();
            i++;

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void nextGeneration() {
        int[][] next = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int neighbors = census(i, j);
    
                if (grid[i][j] == 1 && neighbors < 2) {
                    next[i][j] = 0;
                } else if (grid[i][j] == 1 && neighbors > 3) {
                    next[i][j] = 0;
                } else if (grid[i][j] == 0 && neighbors == 3) {
                    next[i][j] = 1;
                } else {
                    next[i][j] = grid[i][j];
                }
            }
        }
        grid = next;
    }

    private int census(int row, int col) {
        int neighbors = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((row + i >= 0 && row + i < size) &&
                    (col + j >= 0 && col + j < size))
                        neighbors += grid[row + i][col + j];
            }
        }
        neighbors -= grid[row][col];
        return neighbors;
    }

    public String toString() {
        String ret = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == 1) {
                    ret += "* ";
                } else {
                    ret += ". ";
                }
            }
            ret += "\n";
        }
        return ret;
    }
}
