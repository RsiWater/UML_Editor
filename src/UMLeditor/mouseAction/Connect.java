package UMLeditor.mouseAction;

import UMLeditor.objects.BasicObject;
import UMLeditor.editorFrame.EditorPanel;
import UMLeditor.structure.ObjectVector;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Connect extends MouseAction{
    protected BasicObject startObj;
    protected BasicObject endObj;
    protected String startLocation;
    protected String endLocation;

    public Connect(EditorPanel p) {
        super(p);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        this.startObj = this.panel.selectObject(e.getX(), e.getY());
        if(!Objects.isNull(this.startObj) && this.startObj.isCanConnect())
            this.assignLocation(new Point(e.getX(), e.getY()), this.startObj, true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        this.endObj = this.panel.selectObject(e.getX(), e.getY());
        if(!Objects.isNull(this.endObj) && this.endObj.isCanConnect())
            this.assignLocation(new Point(e.getX(), e.getY()), this.endObj, false);
        if(!Objects.isNull(this.startObj) && !Objects.isNull(this.endObj) && this.startObj.isCanConnect() && this.endObj.isCanConnect())
            this.startObj.getCntList().add(new ObjectVector(this.endObj, this.startLocation, this.endLocation));
        this.panel.repaint();
    }

    private double distance(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow(p2.x-p1.x, 2) + Math.pow(p2.y-p1.y, 2));
    }
    private void assignLocation(Point mouseP, BasicObject targetObj, boolean isStart)
    {
        //need rewrite the judge circumstance
        Point[] cntList = {targetObj.getUpPoint(), targetObj.getDownPoint(), targetObj.getLeftPoint(), targetObj.getRightPoint()};
        double minDistance = Double.MAX_VALUE, crtDistance;
        int minIdx = 0;
        String rstDirection;
        for(int i = 0;i < cntList.length;i++)
        {
            crtDistance = this.distance(mouseP, cntList[i]);
            if(minDistance > crtDistance)
            {
                minDistance = crtDistance;
                minIdx = i;
            }
        }
        rstDirection = switch (minIdx) {
            case 0 -> "up";
            case 1 -> "down";
            case 2 -> "left";
            case 3 -> "right";
            default -> null;
        };

        if(isStart) this.startLocation = rstDirection;
        else this.endLocation = rstDirection;
    }
}
