package UMLeditor.menuBar;

import UMLeditor.editorFrame.EditorPanel;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMenu extends JMenu {
    private final JMenuItem group, unGroup;
    private EditorPanel panel;
    public EditMenu(EditorPanel panel)
    {
        super("Edit");
        this.panel = panel;
        this.group = new JMenuItem("Group");
        this.unGroup = new JMenuItem("UnGroup");

        this.group.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.groupObjects();
            }
        });
        this.unGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ungroup");
            }
        });

        this.add(this.group);
        this.add(this.unGroup);
    }
}
