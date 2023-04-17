import java.io.FileNotFoundException;

public class TreeWorkspace {

    public static void main(String[] args) throws FileNotFoundException {
        DataSet data = new DataSet("restaurant_data/Restaurant_Data.csv", 1);
        System.out.println(data);
    }
}
