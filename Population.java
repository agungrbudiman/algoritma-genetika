import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Population {
    private List<Chromosome> population;
    private Random rnd;
    private double sumFitness, bestDistance;

    public Population() {
        population = new ArrayList<>();
        rnd = new Random();
        sumFitness = 0;
        bestDistance = Double.POSITIVE_INFINITY;
    }

    public void initPopulation(int popSize) {
        for (int i = 0; i < popSize; i++) {
            Chromosome c = new Chromosome();
            c.initChromosome();
            population.add(c);
        }
    }

    public void print() {
        for (Chromosome c : population) {
            c.print();System.out.println();
        }
    }

    public void normalizeFitness() {
        for (Chromosome c : population) {
            sumFitness += c.getFitness();
        }
        for (Chromosome c : population) {
            c.setFitness(c.getFitness() / sumFitness);
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
    public Chromosome ox1_crossover(Chromosome p1, Chromosome p2) {
        int cutPoint1 = rnd.nextInt(Data.size-1); //generate cut point
        int cutPoint2 = rnd.nextInt(Data.size-1);
        if(cutPoint1 > cutPoint2) { //swap if cutpoint1 > cutpoint2
            int temp = cutPoint2;
            cutPoint2 = cutPoint1;
            cutPoint1 = temp;
        }
        // System.out.println("cut1:"+cutPoint1+" cut2:"+cutPoint2);

        Chromosome child = new Chromosome();
        List<Integer> p2_copy = new ArrayList<>();
        
        // add non duplicate data from p2 into p2_copy
        for (int i = 0; i < Data.size; i++) {
            for (int j = cutPoint1; j <= cutPoint2; j++) { //check with every data in cutpoint
                if(p2.getGene(i) == p1.getGene(j)) {
                    break;
                }
                else if(j == cutPoint2) { //end of the loop
                    p2_copy.add(p2.getGene(i));
                }
            }
        }
        // System.out.println(p2_copy.toString());

        // add element before cutpoint1 into child
        for (int i = 0; i < cutPoint1; i++) {
            child.addGene(p2_copy.get(i));
        }
        // add element inside cutpoint into child
        for (int i = cutPoint1; i <= cutPoint2; i++) {
            child.addGene(p1.getGene(i));
        }
        // add element after cutpoint2 into child
        for (int i = cutPoint1; i < p2_copy.size(); i++) {
            child.addGene(p2_copy.get(i));
        }

        return child;
    }
    public void swap_mutation() {
        
    }

    public void addChromosome(Chromosome c) {
        population.add(c);
    }
}