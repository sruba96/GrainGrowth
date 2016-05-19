package domain;

import java.awt.*;

/**
 * Created by pawel on 19.05.16.
 */
public class Germ {
    private static int GlobalID = 0;
    private int LocalID;

    private Color color;


    public Germ(Color color) {
        LocalID = GlobalID++;


        this.color = color;
    }

    public int getLocalID() {
        return LocalID;
    }

    public Color getColor() {
        return color;
    }
}
