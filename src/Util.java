import java.util.ArrayList;

public class Util {
    public double getRemainder(DataSet data, String attr){
        double remainder = 0;
        String pos = "Yes"; // positive answer
        ArrayList<String> d = new ArrayList<>(); // unique possibilities of attribute
        ArrayList<Double> n = new ArrayList<>(); // number of each d
        ArrayList<Double> pk = new ArrayList<>(); // number of d associated with yes
        ArrayList<Record> r = data.getData();
        int index = data.getAttributeList().indexOf(attr);

        //populate d
        for(Record record : r){
            String a = record.getAttributeAtIndex(index);
            if(!d.contains(a)) d.add(a);
        }
        //populate n and pk
        for(String str : d){
            double nCount = 0;
            double pkCount = 0;
            for(Record record : r){
                String a = record.getAttributeAtIndex(index);
                if(a.equals(str)) nCount++;
                if(record.getClassification().equals("Yes")&&a.equals(str)) pkCount++;
            }
            n.add(nCount);
            pk.add(pkCount);
        }
        //sum
        for(int i=0; i<d.size(); i++){
            double PKNK = n.get(i);
            double PN = r.size();
            double PK = pk.get(i);
            remainder += (PKNK/PN) * getB(PK/PKNK);
//            remainder += (n.get(i)/r.size()) * getB((pk.get(i)/n.get(i)));
        }
        return remainder;
    }

    public double getGain(DataSet data, String attr){
        ArrayList<Record> r = data.getData();
        double p = 0;
        for(Record record : r){
            if(record.getClassification().equals("Yes")) p++;
        }
        double gain = getB(p/(r.size()));

        return gain - getRemainder(data, attr);
    }

    public String getImportance(Attribute attributes, DataSet data){
        String attrImportant="";
        double gain = 0;
        for(String a : attributes.getFields()){
            double g = getGain(data, a);
            if(g>gain){
                gain = g;
                attrImportant = a;
            }
        }
        return attrImportant;
    }

    public static String getPluralityValue(DataSet data){
        int yes = 0;
        int no = 0;
        for(Record r : data.getData()){
            if(r.getClassification().equals("Yes")) yes++;
            else no++;
        }
        if(yes>=no) return "Yes";
        else return "No";
    }

    public double getB(double q) {
        double b = 0;
        b = -(q*(Math.log(q)/Math.log(2)) + (1-q)*(Math.log(1-q)/Math.log(2)));
        if(Double.isNaN(b)) b=0;
        return b;
    }

    public static ArrayList<String> getAttributeChoices(DataSet data, String attr){
        ArrayList<String> out = new ArrayList<>();
        int index = data.getAttributeList().indexOf(attr);
        for(Record record : data.getData()){
            String a = record.getAttributeAtIndex(index);
            if(!out.contains(a)) out.add(a);
        }
        return out;
    }

    public static boolean allSameClassification(DataSet data){
        String classification = data.getData().get(0).classification;
        for(Record r : data.getData()){
            if(!r.classification.equals(classification)) return false;
        }
        return true;
    }

    public static Node DecisionTreeLearning(DataSet examples, Attribute attributes, DataSet parent){
        if(examples.getData().isEmpty()) return new Node(getPluralityValue(parent));
        else if(allSameClassification(examples)) return null;
        return null;
    }
}

