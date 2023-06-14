package Object.Shape;

import Object.Port.Port;

import java.awt.*;

public class GroupShape extends MyShape{
    private MyShape[] _groupedShapes;
    private final int offset = 10;
    public GroupShape(MyShape[] shapes, int depthCounter) {
        // add shapes in the local MyShape Array
        this._groupedShapes = shapes;
        super._depth = depthCounter;

        // initial the Shapes in the GroupShape
        this.addInGroup();

        // calculate the group shape boundary rectangle using corner
        int[] minMaxBoundaries = calculateBoundary();// minX, minY, maxX, maxY (top-left and bottom-right corner)
        super._corners[0] = new Point(minMaxBoundaries[0], minMaxBoundaries[1]);
        super._corners[1] = new Point(minMaxBoundaries[2], minMaxBoundaries[1]);
        super._corners[2] = new Point(minMaxBoundaries[0], minMaxBoundaries[3]);
        super._corners[3] = new Point(minMaxBoundaries[2], minMaxBoundaries[3]);

        super._width = minMaxBoundaries[2] - minMaxBoundaries[0];
        super._height = minMaxBoundaries[3] - minMaxBoundaries[1];
    }

    private int[] calculateBoundary() {
        int[] minMaxBoundaries = new int[]{9999, 9999, 0, 0};
        for(MyShape ms: _groupedShapes) {
            int[] boundaries = ms.getBoundaries();
            for(int i = 0; i < 4; ++i) {
                if(i < 2)
                    minMaxBoundaries[i] = Math.min(minMaxBoundaries[i], boundaries[i]);
                else
                    minMaxBoundaries[i] = Math.max(minMaxBoundaries[i], boundaries[i]);
            }
        }

        minMaxBoundaries[0] -= offset;
        minMaxBoundaries[1] -= offset;
        minMaxBoundaries[2] += offset;
        minMaxBoundaries[3] += offset;

        for(int i = 0; i < 4; ++i)
            System.out.print(minMaxBoundaries[i] + " ");
        System.out.println();

        return minMaxBoundaries;
    }

    private void addInGroup() {
        // set the shapes in the GroupShape with isSelected = false and isGrouped = true
        for(MyShape ms: this._groupedShapes) {
            ms.setSelected(false);
            ms.setGrouped(true);
        }
    }

    public boolean removeFromGroup() {
        // set the shapes in the GroupShape with isGrouped = false
        // return true refers to a GroupShape, return false refers to not a GroupShape
        for(MyShape ms: _groupedShapes) {
            ms.setGrouped(false);
        }
        return true;
    }

    @Override
    public void draw(Graphics g) {
        // draw with all shapes in GroupShape
        for(MyShape ms: _groupedShapes)
            ms.draw(g);

        if(isSelected) {
            // draw selected GroupShape boundary
            g.setColor(Color.RED);
            g.drawRect(super._corners[0].x, super._corners[0].y, super._width, super._height);
        } else {
            // draw GroupShape boundary
            g.setColor(Color.black);
            g.drawRect(super._corners[0].x, super._corners[0].y, super._width, super._height);
        }
        g.setColor(Color.black);
    }

    @Override
    public void dragShape(int offsetX, int offsetY) {
        // revise GroupShape boundary
        super.reviseCornerPosition(offsetX, offsetY);
        // drag with all shapes in GroupShape
        for(MyShape ms: _groupedShapes)
            ms.dragShape(offsetX, offsetY);
    }

    @Override // we want the GroupShape cannot be connected with line
    public Port getNearestPort(Point currentPoint) {
        return null;
    }
}
