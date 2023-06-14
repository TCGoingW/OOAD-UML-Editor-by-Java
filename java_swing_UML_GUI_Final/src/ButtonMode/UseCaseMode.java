package ButtonMode;

import Object.*;
import Object.Shape.UseCaseShape;

import java.awt.*;

public class UseCaseMode extends Mode {
    public UseCaseMode(MyGraphic gg) { super(gg); }

    @Override
    public void mouseClickedAction(Point currentPoint, int depthCounter) {
        // clear the selection of all graphics in canvas
        super.graphicsInCanvas.clearSelectedShapes();
        // add the UseCaseShape into the graphicsInCanvas Shape
        super.graphicsInCanvas.addGraphic(new UseCaseShape(currentPoint, depthCounter));
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
