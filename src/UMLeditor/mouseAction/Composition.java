package UMLeditor.mouseAction;

import UMLeditor.editorFrame.EditorPanel;
import UMLeditor.structure.Calculator;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Composition extends Connect{
    public Composition(EditorPanel p){
        super(p);
        this.btnImgUrl = this.btnImgUrl.concat("\\composition.png");
        this.defaultTypeName = "COMPOSITION";
    }

    @Override
    public void mouseClicked(MouseEvent e){
        super.mouseClicked(e);
        System.out.println("composition clicked");
    }

    @Override
    public void drawConnect(Point sp, Point ep, Graphics g) {
//        super.drawConnect(sp, ep, g);
        final double DEGREE = 45.0, LENGTH = 15;
        Point newP1, newP2, newP3;

        newP1 = Calculator.rotate(ep, sp, DEGREE);
        newP1 = Calculator.setLength(ep, newP1, LENGTH);
        g.drawLine(newP1.x, newP1.y, ep.x, ep.y);
        newP2 = Calculator.rotate(ep, sp, -DEGREE);
        newP2 = Calculator.setLength(ep, newP2, LENGTH);
        g.drawLine(newP2.x, newP2.y, ep.x, ep.y);
        newP3 = Calculator.rotate(ep, newP1, -DEGREE);
        newP3 = Calculator.setLength(ep, newP3, Calculator.distance(ep, newP3) * Math.cos(Math.toRadians(-DEGREE)) * 2);
        g.drawLine(newP1.x, newP1.y, newP3.x, newP3.y);
        g.drawLine(newP2.x, newP2.y, newP3.x, newP3.y);
        g.drawLine(sp.x, sp.y, newP3.x, newP3.y);
    }
}
