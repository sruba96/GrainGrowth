package logic;

import com.sun.org.apache.regexp.internal.RE;
import data.Data;
import domain.Rec;
import gui.BoardPanel;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by pawel on 09.05.16.
 */
public class Rules {

//    private static final Logger logger = Logger.getLogger(Rules.class);
    //recrystalization


    final double A = 86710969050178.5;
    final double B = 9.41268203527779;

    int Size = Data.SIZE / Data.CELLSIZE;
    double Ro = calculateRo(0);
    double BoardSize = Data.SIZE * Data.SIZE;
    double RoAvr = Ro / BoardSize;
    double time = 0.001;
    double oldRo;
    double critical = 46842668.248;  //4215840142323.42; // remove 4
    public boolean growingIsDone = false;
    int pack = Size * Size / 10;

    private BoardPanel boardPanel;

    Rec[][] rec = new Rec[Size][Size];
    Rec[][] recNew;

    public double calculateRo(double t) {
        return (A / B) + ((1 - (A / B)) * Math.exp(-B * t));
    }

    public void recsInit() {
        edgesInit();

    }

    public Rules(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public void edgesInit() {
        System.out.println("initial");
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {

                if (checkNeighborsIfEdge(i, j)) {

                    rec[i][j] = new Rec(true);
//                    grid[i][j] = 1;
                } else {
                    rec[i][j] = new Rec(false);
                }
            }
        }

    }

    public boolean checkNeighborsIfEdge(int x, int y) {
        int idGrain = grid[x][y];

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {


                if (i < 0)
                    continue;
                if (i >= Size)
                    continue;


                if (j < 0)
                    continue;
                if (j >= Size)
                    continue;

                if (i == x && y == j)
                    continue;

                if (grid[i][j] != idGrain)
                    return true;


            }
        }

        return false;
    }

    public int checkNeighborsIsRec(int x, int y) {
        int idGrain;

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {


                if (i < 0)
                    continue;
                if (i >= Size)
                    continue;


                if (j < 0)
                    continue;
                if (j >= Size)
                    continue;

                if (i == x && y == j)
                    continue;

                if (rec[i][j].isRect()) {
                    if (checkNeighborsLowestDyslocation(i, j))
                        return boardPanel.grid[i][j];
                }


            }
        }

        return -1;
    }

    public boolean checkNeighborsLowestDyslocation(int x, int y) {
        int idGrain;

        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {


                if (i < 0)
                    continue;
                if (i >= Size)
                    continue;


                if (j < 0)
                    continue;
                if (j >= Size)
                    continue;

                if (i == x && y == j)
                    continue;

                if (rec[x][y].dyslocation > rec[i][j].dyslocation && rec[x][y].dyslocation != 0) {
                        return false;
                }


            }
        }

        return true;
    }


    public void recrystalizationUp() {


        oldRo = Ro;
//        critical = Ro/BoardSize;
        Ro = calculateRo(time);

        double Ro2 = Ro;
//        System.out.println(Ro);
        Ro = Ro - oldRo;


        RoAvr = Ro / BoardSize;

        //recNew = cloneRec(rec);
        giveDyslocation(RoAvr);


        double suma = countingDyslocation();

//        System.out.println(time + " " + Ro2 + " " + suma);
        System.out.println(suma);

        time = time + 0.001;

    }

    private double countingDyslocation() {

        double suma = 0;
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                suma += rec[i][j].dyslocation;
            }
        }

        return suma;
    }

    private void giveDyslocation(double RoAvr) {
        Random rand = new Random();
        int count = 0;
//        while (count == pack) {
            for (int i = 0; i < Size - 1; i++) {
                for (int j = 0; j < Size - 1; j++) {


                    if (rec[i][j].isRect())
                        continue;

                    if (rec[i][j].isWillBeRect()) {
                        rec[i][j].setWillBeRect(false);
                        rec[i][j].setRect(true);

                        continue;
                    }


                    if (checkNeighborsIsRec(i, j) != -1) {

                        boardPanel.grid[i][j] = checkNeighborsIsRec(i, j);
                        rec[i][j].setWillBeRect(true);
                        continue;
                    }

                    if(rand.nextInt(100)>96) {
                        rec[i][j].dyslocation += randRo(RoAvr, rec[i][j]);
                        count++;
                    }
//
//                if (rec[i][j].isOnEdge() && i < 10 && j < 10)
//                    System.out.println(rec[i][j].dyslocation);

                    if (rec[i][j].dyslocation > critical) {

                        rec[i][j].dyslocation = 0;
                        rec[i][j].setWillBeRect(true);
                        boardPanel.addLife(i, j);
                        //rec[i][j].setRect(true);

//                    System.out.println("critical");


                    }
                }
            }



//        }
    }

    private double randRo(double roAvr, Rec rec) {
        Random rand = new Random();
        double percentage = 0;
        if (rec.isOnEdge()) {
            percentage = (double) ((rand.nextInt(61) + 20) / 100.0) + 1.0;
        } else {
            percentage = (double) (rand.nextInt(31)) / 100.0;
        }

//        System.out.println(percentage*roAvr);
        return percentage * roAvr;
    }


    // growing
    private int[][] grid;
    private int[][] nextGrid;
    private int gridSize;
    private boolean periodic;
    private int selectedIndex;

    private int NW, N, NE, W, E, SW, S, SE;

//    public static void main(String[] args) {
//
//        Rules rules = new Rules();
//        System.out.println(rules.takeID(0, 2, 2, 0, 4));
//
//    }

    public int[][] generationUP(int[][] grid, boolean periodic, int selectedIndex) {
        this.periodic = periodic;
        this.grid = grid;
        this.gridSize = grid.length;
        this.selectedIndex = selectedIndex;
        nextGrid = cloneArray(grid);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] == 0)
                    updateCell(i, j, selectedIndex);
            }
        }

        return cloneArray(nextGrid);
    }

    private void updateCell(int x, int y, int index) {

        int selectedIndexMy = index;

        NW = checkCell(x - 1, y - 1);
        N = checkCell(x - 1, y);
        NE = checkCell(x - 1, y + 1);
        W = checkCell(x, y - 1);
        E = checkCell(x, y + 1);
        SW = checkCell(x + 1, y - 1);
        S = checkCell(x + 1, y);
        SE = checkCell(x + 1, y + 1);

        int ID = 0;

        switch (selectedIndexMy) {
            case 0:
                ID = takeID(N, E, W, S, SW, SE, NE, NW);
                break;

            case 1:
                ID = takeID(N, E, W, S);
                break;

            case 2:
                ID = takeID(N, E, W, S, NW, SE);
                break;

            case 3:
                ID = takeID(N, E, W, S, NE, SW);
                break;

            case 4:
                ID = randHexagonal();
                break;
            case 5:
                ID = randPentagonal();
                break;
            case 6:
                randRandom(x, y);
                break;

        }

        if (ID > 0)
            nextGrid[x][y] = ID;

    }

    private void randRandom(int x, int y) {
        Random rand = new Random();
        int index = 0;
        index = rand.nextInt(6);

        updateCell(x, y, index);

    }

    private int randHexagonal() {
        Random random = new Random();

        int r = random.nextInt(100);

        if (r > 50)
            return takeID(N, E, W, S, NW, SE);
        else
            return takeID(N, E, W, S, NE, SW);

    }

    private int randPentagonal() {
        Random random = new Random();

        int r = random.nextInt(100);

        if (r < 26)
            return takeID(N, E, W, NW, NE);
        else if (r > 25 && r < 50)
            return takeID(N, W, S, NW, SW);
        else if (r > 50 && r < 75)
            return takeID(E, W, S, SE, SW);
        else
            return takeID(N, E, S, NE, SE);

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

    private Rec[][] cloneRec(Rec[][] toCLone) {
        Rec[][] clone = new Rec[toCLone.length][];
        for (int i = 0; i < toCLone.length; i++)
            clone[i] = toCLone[i].clone();

        return clone;
    }


}
