import java.util.*;
public class Record {
    Attribute attributes;
    String classification;

    public Record(Attribute attributes, String classification){
        this.attributes = attributes;
        this.classification = classification;
    }

    public Record(ArrayList<String> attributes, String classification){
        this.attributes = new Attribute(attributes);
        this.classification = classification;
    }


    public Attribute getAttributes(){return attributes;}

    public String getClassification(){return classification;}

    public String getAttributeAtIndex(int i){return attributes.getFields().get(i);}

    public String toString(){return attributes.toString() + " : " + classification;}

    public void removeAttributeAtIndex(int i){attributes.removeAttributeAtIndex(i);}
}
