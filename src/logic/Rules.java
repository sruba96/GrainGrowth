package logic;

/**
 * Created by pawel on 09.05.16.
 */
public class Rules {

    private int[][] grid;
    private int[][] nextGrid;
    private int gridSize;
    private boolean periodic;
    private int selectedIndex;

    public static void main(String[] args) {

        Rules rules = new Rules();
        System.out.println(rules.takeID(0, 2, 2, 0, 4));

    }

    public int[][] generationUP(int[][] grid, boolean periodic, int selectedIndex) {
        this.periodic = periodic;
        this.grid = grid;
        this.gridSize = grid.length;
        this.selectedIndex = selectedIndex;
        nextGrid = cloneArray(grid);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] == 0)
                    updateCell(i, j);
            }
        }

        return cloneArray(nextGrid);
    }

    private void updateCell(int x, int y) {

        int NW, N, NE, W, E, SW, S, SE;

        NW = checkCell(x - 1, y - 1);
        N = checkCell(x - 1, y);
        NE = checkCell(x - 1, y + 1);
        W = checkCell(x, y - 1);
        E = checkCell(x, y + 1);
        SW = checkCell(x + 1, y - 1);
        S = checkCell(x + 1, y);
        SE = checkCell(x + 1, y + 1);

        int ID = 0;

        switch (selectedIndex) {
            case 0:
                ID = takeID(N, E, W, S, SW, SE, NE, NW);
                break;

            case 1:
                ID = takeID(N, E, W, S);
                break;

            case 2:
                ID = takeID(N, E, W, S, SW, NW);
                break;

            case 3:
                ID = takeID(N, E, W, S, NE, SE);
                break;

        }

        if(ID > 0)
            nextGrid[x][y] = ID;

    }

    private int checkCell(int x, int y) {

        if (x < 0) {
            if (periodic)
                x = gridSize - 1;
            else
                return 0;
        }
        if (x > gridSize - 1) {
            if (periodic)
                x = 0;
            else
                return 0;
        }

        if (y < 0) {
            if (periodic)
                y = gridSize - 1;
            else
                return 0;
        }
        if (y > gridSize - 1) {
            if (periodic)
                y = 0;
            else
                return 0;
        }

        return grid[x][y];

    }

    private int takeID(int... cells) {

        int which, how, howBest, whiczBest;

        whiczBest = 0;
        howBest = 0;

        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == 0)
                continue;
            which = cells[i];
            how = 0;
            for (int j = 0; j < cells.length; j++) {
                if (cells[i] == cells[j])
                    how++;
            }

            if (how > howBest) {
                howBest = how;
                whiczBest = which;
            }
        }
        return whiczBest;
    }

    private int[][] cloneArray(int[][] toCLone) {
        int[][] clone = new int[toCLone.length][];
        for (int i = 0; i < toCLone.length; i++)
            clone[i] = toCLone[i].clone();

        return clone;
    }


}
