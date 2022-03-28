package UMLeditor.structure;

import UMLeditor.mouseAction.Connect;
import UMLeditor.objects.BasicObject;

import java.awt.*;
import java.util.Objects;

public class ObjectVector {
    public BasicObject startObj, endObj;
    public int startDir, endDir;
    //0 = up, 1 = down, 2 = left, 3 = right
    public Connect mouseAction;
    public ObjectVector(BasicObject startObj, BasicObject endObj, int startIdx, int endIdx, Connect cnt)
    {
        this.startObj = startObj;
        this.endObj = endObj;
        this.startDir = startIdx;
        this.endDir = endIdx;
        this.mouseAction = cnt;
    }
}
