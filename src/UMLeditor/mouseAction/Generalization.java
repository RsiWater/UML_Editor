package UMLeditor.mouseAction;

import UMLeditor.editorFrame.EditorPanel;

import java.awt.event.MouseEvent;

public class Generalization extends Connect{
    public Generalization(EditorPanel p){
        super(p);
        this.btnImgUrl = this.btnImgUrl.concat("\\generalization.png");
        this.defaultTypeName = "GENERALIZATION";
    }

    @Override
    public void mouseClicked(MouseEvent e){
        super.mouseClicked(e);
        System.out.println("generalization clicked");
    }
}
