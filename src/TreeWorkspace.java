import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


public class TreeWorkspace {

    public static void main(String[] args) throws FileNotFoundException {
        DataSet data = new DataSet("restaurant_data/Restaurant_Data.csv", 10);
        System.out.println(data);
//        ArrayList<String> rootValues = new ArrayList<>(Arrays.asList("None", "Some", "Full"));

        //Leaves
//        Node leaf1 = new Node("No");
//        Node leaf2 = new Node("Yes");
//        Node leaf3 = new Node("No");
//        Node leaf4 = new Node("Yes");
//        Node leaf5 = new Node("No");
//        Node leaf6 = new Node("Yes");
//        Node leaf7 = new Node("No");
//        Node leaf8 = new Node("Yes");
//
//        //Nodes
//        Node node3 = new Node("Fri", 2);
//        node3.addBranch(leaf7, "No");
//        node3.addBranch(leaf8, "Yes");
//
//        Node node2 = new Node("Type", 8);
//        node2.addBranch(leaf4, "Yes");
//        node2.addBranch(leaf5, "No");
//        node2.addBranch(node3, "Fri");
//        node2.addBranch(leaf6, "Yes");
//
//        Node node1 = new Node("Hun", 3);
//        node1.addBranch(leaf3, "No");
//        node1.addBranch(node2, "Type");
//
//        Node root = new Node("Pat", 4);
//        root.addBranch(leaf2, "Yes");
//        root.addBranch(leaf1, "No");
//        root.addBranch(node1, "Hun");

        //Leaves
        Node leaf1 = new Node("No");
        Node leaf2 = new Node("Yes");
        Node leaf3 = new Node("No");
        Node leaf4 = new Node("Yes");
        Node leaf5 = new Node("No");
        Node leaf6 = new Node("Yes");
        Node leaf7 = new Node("No");
        Node leaf8 = new Node("Yes");

        //Nodes
        Node root = new Node("Pat", 4);
        Node node1 = new Node("Hun", 3);
        Node node2 = new Node("Type", 8);
        Node node3 = new Node("Fri", 2);

        //Build Tree
        root.addBranch(leaf1, "None");
        root.addBranch(leaf2, "Some");
        root.addBranch(node1, "Full");

        node1.addBranch(leaf3, "No");
        node1.addBranch(node2, "Yes");

        node2.addBranch(leaf4, "French");
        node2.addBranch(leaf5, "Italian");
        node2.addBranch(node3, "Thai");
        node2.addBranch(leaf6, "Burger");

        node3.addBranch(leaf7, "No");
        node3.addBranch(leaf8, "Yes");

        Record test = data.getDataAtIndex(3);
        System.out.println(data.getAttributeList());
        System.out.println(test.getAttributes());
        System.out.println(root.classify(test.getAttributes()));

        Attribute attr = new Attribute(data.getAttributeListCopy());

        Node tree = Util.DecisionTreeLearning(data, attr, data);

        //data, copy of data's attributes as attribute object, data

//        System.out.println(Util.getAttributeChoices(data, "Pat"));
    }
}
