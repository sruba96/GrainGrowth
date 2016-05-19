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

        JButton notRandomButton = new JButton("Not random");
        notRandomButton.addActionListener(new NotRandomButtonListener());

        // TODO: 19.05.16 I was creating NorthPanel ... with some functions to add life
        this.add(jTextField);
        this.add(randomButton);
        this.add(notRandomButton);
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

            if(krok < 3 || krok >50)
                krok = 3;


            for (int i = krok; i < 50; i=i+krok) {
                for (int j = krok; j < 50 ; j=j+krok) {
                    boardPanel.addLife(i,j);
                }
            }
        }
    }
}
