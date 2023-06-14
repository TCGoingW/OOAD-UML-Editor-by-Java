package BarBehavior;
import Object.*;
import Object.Shape.MyShape;

public class UnGroupBehavior implements IMenuBarBehavior {
    @Override
    public void barActionPerformed(MyGraphic graphicsInCanvas, int depth) {
        MyShape[] selectedShape = graphicsInCanvas.getSelectedShapeForBehavior();
        if(selectedShape != null && selectedShape.length == 1) {
            // set the shapes in the GroupShape with isGrouped = false then return true
            if(selectedShape[0].removeFromGroup() == true)
                graphicsInCanvas.removeGraphic(selectedShape[0]);
        }
    }
}
