import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GA {

    static Random rnd = new Random();

    public static Chromosome ox1_crossover(Chromosome p1, Chromosome p2) {
        int cutPoint1 = rnd.nextInt(16); //generate cut point
        int cutPoint2 = rnd.nextInt(16-cutPoint1)+cutPoint1;
        
        List<Integer> gene = new ArrayList<>();
        List<Integer> p2_copy = new ArrayList<>();
        
        // add non duplicate data from p2 into p2_copy
        for (int i = 0; i < Data.size; i++) {
            for (int j = cutPoint1; j <= cutPoint2; j++) { //check with every data in cutpoint
                if(p2.getGene(i) == p1.getGene(j)) {
                    break;
                }
                else if(j == cutPoint2) { //end of the loop ~ different than anything
                    p2_copy.add(p2.getGene(i));
                }
            }
        }
        
        // add element before cutpoint1 into child
        for (int i = 0; i < cutPoint1; i++) {
            gene.add(p2_copy.get(i));
        }
        // add element inside cutpoint into child
        for (int i = cutPoint1; i <= cutPoint2; i++) {
            gene.add(p1.getGene(i));
        }
        // add element after cutpoint2 into child
        for (int i = cutPoint1; i < p2_copy.size(); i++) {
            gene.add(p2_copy.get(i));
        }
        
        Chromosome child = new Chromosome(gene);
        return child;
    }
    
    public static void swap_mutation(Chromosome c, double rate) {
        if(rnd.nextDouble() <= rate) { //mutation probability
            int point1 = rnd.nextInt(16);
            int point2 = rnd.nextInt(16);
            c.swapGene(point1, point2);
        }

    }
}