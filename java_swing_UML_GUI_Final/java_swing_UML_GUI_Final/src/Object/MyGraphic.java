package Object;

import Object.Port.Port;
import Object.Shape.MyShape;

import java.awt.*;

public abstract class MyGraphic {
    // for all Shape
    /* composite pattern
     *  用小零件組合成較大的群組物件，又能夠將群組物件拼出更大的圖形 也就是Graphics in Canvas
     *  所以把method percolating up -> 主要就是draw method的部分
     *  他強調能在群組物件內用ArrayList維護 呼叫小物件內的draw method
     * */
    public abstract void draw(Graphics g);

    // GroupInCanvas methods
    public void clearSelectedShapes(){}
    public void addGraphic(MyGraphic myGraphic) {}
    public void removeGraphic(MyGraphic myGraphic) {}
    public MyShape getShapeUnderTheMouse(Point currentPoint) { return null; }
    public void setShapeIsSelectedUnderTheArea(Point areaTopLeftPoint, Point areaBottomRightPoint) {}
    public MyGraphic[] isStandardLineAndGetShapes(Point lastPressedPoint, Point currentPoint) { return null; }
    public MyShape[] getSelectedShapeForBehavior() { return null; }


    /*
    *  單純只是希望能用MyGraphic物件去使用所有Shape物件
    *  達到多型目的 -> 使用最基礎型別物件做所有操控，而不是用不同物件各別操作
    * */
    // MyShape methods
    public boolean isShapeUnderTheMouse(Point currentPoint) { return false; }
    public boolean isShapeUnderTheDragArea(Point areaTopLeftPoint, Point areaBottomRightPoint) { return false; }
    public Port getNearestPort(Point currentPoint){ return null; }
    public int[] getBoundaries() { return null; }
    public void setSelected(boolean b) {}
    public void setGrouped(boolean b) {}
    public boolean getGrouped() { return false; }
    public boolean getSelected() { return false; }
    public void setName(String name) {}
    public int getDepth() { return 0; }

    // MyShape and GroupShape methods
    public boolean removeFromGroup() { return false; }
    public void dragShape(int OffsetX, int OffsetY) {}

}
