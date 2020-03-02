public class Main {
    public static void main(String []args) {
        Data.loadData();
        Generation.start(10);
        Generation.nextGeneration();
        // Generation.getPopulation(0).print();
    }
}