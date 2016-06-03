package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by pawel on 19.05.16.
 */
public class NorthPanel extends JPanel {

    BoardPanel boardPanel;
    JTextField jTextField;

    public NorthPanel(BoardPanel boardPanel) {
        super(new FlowLayout());
        this.boardPanel = boardPanel;

        jTextField = new JTextField("100");
        jTextField.setSize(30, 20);

        JButton randomButton = new JButton("Random");
        randomButton.addActionListener(new RandomButtonListener());

        JButton recrystalizationButton = new JButton("Recy");
        recrystalizationButton.addActionListener(new RecystalizationButtonListener());


        JButton notRandomButton = new JButton("Not random");
        notRandomButton.addActionListener(new NotRandomButtonListener());


        JButton radiusButton = new JButton("Radius");
        radiusButton.addActionListener(new RadiusButtonListener());

        // TODO: 19.05.16 I was creating NorthPanel ... with some functions to add life
        this.add(jTextField);
        this.add(randomButton);
        this.add(notRandomButton);
        this.add(radiusButton);
        this.add(recrystalizationButton);
    }

    private class RandomButtonListener implements ActionListener {

        Random random = new Random();

        int size = boardPanel.getSIZE();
        int x, y;

        @Override
        public void actionPerformed(ActionEvent e) {

            int howMany = Integer.parseInt(jTextField.getText());
            System.out.println(howMany);
            for (int i = 0; i < howMany; i++) {


                x = random.nextInt(size);
                y = random.nextInt(size);

                boardPanel.addLife(x, y);
            }


        }
    }

    private class NotRandomButtonListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            int krok = 3;
            krok = Integer.parseInt(jTextField.getText());

            if (krok < 3 || krok > 50)
                krok = 3;


            for (int i = krok; i < 50; i = i + krok) {
                for (int j = krok; j < 50; j = j + krok) {
                    boardPanel.addLife(i, j);
                }
            }
        }
    }

    private class RecystalizationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


//            if (boardPanel.rules.growingIsDone) {
//                boardPanel.recrystalizationTimer.start();

//            while(true)
//            {
//                boardPanel.rules.recrystalizationUp();
//                boardPanel.repaint();
//            }

                if (!boardPanel.recrystalizationTimer.isRunning()) {
                    boardPanel.rules.recsInit();
                    boardPanel.rules.growingIsDone = true;
                    boardPanel.recrystalizationTimer.start();
                }
               else
                    boardPanel.recrystalizationTimer.stop();
            }
        }


    private class RadiusButtonListener implements ActionListener {

        Random random = new Random();

        int howMany = random.nextInt(100);
        int x, y;

        @Override
        public void actionPerformed(ActionEvent e) {

            int rad = 3;
            rad = Integer.parseInt(jTextField.getText());

            if (rad < 3 || rad > 50)
                rad = 25;

            for (int i = 0; i < howMany; i++) {

                x = random.nextInt(51);
                y = random.nextInt(51);

                int x1, x2, y1, y2;
                if ((x - rad) < 0)
                    x1 = 0;
                else
                    x1 = x - rad;

                if ((x + rad) > 50)
                    x2 = 50;
                else
                    x2 = x + rad;

                if ((y - rad) < 0)
                    y1 = 0;
                else
                    y1 = y - rad;

                if ((y + rad) > 50)
                    y2 = 50;
                else
                    y2 = y + rad;

                int clear = 0;

                for (int j = x1; j < x2; j++) {
                    for (int k = y1; k < y2; k++) {

                        if (boardPanel.grid[j][k] != 0)
                            clear++;

                    }
                }

                if (clear == 0)
                    boardPanel.addLife(x, y);

            }

        }
    }


}
