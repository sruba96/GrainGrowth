package gui;

import data.Data;
import domain.Germ;
import logic.Rules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pawel on 08.05.16.
 */
public class BoardPanel extends JPanel {

    private static final int PANELSIZE = Data.SIZE;
    private static final int CELLSIZE = Data.CELLSIZE;
    public Timer timer;
    private int[][] grid;
    private int generationCounter;
    private boolean periodic = false;
    private int selectedIndex = 0;
    private int width = PANELSIZE;
    private int height = PANELSIZE;
    private BoardMouseListener boardMouseListener;
    private List<Germ> germList;
    private Random random = new Random();
    private Rules rules;

    public BoardPanel() {
        germList = new ArrayList<Germ>();
        Germ germ = new Germ(Color.DARK_GRAY);
        germList.add(0, germ);
        rules = new Rules();
        boardMouseListener = new BoardMouseListener(this);
        this.addMouseListener(boardMouseListener);
        timer = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                grid = rules.generationUP(grid,true);
                repaint();
            }
        });

        clearAll();
    }

    public void clearAll() {
        this.grid = new int[this.width / CELLSIZE][this.height / CELLSIZE];
        generationCounter = 0;
        repaint();
    }

    public void addLife(int x, int y) {

        Color c = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        Germ germ = new Germ(c);


        grid[x][y] = germ.getLocalID();
        germList.add(germ.getLocalID(), germ);

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
                if (grid[i][j] > 0) {
                    g.setColor(germList.get(grid[i][j]).getColor());
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

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }
}
