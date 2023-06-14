package Object.Port;

import java.awt.*;

public class Port {
    private Point _position;
    private int offset = 5;

    public Port(int x, int y) {
        this._position = new Point(x, y);
    }

    public void drawPort(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(this._position.x - offset , this._position.y - offset, 10, 10);
    }

    public void revisePortPosition(int newPortX, int newPortY) {
        _position.x = newPortX;
        _position.y = newPortY;
    }

    public Point getPosition() {
        return _position;
    }
}
