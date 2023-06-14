package ButtonMode;

import Object.*;
import Object.Shape.ClassShape;

import java.awt.*;

public class ClassMode extends Mode {
    public ClassMode(MyGraphic gg) {
        super(gg);
    }

    @Override
    public void mouseClickedAction(Point currentPoint, int depthCounter) {
        // clear the selection of all graphics in canvas
        super.graphicsInCanvas.clearSelectedShapes();
        // add the ClassShape into the graphicsInCanvas Shape
        super.graphicsInCanvas.addGraphic(new ClassShape(currentPoint, depthCounter));
    }

    @Override
    public void mouseDraggedAction(Point currentPoint) {}
    @Override
    public void mousePressedAction(Point currentPoint) {
        super.graphicsInCanvas.clearSelectedShapes();
    }
    @Override
    public void mouseReleasedAction(Point currentPoint) {}
}
