import java.util.List;
import java.util.ArrayList;

public class Generation {
    private static List<Population> generation;
    
    public static void start(int popSize) {
        generation = new ArrayList<>();
        Population p = new Population();
        p.initPopulation(popSize);
        p.normalizeFitness();
        generation.add(p);
    }

    public static void nextGeneration() {
        Chromosome p1 = getPopulation(0).naturalSelection();
        Chromosome p2 = getPopulation(0).naturalSelection();
        Chromosome child = getPopulation(0).ox1_crossover(p1, p2);
        p1.print();System.out.println();
        p2.print();System.out.println();
        child.print();

        Population p = new Population();
        p.addChromosome(child);
    }

    public static Population getPopulation(int i) {
        return generation.get(i);
    }
 }