package gui;

import data.Data;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by pawel on 09.05.16.
 */
public class BoardMouseListener implements MouseListener {

    BoardPanel boardPanel;

    public BoardMouseListener(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getY() / Data.CELLSIZE;
        int y = e.getX() / Data.CELLSIZE;

        boardPanel.addLife(x,y);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
