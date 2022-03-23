package UMLeditor.structure;

import UMLeditor.objects.BasicObject;

import java.awt.*;

public class ObjectVector {
    public BasicObject startObj, endObj;
    public int startDir, endDir;
    //0 = up, 1 = down, 2 = left, 3 = right
    public ObjectVector(BasicObject startObj, BasicObject endObj, int startIdx, int endIdx)
    {
        this.startObj = startObj;
        this.endObj = endObj;
        this.startDir = startIdx;
        this.endDir = endIdx;
    }

    static public Point judgePoint(Point[] pnts, String dir)
    {
        return switch (dir) {
            case "up" -> pnts[0];
            case "down" -> pnts[1];
            case "left" -> pnts[2];
            case "right" -> pnts[3];
            default -> null;
        };
    }
}
