import Panel.*;
import Object.*;
import Object.Shape.GraphicsInCanvas;

import javax.swing.*;
import java.awt.*;

public class UMLEditor extends JFrame {
    public static void main(String[] args) {
        // create UMLEditor object
        UMLEditor frame = new UMLEditor();

        // Create a singleton Object for the all graphics in the canvas
        MyGraphic SingletonGraphics = new GraphicsInCanvas();

        // create SideButtonPanel with all graphics in the canvas
        // because we need it for create line or shape object
        // SideButtonPanel is Observer pattern's Subject
        SideButtonPanel sideButtonPanel = new SideButtonPanel(SingletonGraphics);
        // create Panel.UpperMenuBar with nothing
        // UpperMenuBar is Observer pattern's Subject
        UpperMenuBar upperMenuBar = new UpperMenuBar();
        // create DrawPanel with upperMenuBar, sideButtonPanel and all graphics in the canvas
        // DrawPanel is Observer pattern's Observer
        DrawPanel drawPanel = new DrawPanel(upperMenuBar, sideButtonPanel, SingletonGraphics);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 870, 507);

        // add DrawPanel, SideButtonPanel, UpperMenuBar on the UMLEditor
        frame.setMenuBar(upperMenuBar);
        frame.add(sideButtonPanel, BorderLayout.WEST);
        frame.add(drawPanel, BorderLayout.CENTER);

        // Subject(sideButtonPanel, upperMenuBar) add Observer(drawPanel)
        sideButtonPanel.addObserver(drawPanel);
        upperMenuBar.addObserver(drawPanel);

        // set frame visible
        frame.setVisible(true);
    }
}