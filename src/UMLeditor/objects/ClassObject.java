package UMLeditor.objects;

import java.awt.*;
import java.util.ArrayList;

public class ClassObject extends BasicObject{
    public ClassObject(Point p){
        super(p);
        this.width = 100;
        this.height = 100;
        this.setCntPoint();
    }

    @Override
    public void drawObject(Graphics g)
    {
        super.drawObject(g);
        g.drawRect(this.location.x, this.location.y, this.width, this.height);
        for(int i = 1;i < 3;i++)
        {
            g.drawLine(this.location.x, this.location.y + this.height * i / 3, this.location.x + this.width, this.location.y + this.height * i / 3);
        }
    }
}
