package UMLeditor.objects;

import java.awt.*;

public class UseCaseObject extends BasicObject{
    public UseCaseObject(Point p){
        super(p);
        this.width = 100;
        this.height = 50;
        this.setCntPoint();
    }

    @Override
    public void drawObject(Graphics g)
    {
        super.drawObject(g);
        g.drawOval(this.location.x, this.location.y, this.width, this.height);
    }
}
