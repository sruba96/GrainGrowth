package logic;

/**
 * Created by pawel on 09.05.16.
 */
public class Rules {


    private int[][] nextGrid;



    public void generationUP(){
        // TODO: 09.05.16 create all game or growth 

    }

    public int[][] cloneArray(int[][] toCLone) {
        int[][] clone = new int[toCLone.length][];
        for (int i = 0; i < toCLone.length; i++)
            clone[i] = toCLone[i].clone();

        return clone;
    }

}
