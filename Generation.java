import java.util.List;
import java.util.ArrayList;

public class Generation {
    private static List<Population> generation = new ArrayList<>();;
    private static double bestOverall;
    private static int idx_bestOverall;
    
    private static void init(int popSize) {
        Population p = new Population(popSize);
        generation.add(p);
        bestOverall = p.bestDistance;
    }

    public static void run(int genSize, int popSize, double mutationRate) {
        init(popSize);
        for (int i = 0; i < genSize; i++) {
            nextGeneration(i,popSize,mutationRate);
            print(i);
            if(generation.get(i).bestDistance < bestOverall) {
                bestOverall = generation.get(i).bestDistance;
                idx_bestOverall = i;
            }
        }
        System.out.printf("First Gen Shortest : %.2f KM\n", generation.get(0).bestDistance);
        System.out.printf("Global Shortest @Gen-"+idx_bestOverall+": %.2f KM\n", bestOverall);
        System.out.print("===============\nRute : ");
        generation.get(idx_bestOverall).getBestChromosome().printRoute();
    }

    public static void nextGeneration(int idx, int popSize, double mutationRate) { 
        Population newPop = new Population();
        Chromosome.sumFitness = 0;
        // Chromosome bestP1 = generation.get(idx).getBestChromosome();
        // newPop.addChromosome(bestP1); //add best parent from previous generation ~ elitism
        do{
            Chromosome p1 = generation.get(idx).naturalSelection();
            Chromosome p2 = generation.get(idx).naturalSelection();
            Chromosome child = GA.ox1_crossover(p1, p2);
            GA.swap_mutation(child, mutationRate);
            newPop.addChromosome(child);
        }
        while(newPop.getSize() < popSize);
        newPop.normalizeFitness();
        generation.add(newPop);
    }

    private static void print(int i) {
        System.out.println("Generation-"+i);
        generation.get(i).print();
        System.out.println("===============");
    }
 }