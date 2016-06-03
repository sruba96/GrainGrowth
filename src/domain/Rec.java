package domain;

/**
 * Created by pawel on 01.06.16.
 */
public class Rec {

    private boolean onEdge;
    private boolean willBeRect = false;
    private boolean willWillBeRec = false;
    private boolean isRect = false;
    public double dyslocation = 0;


    public Rec(boolean onEdge) {
        this.onEdge = onEdge;
    }


    public boolean isOnEdge() {
        return onEdge;
    }


    public void setOnEdge(boolean onEdge) {
        this.onEdge = onEdge;
    }

    public boolean isWillBeRect() {
        return willBeRect;
    }

    public void setWillBeRect(boolean willBeRect) {
        this.willBeRect = willBeRect;
    }

    public boolean isRect() {
        return isRect;
    }

    public void setRect(boolean rect) {
        dyslocation = 0;
        isRect = rect;
    }

    public boolean isWillWillBeRec() {
        return willWillBeRec;
    }

    public void setWillWillBeRec(boolean willWillBeRec) {
        this.willWillBeRec = willWillBeRec;
    }
}
