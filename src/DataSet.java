import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileReader;
import java.util.Scanner;

public class DataSet {
    public ArrayList<Record> data;
    public ArrayList<String> attributeList;

    public DataSet(){}

    //reads file and creates attributes and records.
    public DataSet(String path, int classIndex) throws FileNotFoundException {
        File file = new File(path);
        Scanner s = new Scanner(new FileReader(file));

        attributeList = new ArrayList<>();
        data = new ArrayList<>();

        String[] attributeTitles = s.nextLine().split(",");
        String classification = attributeTitles[classIndex];
        Collections.addAll(attributeList, attributeTitles);


        while(s.hasNextLine()){
            ArrayList<String> a = new ArrayList<>();
            String[] attributes = s.nextLine().split(",");
            String c = attributes[attributes.length-1];
            for(int i=0; i<attributes.length-1; i++) a.add(attributes[i]);
            data.add(new Record(a, c));
        }

    }

    public DataSet(DataSet d){
        this.data = (ArrayList<Record>) d.getData().clone();
        this.attributeList = (ArrayList<String>) d.getAttributeList().clone();
    }

    public void addRecord(Record record){data.add(record);}


    public DataSet removeAttributeAtIndex(int i){
        DataSet d = new DataSet(this);
        for(Record record : d.getData()){
            record.removeAttributeAtIndex(i);
        }
        return d;
    }

    public ArrayList<Record> getData(){return data;}

    public Record getDataAtIndex(int i){return data.get(i);}

    public ArrayList<String> getAttributeList(){return attributeList;}

    public ArrayList<String> getAttributeListCopy(){return (ArrayList<String>) attributeList.clone();}

//    public Record getRecordAtAttribute(String attr){
//        for(Record record : data){
//            if(record.getClassification().equals(attr)) return record;
//        }
//        return null;
//    }

    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append(attributeList).append("\n");
        for(Record record : data){
            output.append(record).append("\n");
        }
        return output.toString();
    }
}
