package BarBehavior;

import Object.*;
import Object.Shape.*;
import javax.swing.*;

public class ChangShapeNameBehavior implements IMenuBarBehavior {
    @Override
    public void barActionPerformed(MyGraphic graphicsInCanvas, int depth) {
        // if the action has being active, create a ChangeObjectName frame
        JFrame f = new JFrame("Change Object Name.");
        // set input pane named Change Object Name
        String input = JOptionPane.showInputDialog("Change Object Name");
        if(input != null)
        {
            MyShape[] needToChangeNameShape = graphicsInCanvas.getSelectedShapeForBehavior();
            if(needToChangeNameShape != null && needToChangeNameShape.length == 1) {
                needToChangeNameShape[0].setName(input);
            }
        }
    }
}
