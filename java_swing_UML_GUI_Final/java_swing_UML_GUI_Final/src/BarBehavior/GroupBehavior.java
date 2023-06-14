package BarBehavior;

import Object.*;
import Object.Shape.*;

public class GroupBehavior implements IMenuBarBehavior{
    @Override
    public void barActionPerformed(MyGraphic graphicsInCanvas, int depth) {
        // get the selected shape in the canvas
        MyShape[] selectedShape = graphicsInCanvas.getSelectedShapeForBehavior();
        // if selected multi-shapes, add groupShape into canvas
        if(selectedShape != null && selectedShape.length > 1) {
            graphicsInCanvas.addGraphic(new GroupShape(selectedShape, depth));
        }
    }
}
