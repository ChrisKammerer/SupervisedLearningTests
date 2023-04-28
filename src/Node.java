import java.util.ArrayList;

public class Node {
    private String attribute;
    private int attributeIndex;
    private ArrayList<Node> branches = new ArrayList<>();
    private ArrayList<String> values = new ArrayList<>();
    private String output = "";

    public Node(){}

    /**
     * Builds Branch Node
     * @param attribute
     * @param attributeIndex
     */
    public Node(String attribute, int attributeIndex){
        this.attribute = attribute;
        this.attributeIndex = attributeIndex;
    }

    /**
     * Builds Leaf Node
     * @param output
     */
    public Node(String output){
        this.output = output;
    }

    /**
     * Adds new Node to Tree
     * @param node
     * @param value
     */
    public void addBranch(Node node, String value){
        branches.add(node);
        values.add(value);
    }

    /**
     * Recursively inspects tree to classify Attribute
     * @param instance
     * @return classification of instance
     */
    public String classify(Attribute instance){
        if(branches.size()==0){
            return output;
        }
        else{
            String value = instance.getValueAtIndex(attributeIndex);
            int branchIndex = values.indexOf(value);
            return branches.get(branchIndex).classify(instance);
        }
    }
}
