package UMLeditor.menuBar;

import UMLeditor.editorFrame.EditorPanel;
import UMLeditor.objects.BasicObject;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class EditMenu extends JMenu {
    private final JMenuItem group, unGroup, chgObjName;
    private final EditorPanel panel;
    public EditMenu(EditorPanel panel)
    {
        super("Edit");
        this.panel = panel;
        this.group = new JMenuItem("Group");
        this.unGroup = new JMenuItem("UnGroup");
        this.chgObjName = new JMenuItem("Change Object Name");

        this.group.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.editBehavior(0);
            }
        });
        this.unGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.editBehavior(1);
            }
        });
        this.chgObjName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(panel.getSelectedObject().size() != 1) return;

                BasicObject target = panel.getSelectedObject().get(0);
                JFrame f = new JFrame("Edit Window");
                JTextField jt = new JTextField(target.getName());
                JLabel label = new JLabel("Object Name: ");
                JButton acceptBtn = new JButton("OK"), refuseBtn = new JButton("Cancel");

                label.setFont(new Font("Serif", Font.PLAIN, 18));
                label.setSize(label.getPreferredSize());
                label.setLocation(50, 50);
                jt.setSize(150, label.getPreferredSize().height);
                jt.setLocation(label.getLocation().x + label.getPreferredSize().width, 50);
                acceptBtn.setSize(acceptBtn.getPreferredSize());
                acceptBtn.setLocation(100, 100);
                acceptBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        target.setName(jt.getText());
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                    }
                });
                refuseBtn.setSize(refuseBtn.getPreferredSize());
                refuseBtn.setLocation(200, 100);
                refuseBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                    }
                });
                f.setSize(400, 200);
                f.add(label);
                f.add(jt);
                f.add(acceptBtn);
                f.add(refuseBtn);
                f.setLayout(null);
                f.setVisible(true);
            }
        });

        this.add(this.group);
        this.add(this.unGroup);
        this.add(this.chgObjName);
    }
}
