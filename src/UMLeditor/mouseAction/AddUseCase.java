package UMLeditor.mouseAction;

import UMLeditor.objects.UseCaseObject;
import UMLeditor.editorFrame.EditorPanel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class AddUseCase extends MouseAction{
    public AddUseCase(EditorPanel p){
        super(p);
        this.btnImgUrl = this.btnImgUrl.concat("\\useCase.png");
        this.defaultTypeName = "USECASE";
    }

    @Override
    public void mouseClicked(MouseEvent e){
        super.mouseClicked(e);
        this.panel.addNewObject(new UseCaseObject(new Point(e.getX(), e.getY())));
        this.panel.repaint();
//        System.out.println("add use case clicked");
    }
}
