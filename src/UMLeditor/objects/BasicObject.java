package UMLeditor.objects;

import UMLeditor.structure.ObjectVector;

import java.awt.*;
import java.util.ArrayList;

public class BasicObject {
    protected Point location, upPoint, downPoint, leftPoint, rightPoint;
    protected int depth = 0;
    protected int width = 0;
    protected int height = 0;
    protected boolean beSelected = false;
    protected boolean canConnect;
    protected static final int BLACK_POINT_SIDE_WIDTH = 10;
    protected ArrayList<ObjectVector> cntList = new ArrayList<>();

    public BasicObject(){
        this.canConnect = true;
    }

    public BasicObject(Point p)
    {
        this.canConnect = true;
        this.location = p;
        this.setCntPoint();
    }

    public BasicObject(Point p, int w, int h)
    {
        this.canConnect = true;
        this.location = p;
        this.width = w;
        this.height = h;
        this.setCntPoint();
    }

    public void drawObject(Graphics g) {
        if(this.beSelected)
        {
            g.fillRect(this.upPoint.x - (BLACK_POINT_SIDE_WIDTH / 2), this.upPoint.y  - (BLACK_POINT_SIDE_WIDTH / 2), BLACK_POINT_SIDE_WIDTH, BLACK_POINT_SIDE_WIDTH);
            g.fillRect(this.downPoint.x - (BLACK_POINT_SIDE_WIDTH / 2),  this.downPoint.y  - (BLACK_POINT_SIDE_WIDTH / 2), BLACK_POINT_SIDE_WIDTH, BLACK_POINT_SIDE_WIDTH);
            g.fillRect(this.leftPoint.x - (BLACK_POINT_SIDE_WIDTH / 2),  this.leftPoint.y  - (BLACK_POINT_SIDE_WIDTH / 2), BLACK_POINT_SIDE_WIDTH, BLACK_POINT_SIDE_WIDTH);
            g.fillRect(this.rightPoint.x - (BLACK_POINT_SIDE_WIDTH / 2),  this.rightPoint.y - (BLACK_POINT_SIDE_WIDTH / 2), BLACK_POINT_SIDE_WIDTH, BLACK_POINT_SIDE_WIDTH);
        }
    }
    public boolean contains(int x,int y)
    {
        return (x > this.location.x && x < this.location.x + this.width && y > this.location.y && y < this.location.y + this.height);
    }
    public boolean beContained(Point sp, Point ep)
    {
        return (sp.x < this.location.x && sp.y < this.location.y && ep.x > this.location.x + this.width && ep.y > this.location.y + this.height);
    }

    public int getDepth() {
        return depth;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
    public void setBeSelected(boolean beSelected) {
        this.beSelected = beSelected;
    }
    public boolean isBeSelected() {return beSelected;}
    public boolean isCanConnect() {return canConnect;}
    public ArrayList<ObjectVector> getCntList() { return cntList; }
    public Point getLocation() {
        return location;
    }
    public void setLocation(int x,int y)
    {
        this.location.x = x;
        this.location.y = y;
        setCntPoint();
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {return height;}
    protected void setCntPoint() //set connect point
    {
        upPoint = new Point(this.location.x + this.width / 2, this.location.y);
        downPoint = new Point(this.location.x + this.width / 2, this.location.y + this.height);
        leftPoint = new Point(this.location.x, this.location.y + this.height / 2);
        rightPoint = new Point(this.location.x + this.width, this.location.y + this.height / 2);
    }
    //up, down, left, right %= 0, 1, 2, 3
    public ArrayList<Point> getCntPoint()
    {
        ArrayList<Point> rst = new ArrayList<Point>();
        rst.add(this.upPoint);
        rst.add(this.downPoint);
        rst.add(this.leftPoint);
        rst.add(this.rightPoint);

        return rst;
    }
    public ArrayList<BasicObject> getChildren(){
        return null;
    }

}
