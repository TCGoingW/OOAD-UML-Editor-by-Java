package Object.Shape;

import Object.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicsInCanvas extends MyGraphic {
    private java.util.List<MyGraphic> _myGraphics ;

    public GraphicsInCanvas() {
        // create with singleton pattern!
        _myGraphics = Singleton.getInstance().getMyGraphics();
    }

    @Override
    public void draw(Graphics g) {
        // draw with all graphics in the canvas
        for(MyGraphic mg: _myGraphics) {
            mg.draw(g);
        }
    }

    @Override
    public void addGraphic(MyGraphic myGraphic) {
        // add graphics in the canvas
        _myGraphics.add(myGraphic);
        System.out.println("Number of Graphics in Canvas = " + _myGraphics.size());
    }

    @Override
    public void removeGraphic(MyGraphic myGraphic) {
        // remove graphics in the canvas
        _myGraphics.remove(myGraphic);
        System.out.println("number of Graphics in Canvas " +_myGraphics.size());
    }

    @Override
    public void clearSelectedShapes() {
        // clear the selection for all the graphics in the canvas
        if(_myGraphics != null){
            for (MyGraphic mg : _myGraphics) {
                mg.setSelected(false);
            }
        }
    }

    @Override
    public MyShape getShapeUnderTheMouse(Point currentPoint) {
        // for the SelectMode mouseClicked and mousePressed function

        // check the graphics in the canvas is null or not
        if(_myGraphics == null)
            return null;

        MyShape returnShape = null;

        for(MyGraphic mg: _myGraphics) {
            // check the current graphic is under the mouse or not, and current graphic is not part of the group
            // if true, found the returnShape
            if(mg.isShapeUnderTheMouse(currentPoint) && !mg.getGrouped()) {
                if(returnShape == null) {
                    returnShape = (MyShape) mg;
                } else if(returnShape.getDepth() < mg.getDepth()) {
                    // using depth to choose which shape should return
                    returnShape = (MyShape) mg;
                }
            }
        }
        return returnShape;
    }
    @Override
    public void setShapeIsSelectedUnderTheArea(Point areaTopLeftPoint, Point areaBottomRightPoint) {
        // for the SelectMode mouseReleased function

        // set the shape's isSelected to true under the dragged area
        if(_myGraphics != null) {
            for (MyGraphic mg : _myGraphics) {
                if(mg.isShapeUnderTheDragArea(areaTopLeftPoint, areaBottomRightPoint)) {
                    mg.setSelected(true);
                }
            }
        }
    }

    @Override
    public MyGraphic[] isStandardLineAndGetShapes(Point lastPressedPoint, Point currentPoint) {
        // for the all type of Line
        // check two of the shape is legal for generating the line

        // mouse last pressed shape
        MyGraphic mouseLastPressedShapeForLine = null;
        // mouse release shape
        MyGraphic mouseReleaseShapeForLine = null;

        if(_myGraphics != null) {
            for(MyGraphic mg: _myGraphics) {
                // if the graphic is part of the group, change the other graphic
                if(mg.getGrouped())
                    continue;
                // if is the last pressed shape and the mouse is right on the shape
                if(mouseLastPressedShapeForLine == null && mg.isShapeUnderTheMouse(lastPressedPoint))
                    mouseLastPressedShapeForLine = mg;
                // if is the release shape and the mouse is right on the shape
                if(mouseReleaseShapeForLine == null && mg.isShapeUnderTheMouse(currentPoint))
                    mouseReleaseShapeForLine = mg;
                // if the two shape is found, and they are not the same shape, return them!
                if(mouseReleaseShapeForLine != null && mouseLastPressedShapeForLine != null && mouseLastPressedShapeForLine != mouseReleaseShapeForLine)
                    return new MyGraphic[]{mouseLastPressedShapeForLine, mouseReleaseShapeForLine};
            }
        }
        return null;
    }

    @Override
    public MyShape[] getSelectedShapeForBehavior() {
        // for the all Behavior

        // check the graphics in the canvas is null or not
        if(_myGraphics == null)
            return null;

        // for the only shape that needs to change name
        java.util.List<MyShape> selectedShape = new ArrayList<>();

        // add many shape with the selection in the canvas
        for(MyGraphic mg: _myGraphics) {
            if(mg.getSelected()) {
                selectedShape.add((MyShape) mg);
            }
        }

        // check the shape that needs to change name is one and only
        // return it int array type
        if(selectedShape.size() == 0)
            return null;
        else
            return selectedShape.toArray(new MyShape[selectedShape.size()]);
    }
}
