package UMLeditor.mouseAction;

import UMLeditor.editorFrame.EditorPanel;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

public class MouseAction implements MouseListener, MouseMotionListener {
    protected String btnImgUrl;
    protected String defaultTypeName = "MouseAction";
    protected int width = 100;
    protected int height = 100;
    protected int mouseX = 0;
    protected int mouseY = 0;
    protected EditorPanel panel = null;

    public MouseAction(EditorPanel p) {
        this.panel = p;
        this.btnImgUrl = new File("").getAbsolutePath().concat("\\src\\img");
    }

    public JButton createBtn(int x, int y) {
        JButton b;
        try{
            b = new JButton(new ImageIcon(ImageIO.read(new File(this.btnImgUrl))));
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
            b = new JButton(this.defaultTypeName);
        }

        b.setBounds(x,y,this.width,this.height);

        return b;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
