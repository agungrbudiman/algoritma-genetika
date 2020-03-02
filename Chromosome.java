import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Chromosome {

    private List<Integer> chromosome;
    private double distance, fitness;
    public static double sumFitness;

    public Chromosome(List<Integer> chromosome) {
        this.chromosome = chromosome;
        calcDistance();
        calcFitness();
    }

    public Chromosome() {
        chromosome = new ArrayList<>();
        for (int i = 0; i < Data.size; i++) {
            chromosome.add(i);
        }
        Collections.shuffle(chromosome);
        calcDistance();
        calcFitness();
    }

    public void normalizeFitness() {
        fitness = fitness / sumFitness;
    }

    private void calcDistance() {
        for (int i = 0; i < Data.size-1; i++) {
            distance += Data.distance[chromosome.get(i)][chromosome.get(i+1)];
        }
    }

    private void calcFitness() {
        fitness = 1/(Math.pow(distance,2)+1); //prevent divide by 0
        sumFitness += fitness;
    }

    public double getFitness() {
        return fitness;
    }
    public double getDistance() {
        return distance;
    }
    public void swapGene(int point1, int point2) {
        int temp = chromosome.get(point1);
        chromosome.set(point1, chromosome.get(point2));
        chromosome.set(point2, temp);
        calcDistance();
        calcFitness();
    }
    public void addGene(int val) {
        chromosome.add(val);
    }
    public int getGene(int index) {
        return chromosome.get(index);
    }
    public void print() {
        System.out.print(chromosome);
        System.out.printf(" (%.2f)", distance);
        System.out.printf("(%.5f)", fitness);
    }
    public void printKecamatan() {
        for (int i = 0; i < chromosome.size(); i++) {
            System.out.print(Data.kecamatan[chromosome.get(i)]+"-");
        }
    }
}