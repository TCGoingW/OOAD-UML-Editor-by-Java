package ButtonMode;

import Object.*;
import Object.Shape.MyShape;

import java.awt.*;

public class SelectMode extends Mode {
    // false is for select multiple shape, true is for drag shape
    // make the select multiple shapes flag as default
    private boolean isSelectMultiShapeOrDragShape = false;
    private MyShape beDraggedShape = null;
    private Point lastPressedPoint = null;
    public SelectMode(MyGraphic gg) {
        super(gg);
    }

    @Override
    public void mouseClickedAction(Point currentPoint, int _depthCounter) {
        // clear the selection of all graphics in canvas
        super.graphicsInCanvas.clearSelectedShapes();
        // get the shape which is under the mouse
        MyShape selectedShape = super.graphicsInCanvas.getShapeUnderTheMouse(currentPoint);
        if(selectedShape != null) {
            // make the shape selected (port show)
            selectedShape.setSelected(true);
        }
    }

    @Override
    public void mouseDraggedAction(Point currentPoint) {
        if(isSelectMultiShapeOrDragShape) {
            // drag shape flag

            // calculate with the last pressed point and current point
            int offsetX = currentPoint.x - lastPressedPoint.x;
            int offsetY = currentPoint.y - lastPressedPoint.y;

            // drag shape with offset
            beDraggedShape.dragShape(offsetX, offsetY);

            // update the last pressed point with current point
            lastPressedPoint = currentPoint;
        }
    }

    @Override
    public void mousePressedAction(Point currentPoint) {
        // clear the selection of all graphics in canvas
        super.graphicsInCanvas.clearSelectedShapes();
        // get the shape which is under the mouse
        MyShape selectedShape = super.graphicsInCanvas.getShapeUnderTheMouse(currentPoint);
        if(selectedShape == null) {
            // false is for select multiple shapes
            isSelectMultiShapeOrDragShape = false;
        }else {
            // true is for drag shape
            isSelectMultiShapeOrDragShape = true;
            // let the shape which need to drag be the shape we have selected
            beDraggedShape = selectedShape;
        }
        // update the last pressed point with current point
        lastPressedPoint = currentPoint;
    }

    @Override
    public void mouseReleasedAction(Point currentPoint) {
        if(!isSelectMultiShapeOrDragShape) {
            // select multiple shapes flag
            // calculate the selection area with last pressed point and current point
            Point areaTopLeftPoint = new Point(Math.min(lastPressedPoint.x, currentPoint.x), Math.min(lastPressedPoint.y, currentPoint.y));
            Point areaBottomRightPoint = new Point(Math.max(lastPressedPoint.x, currentPoint.x), Math.max(lastPressedPoint.y, currentPoint.y));
            // set the selection of the shape within the area
            super.graphicsInCanvas.setShapeIsSelectedUnderTheArea(areaTopLeftPoint, areaBottomRightPoint);
        }else {
            // drag shape operation end
            // let the shape which need to drag to null
            beDraggedShape = null;
            // change the drag shape flag to select multiple shapes flag
            // make the select multiple shapes flag as default
            isSelectMultiShapeOrDragShape = false;
        }
    }
}
