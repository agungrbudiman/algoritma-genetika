import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Chromosome {

    private List<Integer> chromosome;
    private double distance, fitness;

    public Chromosome() {
        chromosome = new ArrayList<>();
        distance = 0;
    }

    public void initChromosome() {
        for (int i = 0; i < Data.size; i++) {
            chromosome.add(i);
        }
        Collections.shuffle(chromosome);
        calcDistance();
        calcFitness();
    }

    public void calcDistance() {
        for (int i = 0; i < Data.size-1; i++) {
            distance += Data.distance[chromosome.get(i)][chromosome.get(i+1)];
        }
    }

    public void calcFitness() {
        fitness = 1/(Math.pow(distance,8)+1); //prevent divide by 0
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void print() {
        System.out.print(chromosome);
        System.out.printf(" (%.2f) ", distance);
        System.out.printf(" (%.5f)", fitness);
    }

    public double getDistance() {
        return distance;
    }
    public void addGene(int val) {
        chromosome.add(val);
    }
    public int getGene(int index) {
        return chromosome.get(index);
    }
}