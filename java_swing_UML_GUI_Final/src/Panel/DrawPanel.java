package Panel;

import Panel.CustomStuff.*;
import Object.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, Observer {
    private UpperMenuBar _UMB; // Panel.Observer pattern's Panel.Subject
    private SideButtonPanel _BP; // Panel.Observer pattern's Panel.Subject
    private MyGraphic _graphicsInCanvas; // Singleton Pattern
    private CustomButton _currentButton = null;
    private CustomMenuItem _currentMenuItemBehavior = null;
    private int _depthCounter = 0;

    public DrawPanel(UpperMenuBar upperMenuBar, SideButtonPanel sideButtonPanel , MyGraphic SingletonGraphics) {
        this.setBackground(Color.lightGray);
        // add listener
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        // initial upperMenuBar, sideButtonPanel and all graphics in the canvas
        this._UMB = upperMenuBar;
        this._BP = sideButtonPanel;
        this._graphicsInCanvas = SingletonGraphics;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // call the all graphics in the canvas to draw
        _graphicsInCanvas.draw(g);
    }

    @Override
    public void updateButtonState() {
        // update the current button Mode
        _currentButton = _BP.getNowButtonMode();
    }

    @Override
    public void updateMenuBarState() {
        // update the current menu item behavior
        _currentMenuItemBehavior = _UMB.getNowMenuBarBehavior();
        // strategy pattern
        // 像是每個menu item會做不同的behavior，經由最底層的interface去連接到客製化的behavior
        // 每個behavior都要實作內容
        _currentMenuItemBehavior.getBehavior().barActionPerformed(_graphicsInCanvas, _depthCounter);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Click");
        // get the current mouse click point
        Point currentPoint = e.getPoint();

        // check the currentButton is not empty
        if(_currentButton != null) {
            // get the currentButton mode and use its click action
            _currentButton.getMode().mouseClickedAction(currentPoint, _depthCounter);
        }
         _depthCounter++;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed");
        Point currentPoint = e.getPoint();
        if(_currentButton != null) {
            _currentButton.getMode().mousePressedAction(currentPoint);
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released");
        Point currentPoint = e.getPoint();
        if(_currentButton != null) {
            _currentButton.getMode().mouseReleasedAction(currentPoint);
        }
        repaint();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        // System.out.println("Dragged");
        Point currentPoint = e.getPoint();
        if(_currentButton != null) {
            _currentButton.getMode().mouseDraggedAction(currentPoint);
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
