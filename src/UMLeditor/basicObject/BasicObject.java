package UMLeditor.basicObject;

import UMLeditor.structure.ObjectVector;

import java.awt.*;
import java.util.ArrayList;

public class BasicObject {
    protected Point location, upPoint, downPoint, leftPoint, rightPoint;
    protected int depth = 0;
    protected int width = 0;
    protected int height = 0;
    protected boolean beSelected = false;
    protected static final int BLACK_POINT_SIDE_WIDTH = 10;
    protected ArrayList<ObjectVector> cntList = new ArrayList<>();

    public BasicObject(){}

    public BasicObject(Point p)
    {
        this.location = p;
        this.setCntPoint();
    }

    public BasicObject(Point p, int w, int h)
    {
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

    public Point getUpPoint() {return upPoint;}

    public Point getDownPoint() {return downPoint;}

    public Point getLeftPoint() {return leftPoint;}

    public Point getRightPoint() {return rightPoint;}
}
