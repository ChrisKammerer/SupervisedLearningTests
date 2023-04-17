// Testing of Weka J48 Decision Tree
// Iris Data Set

/*
 * Decision Tree Tester
 * For testing of WEKA's J48 Decision Tree Implementation
 * Mr. Michaud
 * www.nebomusic.net
 */

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;

public class TreeTest_J48 {

    public void testTreeJ48(String trainingPath, String testingPath, int classIndex) {

        Instances data;
        Instances dataTest;

        try {

            System.out.println("Building J48 Tree");
            // Training Set
            DataSource source = new DataSource(trainingPath);
            data = source.getDataSet();
            data.setClassIndex(classIndex);

            // Testing Set
            DataSource sourceTest = new DataSource(testingPath);
            dataTest = sourceTest.getDataSet();
            dataTest.setClassIndex(classIndex);

            // Build the J48 Tree and Train with Training Data
            J48 cls = new J48();
            cls.buildClassifier(data);

            // Evaluate Tree - Training Set
            System.out.println("Evaluating Tree for Training Set:");
            Evaluation eval = new Evaluation(data);
            eval.evaluateModel(cls, data);
            System.out.println("Error rate Training Set: " + eval.errorRate());
            System.out.println(eval.toSummaryString());
            double[][] confusionMatrix = eval.confusionMatrix();
            String out = getMatrixString(confusionMatrix);
            System.out.println("Confusion Matrix Training Set:");
            System.out.println(out);

            // Evaluate Tree - Testing Set
            System.out.println("Evaluating Tree for Testing Set:");
            eval = new Evaluation(dataTest);
            eval.evaluateModel(cls, dataTest);
            System.out.println("Error rate Testing Set: " + eval.errorRate());
            System.out.println(eval.toSummaryString());
            confusionMatrix = eval.confusionMatrix();
            System.out.println("Confusion Matrix Testing Set:");
            out = getMatrixString(confusionMatrix);
            System.out.println(out);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getMatrixString(double[][] m) {
        String out = "";

        for (double[] doubles : m) {
            StringBuilder line = new StringBuilder();
            for (int c = 0; c < m[0].length; c++) {
                line.append((int) doubles[c]).append(" ");
            }
            line.append("\n");
            out += line;
        }

        return out;
    }

    public Instances getData(String dataPath, int classIndex) {
        Instances data = null;

        try {
            System.out.println("Getting Data Instances");
            // Build Instances from dataPath
            DataSource source = new DataSource(dataPath);
            data = source.getDataSet();
            data.setClassIndex(classIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public J48 getJ48Tree(String dataPath, int classIndex) {
        Instances data;
        J48 cls = null;
        try {
            System.out.println("Building J48 Tree");
            // Get Data Set from Path
            DataSource source = new DataSource(dataPath);
            data = source.getDataSet();
            data.setClassIndex(classIndex);

            // Build Tree
            cls = new J48();
            cls.buildClassifier(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cls;
    }

    public static void showTree(J48 cls) {

        try {
            String graphDescriptor = cls.graph();

            final javax.swing.JFrame jf =
                    new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");

            jf.setSize(900, 500);
            jf.getContentPane().setLayout(new BorderLayout());

            TreeVisualizer tv = new TreeVisualizer(null, graphDescriptor, new PlaceNode2());

            jf.getContentPane().add(tv, BorderLayout.CENTER);

            jf.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    jf.dispose();
                }
            });

            jf.setVisible(true);
            tv.fitToScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

        // Create Instance and Test
        TreeTest_J48 decisionTree = new TreeTest_J48();


        // Define Paths for Training and Testing Data
//        String trainingPath = "iris_data/iris_train.csv";
//        String testingPath = "iris_data/iris_test.csv";
//        int classIndex = 4;
        //output 0.0 = setosa, 1.0 = versicolor, 2.0 = virginica



//        String trainingPath = "diabetes_data/diabetes_data_train.csv";
//        String testingPath = "diabetes_data/diabetes_data_test.csv";
//        int classIndex = 16;

        String trainingPath = "raisin_data/Raisin_Train.csv";
        String testingPath = "raisin_data/Raisin_Test.csv";
        int classIndex = 7;
        // Run Tree Build and Test
        decisionTree.testTreeJ48(trainingPath, testingPath, classIndex);

        // Get Instances for Data
        Instances data = decisionTree.getData(testingPath, classIndex);
        Instance sample = data.instance(11);

        // Get J48 Tree
        J48 treeClassifier = decisionTree.getJ48Tree(trainingPath, classIndex);

        // Test Classification for one instance
        // Instance 0

        try {
            double type = treeClassifier.classifyInstance(sample);
            double size = treeClassifier.measureTreeSize();

            System.out.println(type);
            System.out.println(size);
            showTree(treeClassifier);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}