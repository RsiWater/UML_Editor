package UMLeditor.mouseAction;

import UMLeditor.editorFrame.EditorPanel;

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
        System.out.println("association clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }
}
