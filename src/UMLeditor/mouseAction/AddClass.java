package UMLeditor.mouseAction;

import UMLeditor.objects.ClassObject;
import UMLeditor.editorFrame.EditorPanel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class AddClass extends MouseAction{
    public AddClass(EditorPanel p){
        super(p);
        this.btnImgUrl = this.btnImgUrl.concat("\\class.png");
        this.defaultTypeName = "CLASS";
    }

    @Override
    public void mouseClicked(MouseEvent e){
        super.mouseClicked(e);
        this.panel.addNewObject(new ClassObject(new Point(e.getX(), e.getY())));
        this.panel.repaint();
//        System.out.println("add class clicked");
    }
}
