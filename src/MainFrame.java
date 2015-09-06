import components.CardioPanel;
import components.CirclePanel;
import components.DrawingPanel;
import components.MouseCoordsPanel;

import javax.swing.*;

/**
 * Created by kamil on 31/08/15.
 * Main frame of application
 */
public class MainFrame extends JFrame{
    private DrawingPanel drawPanel; // panel for drawing

    public MainFrame(DrawingPanel panel){
        super("Week3 - " + panel.getClass().toString());

        drawPanel = panel;
        add(drawPanel);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        //setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args){
        new MainFrame(new CirclePanel());
        new MainFrame(new CardioPanel());
        new MainFrame(new MouseCoordsPanel());
    }
}
