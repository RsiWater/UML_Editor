package UMLeditor.objects.composite;

import UMLeditor.objects.basic.BasicObject;

import java.util.ArrayList;

public class Composite {
    private ArrayList<BasicObject> objects;
    public Composite()
    {
        objects = new ArrayList<>();
    }
    public Composite(ArrayList<BasicObject> obj)
    {
        this.objects = obj;
    }

    public ArrayList<BasicObject> getAllObjects() {
        return objects;
    }

    public void groups(ArrayList<BasicObject> objects) {
        this.objects = objects;
    }

    public void addObject(BasicObject obj)
    {
        this.objects.add(obj);
    }
}
