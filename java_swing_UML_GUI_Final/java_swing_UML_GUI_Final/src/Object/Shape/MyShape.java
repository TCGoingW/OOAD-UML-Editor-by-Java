package Object.Shape;

import Object.*;
import Object.Port.Port;

import java.awt.*;

public class MyShape extends MyGraphic {
    protected int _depth;
    protected String _name = "ObjectName";
    protected Point[] _corners = new Point[4];
    protected Port[] _ports = new Port[4];
    protected int _height, _width;
    protected boolean isSelected = false, isGrouped = false;

    public MyShape(){}

    public MyShape(Point p, int depthCounter) {
        this._depth = depthCounter;
    }
    @Override
    public void draw(Graphics g) {}

    @Override
    public void dragShape(int offsetX, int offsetY) {
        reviseCornerPosition(offsetX, offsetY);

        for(Port p: _ports) {
            p.revisePortPosition(p.getPosition().x += offsetX, p.getPosition().y += offsetY);
        }
    }

    protected void reviseCornerPosition(int offsetX, int offsetY) {
        for(Point p: _corners) {
            p.x += offsetX;
            p.y += offsetY;
        }
    }

    @Override
    public boolean isShapeUnderTheMouse(Point currentPoint) {
        // fot the GraphicsInCanvas
        // check a shape is under the mouse and return boolean

        // if current point is in the boundary of the shape, return true
        // otherwise return false
        int x_axis = currentPoint.x - _corners[0].x;
        if(x_axis < 0 || x_axis > _width)
            return false;
        int y_axis = currentPoint.y - _corners[0].y;
        if(y_axis < 0 || y_axis > _height)
            return false;
        return true;
    }

    @Override
    public boolean isShapeUnderTheDragArea(Point areaTopLeftPoint, Point areaBottomRightPoint) {
        // for the SelectMode mouseReleased function
        // check the shapes is under the dragged area and return boolean

        // if current point is in the boundary of the dragged area, return true
        // otherwise return false
        if(_corners[0].x >= areaTopLeftPoint.x && _corners[0].y >= areaTopLeftPoint.y &&
                _corners[3].x <= areaBottomRightPoint.x && _corners[3].y <= areaBottomRightPoint.y) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Port getNearestPort(Point currentPoint) {
        // align the port with the nearest shape's port
        double East_dis = calculateDistanceBetweenPoints(_ports[0], currentPoint);
        double South_dis = calculateDistanceBetweenPoints(_ports[1], currentPoint);
        double West_dis = calculateDistanceBetweenPoints(_ports[2], currentPoint);
        double North_dis = calculateDistanceBetweenPoints(_ports[3], currentPoint);

        double minimal;
        minimal = Math.min(East_dis, South_dis);
        minimal = Math.min(minimal, West_dis);
        minimal = Math.min(minimal, North_dis);

        if(minimal == East_dis) {
            return _ports[0];
        } else if(minimal == South_dis) {
            return _ports[1];
        } else if(minimal == West_dis) {
            return _ports[2];
        } else if(minimal == North_dis) {
            return _ports[3];
        }
        return null;
    }

    private double calculateDistanceBetweenPoints(Port port, Point currenPoint)
    {
        double x = Math.pow(currenPoint.x - port.getPosition().x, 2);
        double y = Math.pow(currenPoint.y - port.getPosition().y, 2);
        return Math.sqrt(x+y);
    }

    @Override
    public int[] getBoundaries() {
        // for the GroupShape
        // return minX, minY, maxX, maxY (top-left corner and bottom-right corner)
        return new int[]{ _corners[0].x, _corners[0].y, _corners[3].x, _corners[3].y};
    }

    @Override
    public boolean removeFromGroup() {
        // for the UnGroupBehavior
        // return true refers to a GroupShape, return false refers to not a GroupShape
        return false;
    }

    @Override
    public void setName(String name) { _name = name; }
    @Override
    public void setSelected(boolean b) {
        if(!isGrouped)
            isSelected = b;
    }
    @Override
    public void setGrouped(boolean b) { isGrouped = b; }
    @Override
    public boolean getGrouped() { return isGrouped; }
    @Override
    public boolean getSelected() { return isSelected; }
    @Override
    public int getDepth() { return _depth; }
}
