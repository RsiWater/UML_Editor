package UMLeditor.structure;

import UMLeditor.objects.BasicObject;

import java.awt.*;

public class ObjectVector {
    public BasicObject obj;
    public String startDir, endDir;
    public ObjectVector(BasicObject obj, String startDir, String endDir)
    {
        this.obj = obj;
        this.startDir = startDir;
        this.endDir = endDir;
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
