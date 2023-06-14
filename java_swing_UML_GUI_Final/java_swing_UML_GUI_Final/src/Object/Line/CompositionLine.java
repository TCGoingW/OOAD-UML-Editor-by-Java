package Object.Line;

import Object.Port.Port;

import java.awt.*;
import java.awt.geom.Line2D;

public class CompositionLine extends MyLine {
    public CompositionLine(Port startPort, Port endPort) {
        super(startPort, endPort);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.BLACK);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawLine(super._startPort.getPosition().x , super._startPort.getPosition().y,
                super._endPort.getPosition().x , super._endPort.getPosition().y);
        double theta = Math.atan2((super._endPort.getPosition().y  - super._startPort.getPosition().y ),
                (super._endPort.getPosition().x  - super._startPort.getPosition().x ));
        drawArrow(g2, theta, super._endPort.getPosition().x, super._endPort.getPosition().y );
    }

    private void drawArrow(Graphics2D g2, double theta, double x0, double y0) {
        double x1 = x0 - super.arrowBarb * Math.cos(theta + super.arrowPy);
        double y1 = y0 - super.arrowBarb * Math.sin(theta + super.arrowPy);
        g2.draw(new Line2D.Double(x0, y0, x1, y1));
        double x2 = x0 - super.arrowBarb * Math.cos(theta - super.arrowPy);
        double y2 = y0 - super.arrowBarb * Math.sin(theta - super.arrowPy);
        g2.draw(new Line2D.Double(x0, y0, x2, y2));

        double middle_x = (x1+x2)/2;
        double middle_y = (y1+y2)/2;

        double x3 = x0 + 2*(middle_x-x0);
        double y3 = y0 + 2*(middle_y-y0);

        g2.draw(new Line2D.Double(x1, y1, x3, y3));
        g2.draw(new Line2D.Double(x2, y2, x3, y3));

        g2.draw(new Line2D.Double(x0, y0, x3, y3)); // draw line
    }
}
