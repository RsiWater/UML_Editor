package UMLeditor.mouseAction;

import UMLeditor.objects.BasicObject;
import UMLeditor.editorFrame.EditorPanel;
import UMLeditor.structure.Calculator;
import UMLeditor.structure.ObjectVector;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

public class Connect extends MouseAction{
    protected BasicObject startObj;
    protected BasicObject endObj;
    protected Point preLoc;

    public Connect(EditorPanel p) {
        super(p);
        this.preLoc = new Point();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        this.startObj = this.panel.selectObject(e.getX(), e.getY());
        if(!Objects.isNull(this.startObj) && this.startObj.isCanConnect())
        {
            this.preLoc.setLocation(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        int[] cntIdxs = null;
        this.endObj = this.panel.selectObject(e.getX(), e.getY());
        if(!Objects.isNull(this.startObj) && !Objects.isNull(this.endObj) && this.startObj.isCanConnect() && this.endObj.isCanConnect())
        {
            cntIdxs = this.searchCntPoint(this.startObj, this.endObj, this.preLoc, new Point(e.getX(), e.getY()));
            this.startObj.getCntList().add(new ObjectVector(this.startObj, this.endObj, cntIdxs[0], cntIdxs[1], this));
        }
        this.panel.repaint();
    }

    public void drawConnect(Point sp, Point ep, Graphics g)
    {
        g.drawLine(sp.x, sp.y, ep.x, ep.y);
    }
    private int[] searchCntPoint(BasicObject startObj, BasicObject endObj, Point sp, Point ep)
    {
        //need rewrite the judge circumstance
        ArrayList<Point> startCntList = startObj.getCntPoint(), endCntList = endObj.getCntPoint();
        int[] rst = new int[2];
        double minDistance = Double.MAX_VALUE, crtDistance;
        int minIdx = 0;
        for(int i = 0;i < startCntList.size();i++)
        {
            crtDistance = Calculator.distance(sp, startCntList.get(i));
            if(minDistance > crtDistance)
            {
                minDistance = crtDistance;
                minIdx = i;
            }
        }
        rst[0] = minIdx;

        minDistance = Double.MAX_VALUE;
        crtDistance = 0;
        minIdx = 0;
        for(int i = 0;i < endCntList.size();i++)
        {
            crtDistance = Calculator.distance(ep, endCntList.get(i));
            if(minDistance > crtDistance)
            {
                minDistance = crtDistance;
                minIdx = i;
            }
        }
        rst[1] = minIdx;

        return rst;
    }
}
