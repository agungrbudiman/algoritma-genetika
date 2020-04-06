public class Main {
    public static void main(String []args) {
        Data.loadData();
        Generation.run(1000,50,0.1);
    }
}