package UMLeditor.structure;

import java.awt.*;

public class Calculator {
    static public double distance(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow(p2.x-p1.x, 2) + Math.pow(p2.y-p1.y, 2));
    }
    static public Point rotate(Point baseP, Point tarP, double degree)
    {
        final double COS = Math.cos(Math.toRadians(degree)), SIN = Math.sin(Math.toRadians(degree));
        Point rst = new Point();
        rst.setLocation((int)((tarP.x - baseP.x) * COS + (tarP.y - baseP.y) * -SIN + baseP.x), (int)((tarP.x - baseP.x) * SIN + (tarP.y - baseP.y) * COS + baseP.y));

        return rst;
    }
    static public Point setLength(Point baseP, Point tarP, double length)
    {
        final double D = Calculator.distance(baseP, tarP);
        return new Point((int)((tarP.x - baseP.x) / D * length + baseP.x), (int)((tarP.y - baseP.y) / D * length + baseP.y));
    }
}
