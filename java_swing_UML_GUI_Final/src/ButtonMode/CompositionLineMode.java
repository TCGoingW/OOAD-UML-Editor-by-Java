package ButtonMode;

import Object.*;
import Object.Line.CompositionLine;
import Object.Port.Port;

import java.awt.*;

public class CompositionLineMode extends Mode{
    Point lastPressedPoint = null;

    public CompositionLineMode(MyGraphic gg) {
        super(gg);
    }

    @Override
    public void mouseClickedAction(Point currentPoint, int depthCounter) {}
    @Override
    public void mouseDraggedAction(Point currentPoint) {}

    @Override
    public void mousePressedAction(Point currentPoint) {
        // clear the selection of all graphics in canvas
        super.graphicsInCanvas.clearSelectedShapes();
        // update the last pressed point with current point
        lastPressedPoint = currentPoint;
    }

    @Override
    public void mouseReleasedAction(Point currentPoint) {
        // check if the press and release point is on the two shape
        // when it is true, then return the two shape
        MyGraphic[] twoShapeOfTheLine = super.graphicsInCanvas.isStandardLineAndGetShapes(lastPressedPoint, currentPoint);
        assert twoShapeOfTheLine != null;
        // create startPort, endPort and look for the nearest port in the different shape
        Port startPort = twoShapeOfTheLine[0].getNearestPort(lastPressedPoint);
        Port endPort = twoShapeOfTheLine[1].getNearestPort(currentPoint);
        // when two port aren't null, create line!
        if(startPort != null && endPort != null)
            super.graphicsInCanvas.addGraphic(new CompositionLine(startPort, endPort));
    }
}
