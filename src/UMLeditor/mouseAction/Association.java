package UMLeditor.mouseAction;

import UMLeditor.editorFrame.EditorPanel;
import UMLeditor.structure.Calculator;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Association extends Connect {
    public Association(EditorPanel p){
        super(p);
        this.btnImgUrl = this.btnImgUrl.concat("\\association.png");
        this.defaultTypeName = "ASSOCIATION";
    }

    @Override
    public void mouseClicked(MouseEvent e){
        super.mouseClicked(e);
//        System.out.println("association clicked");
    }

    @Override
    public void drawConnect(Point sp, Point ep, Graphics g) {
        super.drawConnect(sp, ep, g);
        final double DEGREE = 30.0, LENGTH = 30;
        Point newP;

        newP = Calculator.rotate(ep, sp, DEGREE);
        newP = Calculator.setLength(ep, newP, LENGTH);
        g.drawLine(newP.x, newP.y, ep.x, ep.y);
        newP = Calculator.rotate(ep, sp, -DEGREE);
        newP = Calculator.setLength(ep, newP, LENGTH);
        g.drawLine(newP.x, newP.y, ep.x, ep.y);
    }
}
