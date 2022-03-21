package UMLeditor.mouseAction;

import UMLeditor.editorFrame.EditorPanel;

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
}
