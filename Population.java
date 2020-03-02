import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Population {
    private List<Chromosome> population;
    private Random rnd = new Random();
    public double bestDistance = Double.POSITIVE_INFINITY;

    public Population(List<Chromosome> population) {
        this.population = population;
        normalizeFitness();
    }
    public Population(int popSize) {
        population = new ArrayList<>();
        for (int i = 0; i < popSize; i++) {
            Chromosome c = new Chromosome();
            population.add(c);
        }
        normalizeFitness();
    }
    public Population() {
        population = new ArrayList<>();
    }

    public void normalizeFitness() {
        for (Chromosome c : population) {
            c.normalizeFitness();
            if(c.getDistance() < bestDistance) { //numpang
                bestDistance = c.getDistance();
            }
        }
    }
    
    public Chromosome naturalSelection() {
        Chromosome c;
        do {
            c = population.get(rnd.nextInt(population.size()));
            // System.out.println("t");
        }
        while(c.getFitness() < rnd.nextDouble()); //accept-reject
        return c;
    }
    
    public void addChromosome(Chromosome c) {
        population.add(c);
    }
    public int getSize() {
        return population.size();
    }
    public void print() {
        System.out.printf("Shortest Distance : %.2f\n",bestDistance);
        for (Chromosome c : population) {
            c.print();System.out.println();
        }
    }
}