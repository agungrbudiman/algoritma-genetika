import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Population {
    private List<Chromosome> population;
    private Random rnd = new Random();
    public double bestDistance = Double.POSITIVE_INFINITY;
    public int idx_bestDistance;

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
        for (int i = 0; i < population.size(); i++) {
            population.get(i).normalizeFitness();
            calcShortest(i);
        }
    }
    private void calcShortest(int i) {
        if(population.get(i).getDistance() < bestDistance) {
            bestDistance = population.get(i).getDistance();
            idx_bestDistance = i;
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
    public Chromosome getChromosome(int i) {
        return population.get(i);
    }
    public Chromosome getBestChromosome() {
        return population.get(idx_bestDistance);
    }
    public int getSize() {
        return population.size();
    }
    public void print() {
        System.out.printf("Shortest Distance: %.2f KM\n",bestDistance);
        // population.get(idx_bestDistance).printKecamatan();System.out.println();
        // for (Chromosome c : population) {
        //     c.print();System.out.println();
        // }
    }
}