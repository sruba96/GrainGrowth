package gui;

import model.StructureType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pawel on 08.05.16.
 */
public class SouthPanel extends JPanel {

    private static int selectedStructureIndex = 0;
    BoardPanel boardPanel;


    public SouthPanel(BoardPanel boardPanel) {
        super(new FlowLayout());
        this.boardPanel = boardPanel;

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new StartButtonListener());

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new StopButtonListener());

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearButtonListener());

        JCheckBox checkBox = new JCheckBox("Periodic");
        checkBox.addActionListener(new CheckBoxListener(checkBox));

        JComboBox choiceComboBox = new JComboBox(StructureType.values());
        choiceComboBox.addActionListener(e -> {
            boardPanel.setSelectedIndex(choiceComboBox.getSelectedIndex());
        });

        this.add(startButton);
        this.add(stopButton);
        this.add(clearButton);
        this.add(checkBox);
        this.add(choiceComboBox);
    }

    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!boardPanel.timer.isRunning())
                boardPanel.timer.start();
        }
    }

    private class StopButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (boardPanel.timer.isRunning())
                boardPanel.timer.stop();
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boardPanel.clearAll();
        }
    }

    private class CheckBoxListener implements ActionListener {

        JCheckBox checkBox;

        public CheckBoxListener(JCheckBox checkBox) {
            this.checkBox = checkBox;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (checkBox.isSelected())
                boardPanel.setPeriodic(true);
            else
                boardPanel.setPeriodic(false);

        }
    }
}
