package UMLeditor.objects;

import UMLeditor.structure.ObjectVector;

import java.awt.*;
import java.util.ArrayList;

public class Composite extends BasicObject{
    private ArrayList<BasicObject> objects;
    public Composite()
    {
        super();
        this.canConnect = false;
        objects = new ArrayList<>();
    }
    public Composite(ArrayList<BasicObject> obj)
    {
        super();
        this.canConnect = false;
        this.objects = obj;
        this.recalculateAttrs();
    }

    public void clear()
    {
        this.objects.clear();
        this.width = 0;
        this.height = 0;
    }

    public void add(BasicObject obj)
    {
        this.objects.add(obj);
        this.recalculateAttrs();
    }

    public void groups(ArrayList<BasicObject> objects)
    {
        this.objects = objects;
        this.recalculateAttrs();
    }

    @Override
    public void drawObject(Graphics g) {
        for(BasicObject ele : this.objects)
        {
            ele.setBeSelected(this.beSelected);
            ele.drawObject(g);
        }
        //for debug
//        g.drawRect(this.location.x, this.location.y, this.width, this.height);
    }
    @Override
    public boolean contains(int x, int y) {
        for(BasicObject ele : this.objects)
        {
            if(ele.contains(x, y))
                return true;
        }
        return false;
    }

    @Override
    public void setLocation(int x, int y) {
        ArrayList<Point> diffPoints = new ArrayList<>();
        for(int i = 0;i < this.objects.size();i++)
        {
            diffPoints.add(new Point(this.objects.get(i).location.x - this.location.x, this.objects.get(i).location.y - this.location.y));
        }
        this.location.x = x;
        this.location.y = y;
        for(int i = 0;i < this.objects.size();i++)
        {
            this.objects.get(i).setLocation(x + diffPoints.get(i).x, y + diffPoints.get(i).y);
        }
    }

    @Override
    public ArrayList<ObjectVector> getCntList() {
        ArrayList<ObjectVector> rst = new ArrayList<>();
        for(BasicObject ele : this.objects)
        {
            rst.addAll(ele.getCntList());
        }
        return rst;
    }

    @Override
    public ArrayList<BasicObject> getChildren() {
        return this.objects;
    }

    private void recalculateAttrs()
    {
        int mostLeftX = Integer.MAX_VALUE, mostRightX = Integer.MIN_VALUE;
        int mostUpY = Integer.MAX_VALUE, mostDownY = Integer.MIN_VALUE;
        for(BasicObject ele : this.objects)
        {
            if(mostLeftX > ele.location.x)
                mostLeftX = ele.location.x;
            if(mostRightX < ele.location.x + ele.width)
                mostRightX = ele.location.x + ele.width;
            if(mostUpY > ele.location.y)
                mostUpY = ele.location.y;
            if(mostDownY < ele.location.y + ele.height)
                mostDownY = ele.location.y + ele.height;
        }
        this.width = mostRightX - mostLeftX;
        this.height = mostDownY - mostUpY;
        this.location = new Point(mostLeftX, mostUpY);
    }
}
