import java.util.*;


public class Attribute {
    private ArrayList<String> fields;

    /*constructors*/
    public Attribute(ArrayList<String> input){
        fields = input;
    }

    public Attribute(Attribute a)
    {
        fields = a.getCopyFields();
    }

    /*methods*/
    public ArrayList<String> getFields(){return fields;}

    public ArrayList<String> getCopyFields(){return (ArrayList<String>) fields.clone();}

    public String toString(){return fields.toString();}

    public void removeAttributeAtIndex(int i){fields.remove(i);}
}
