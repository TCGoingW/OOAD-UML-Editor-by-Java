package Object.Line;

import Object.*;
import Object.Port.Port;

import java.awt.*;

public class MyLine extends MyGraphic {
    protected final int arrowBarb = 20;
    protected final double arrowPy = Math.PI / 6;
    protected Port _startPort;
    protected Port _endPort;

    public MyLine(Port startPort, Port endPort) {
        this._startPort = startPort;
        this._endPort = endPort;
    }
    @Override
    public void draw(Graphics g) {}
}
