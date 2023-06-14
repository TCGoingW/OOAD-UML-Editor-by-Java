package Object.Shape;

import Object.Port.Port;

import java.awt.*;

public class ClassShape extends MyShape {
    public ClassShape(Point p, int depthCounter) {
        super(p, depthCounter);
        super._width = 90;
        super._height = 90;
        super._corners[0] = new Point(p);
        super._corners[1] = new Point(p.x + _width, p.y);
        super._corners[2] = new Point(p.x, p.y + _height);
        super._corners[3] = new Point(p.x + _width, p.y + _height);

        super._ports[0] = new Port(p.x + _width, p.y + _height / 2);
        super._ports[1] = new Port(p.x + _width / 2, p.y + _height);
        super._ports[2] = new Port(p.x, p.y + _height / 2);
        super._ports[3] = new Port(p.x + _width / 2, p.y);
    }
    @Override
    public void draw(Graphics g) {
        int part = super._height / 3;
        g.setColor(Color.black);
        g.drawRect(super._corners[0].x, super._corners[0].y, super._width, super._height);
        g.drawLine(super._corners[0].x, super._corners[0].y + part, super._corners[0].x + _width, super._corners[0].y + part);
        g.drawLine(super._corners[0].x, super._corners[0].y + part * 2, super._corners[0].x + _width, super._corners[0].y + part * 2);
        g.drawString(super._name, super._corners[0].x + 10, super._corners[0].y + 20);

        if(super.isSelected) {
            for(Port p: super._ports) {
                p.drawPort(g);
            }
        }
    }
}
