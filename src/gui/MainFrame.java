package gui;


import javax.swing.*;
import java.awt.*;

/**
 * Created by pawel on 08.05.16.
 */
public class MainFrame extends JFrame {

    BoardPanel boardPanel;
    SouthPanel southPanel;

    public MainFrame() {
        super("Grain Growth");
        this.setLayout(new BorderLayout());
        boardPanel = new BoardPanel();
        this.add(boardPanel, BorderLayout.CENTER);

        southPanel = new SouthPanel(boardPanel);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame jFrame = new MainFrame();
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationByPlatform(true);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
    }


}
