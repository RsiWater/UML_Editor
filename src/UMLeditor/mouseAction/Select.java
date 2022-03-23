package UMLeditor.mouseAction;

import UMLeditor.objects.BasicObject;
import UMLeditor.editorFrame.EditorPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

public class Select extends MouseAction{
    private final Point startPoint;
    private final Point endPoint;
    private final ArrayList<Point> diffPoints;
    private boolean canDrag = false;

    public Select(EditorPanel p){
        super(p);
        this.startPoint = new Point();
        this.endPoint = new Point();
        this.diffPoints = new ArrayList<>();
        this.btnImgUrl = this.btnImgUrl.concat("\\mouse.png");
        this.defaultTypeName = "SELECT";

    }

    @Override
    public void mouseClicked(MouseEvent e){
        super.mouseClicked(e);
        BasicObject targetObj = this.panel.selectObject(e.getX(), e.getY());
        this.panel.unselectAllObjects();
        if(!Objects.isNull(targetObj))
        {
            targetObj.setBeSelected(true);
            this.panel.setTargetObj(targetObj);
        }
        this.panel.repaint();
        System.out.println("select clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        BasicObject targetObj = this.panel.selectObject(e.getX(), e.getY());

        if(!Objects.isNull(targetObj) && targetObj.isBeSelected())
        {
            this.diffPoints.clear();
            ArrayList<BasicObject> objList = this.panel.getSelectedObject();
            for(BasicObject ele : objList)
            {
                if(Objects.equals(targetObj, ele))
                {
                    this.canDrag = true;
                }
                this.diffPoints.add(new Point(ele.getLocation().x - e.getX(), ele.getLocation().y - e.getY()));
            }
        }
        else
        {
            this.canDrag = false;
            this.panel.unselectAllObjects();
        }
        this.startPoint.setLocation(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if(this.canDrag){
            for(int i = 0;i < this.panel.getSelectedObject().size(); i++)
            {
                BasicObject tarObj = this.panel.getSelectedObject().get(i);
                tarObj.setLocation(e.getX() + diffPoints.get(i).x,e.getY() + diffPoints.get(i).y);
            }
        }
        this.panel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if(!this.canDrag)
        {
            ArrayList<BasicObject> rst;
            this.endPoint.setLocation(e.getX(), e.getY());
            rst = this.panel.selectObjectsInArea(
                    new Point(Math.min(this.startPoint.x, this.endPoint.x), Math.min(this.startPoint.y, this.endPoint.y)),
                    new Point(Math.max(this.startPoint.x, this.endPoint.x), Math.max(this.startPoint.y, this.endPoint.y)));
            for(BasicObject ele : rst)
            {
                ele.setBeSelected(true);
            }
        }
        this.panel.repaint();
    }
}
