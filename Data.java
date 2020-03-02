import java.util.Scanner;
import java.io.File;

public class Data {
    static int size = 17;
    static String[] kecamatan = new String[size];
    static double[][] distance = new double[size][size];

    public static void loadData() {
        try {
            Scanner scan = new Scanner(new File("data.csv"));
            scan.useDelimiter("\n");
            Data.kecamatan = scan.next().split(";");

            for (int i = 0; i < size; i++) {
                String[] temp = scan.next().split(";");
                for (int j = 0; j < size; j++) {
                    distance[i][j] = Double.parseDouble(temp[j]);
                }
            }

            scan.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}