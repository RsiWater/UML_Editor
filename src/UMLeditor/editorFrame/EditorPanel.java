package UMLeditor.editorFrame;

import UMLeditor.basicObject.BasicObject;
import UMLeditor.basicObject.ClassObject;
import UMLeditor.structure.ObjectVector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class EditorPanel extends JPanel {
    private final ArrayList<BasicObject> objects = new ArrayList<BasicObject>();
    private BasicObject targetObj = null;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        this.objects.sort(new Comparator<BasicObject>() {
            @Override
            public int compare(BasicObject o1, BasicObject o2) {
                if (o1.getDepth() == o2.getDepth())
                    return 0;
                return o1.getDepth() < o2.getDepth() ? -1 : 1;
            }
        });
        for(BasicObject ele : this.objects)
        {
            ele.drawObject(g);
            if(ele.getCntList().size() > 0)
            {
                for(ObjectVector cntTo : ele.getCntList())
                {
                    Point[] startPnts = {ele.getUpPoint(), ele.getDownPoint(), ele.getLeftPoint(), ele.getRightPoint()},
                            endPnts = {cntTo.obj.getUpPoint(), cntTo.obj.getDownPoint(), cntTo.obj.getLeftPoint(), cntTo.obj.getRightPoint()};
                    Point startP = ObjectVector.judgePoint(startPnts, cntTo.startDir),
                            endP = ObjectVector.judgePoint(endPnts, cntTo.endDir);
                    g.drawLine(startP.x, startP.y, endP.x, endP.y);
                }
            }
        }
    }

    @Override
    public void setBounds(int x, int y, int width, int height)
    {
        super.setBounds(x, y, width, height);
    }

    public void addNewObject(BasicObject b)
    {
        for(BasicObject ele : this.objects)
        {
            ele.setDepth(ele.getDepth() + 1);
        }
        this.objects.add(b);
    }
    public BasicObject selectObject(int x, int y)
    {
        BasicObject target = new ClassObject(new Point(0, 0));
        target.setDepth(999);
        for(BasicObject ele : objects) {
            if (ele.contains(x, y) && ele.getDepth() < target.getDepth()) {
                target = ele;
                target.setDepth(ele.getDepth());
            }
        }
        if(target.getDepth() != 999)
        {
            if(target.getDepth() != 0)
            {
                target.setDepth(0);
                for(BasicObject ele : objects)
                {
                    if(Objects.equals(ele, target))
                        continue;
                    ele.setDepth(ele.getDepth() + 1);
                }
            }
            return target;
        }
        else return null;
    }
    public ArrayList<BasicObject> selectObjectsInArea(Point sp, Point ep)
    {
        ArrayList<BasicObject> rst = new ArrayList<>();
        for(BasicObject ele : objects)
        {
            if(ele.beContained(sp, ep))
                rst.add(ele);
        }
        return rst;
    }
    public ArrayList<BasicObject> getSelectedObject()
    {
        ArrayList<BasicObject> rst = new ArrayList<>();
        for(BasicObject ele : objects)
        {
            if(ele.isBeSelected())
                rst.add(ele);
        }
        return rst;
    }
    public void unselectAllObjects(){
        for(BasicObject ele : this.objects)
        {
            ele.setBeSelected(false);
        }
    }
    public BasicObject getTargetObj() {
        return targetObj;
    }
    public void setTargetObj(BasicObject targetObj) {
        this.targetObj = targetObj;
    }
}