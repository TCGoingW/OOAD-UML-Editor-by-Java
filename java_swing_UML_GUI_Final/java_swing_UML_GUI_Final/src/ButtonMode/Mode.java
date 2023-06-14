package ButtonMode;

import Object.*;
import Object.Shape.GraphicsInCanvas;

import java.awt.*;


public abstract class Mode {
    // every mode have a same graphicsInCanvas
    // only one and unique
    protected GraphicsInCanvas graphicsInCanvas;

    public Mode(MyGraphic gg) {
        this.graphicsInCanvas = (GraphicsInCanvas) gg;
    }

    public abstract void mouseClickedAction(Point currentPoint, int depthCounter);
    public abstract void mouseDraggedAction(Point currentPoint);
    public abstract void mousePressedAction(Point currentPoint);
    public abstract void mouseReleasedAction(Point currentPoint);
}
