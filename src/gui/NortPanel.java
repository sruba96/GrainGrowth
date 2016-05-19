package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pawel on 19.05.16.
 */
public class NortPanel extends JPanel {

    BoardPanel boardPanel;

    public NortPanel(BoardPanel boardPanel) {
        super(new FlowLayout());
        this.boardPanel = boardPanel;

        JButton randomButton = new JButton("Start");
        randomButton.addActionListener(new RandomButtonListener());

        // TODO: 19.05.16 I was creating NorthPanel ... with some functions to add life 

        this.add(randomButton);
    }

    private class RandomButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!boardPanel.timer.isRunning())
                boardPanel.timer.start();
        }
    }
}
