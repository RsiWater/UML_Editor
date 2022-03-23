package UMLeditor.menuBar;

import UMLeditor.editorFrame.EditorPanel;

import javax.swing.*;

public class FileMenu extends JMenu {
    private final EditorPanel panel;
    public FileMenu(EditorPanel panel)
    {
        super("File");
        this.panel = panel;
    }
}
