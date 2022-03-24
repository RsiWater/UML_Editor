package UMLeditor.editorFrame;

import UMLeditor.objects.BasicObject;
import UMLeditor.objects.ClassObject;
import UMLeditor.objects.Composite;
import UMLeditor.structure.ObjectVector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class EditorPanel extends JPanel {
    private final ArrayList<BasicObject> objects = new ArrayList<BasicObject>();

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
                for(ObjectVector objVec : ele.getCntList())
                {
                    Point startP = objVec.startObj.getCntPoint().get(objVec.startDir),
                            endP = objVec.endObj.getCntPoint().get(objVec.endDir);
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
    public void editBehavior(int behaviorCode){
        ArrayList<BasicObject> objList = this.getSelectedObject();
        switch (behaviorCode)
        {
            case 0:
                if(objList.size() > 1)
                {
                    for(BasicObject ele : objList)
                    {
                        this.objects.remove(ele);
                    }
                    Composite newObj = new Composite(objList);
                    this.objects.add(newObj);
                }
                break;
            case 1:
                if(objList.size() == 1)
                {
                    ArrayList<BasicObject> childrenOfObj = objList.get(0).getChildren();
                    if(!Objects.isNull(childrenOfObj))
                    {
                        this.objects.addAll(childrenOfObj);
                        this.objects.remove(objList.get(0));
                    }
                }
                break;
            default:
                break;
        }
        this.repaint();
    }
}
