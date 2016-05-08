package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Transient;

/**
 * Created by pawel on 08.05.16.
 */
public class BoardPanel extends JPanel {

    private static final int PANELSIZE = Data.SIZE;
    private static final int CELLSIZE = Data.CELLSIZE;
    private int[][] grid;
    private int generationCounter;
    private boolean periodic = false;
    public Timer timer;
    private int width = PANELSIZE;
    private int height = PANELSIZE;

    public BoardPanel() {
        timer = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                boardPanel.updateGrid();
//                boardPanel.repaint();
            }
        });
    }

    public void clearAll() {
        this.grid = new int[this.width / CELLSIZE][this.height / CELLSIZE];
//        setupGrid();
        generationCounter = 0;
        repaint();
    }

    @Override
    @Transient
    public Dimension getPreferredSize() {
        return new Dimension(grid.length * CELLSIZE, grid[0].length * CELLSIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();

        g.drawString("Pokolenie: " + generationCounter++, 0, 10);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    g.setColor(Color.red);
                    g.fillRect(j * CELLSIZE, i * CELLSIZE, CELLSIZE, CELLSIZE);
                }
            }
        }

        g.setColor(gColor);
    }


    public boolean isPeriodic() {
        return periodic;
    }

    public void setPeriodic(boolean periodic) {
        this.periodic = periodic;
    }
}
