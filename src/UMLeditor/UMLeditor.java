package UMLeditor;

import UMLeditor.menuBar.FileMenu;
import UMLeditor.objects.BasicObject;
import UMLeditor.editorFrame.EditorPanel;
import UMLeditor.menuBar.EditMenu;
import UMLeditor.mouseAction.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class UMLeditor{
    private final JFrame mainFrame;
    private final EditorPanel mainPanel;
    private MouseAction crtAction;
    private final MouseAction[] btnType;
    private final ArrayList<JButton> btnList = new ArrayList<>();
    private final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 800;

    public UMLeditor(){
        this.mainPanel = new EditorPanel();
        this.btnType = new MouseAction[]{new Select(this.mainPanel), new Association(this.mainPanel), new Generalization(this.mainPanel), new Composition(this.mainPanel), new AddClass(this.mainPanel), new AddUseCase(this.mainPanel)};
        this.mainFrame = new JFrame("UML_Editor");
        this.mainPanel.setBounds(120, 10, 600, 660);
        this.mainPanel.setBackground(Color.gray);
    }

    public void setUpBtn(){
        boolean firstFlag = false;

        for(int i = 0;i < this.btnType.length; i++) {
            MouseAction ele = this.btnType[i];
            JButton b = ele.createBtn(10, 10 + i * (ele.getHeight() + 10));
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(MouseListener ele : mainPanel.getMouseListeners())
                        mainPanel.removeMouseListener(ele);
                    for(MouseMotionListener ele : mainPanel.getMouseMotionListeners())
                        mainPanel.removeMouseMotionListener(ele);
                    crtAction = ele;
                    mainPanel.addMouseListener(crtAction);
                    mainPanel.addMouseMotionListener(crtAction);
                    selectBtn(b);
                }
            });
            b.setBackground(Color.white);
            this.mainFrame.add(b);
            this.btnList.add(b);

            if(!firstFlag) {
                crtAction = ele;
                firstFlag = true;
            }
        }
    }
    public void selectBtn(JButton b)
    {
        for(JButton ele : this.btnList)
        {
            if(Objects.equals(ele, b))
                ele.setBackground(Color.black);
            else
                ele.setBackground(Color.white);
        }
    }
    public void open(){
        this.setUpBtn();

        JMenuBar m = new JMenuBar();
        m.add(new FileMenu(this.mainPanel));
        m.add(new EditMenu(this.mainPanel));

        SwingUtilities.updateComponentTreeUI(this.mainFrame);
        this.mainFrame.add(this.mainPanel);
        this.mainFrame.setJMenuBar(m);
        this.mainFrame.setSize(this.WINDOW_WIDTH, this.WINDOW_HEIGHT);
        this.mainFrame.setLayout(null);
        this.mainFrame.setVisible(true);
    }
}