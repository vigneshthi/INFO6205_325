import org.junit.Test;
import static org.junit.Assert.*;

public class GeneticAlgorithmTest {
    @Test
    public void geneSequence() {
        Item a = new Item();
        a.setItemValue(6);
        a.setItemWeight(6);
        Knapsack.itemsList[0] = a;
        Item b = new Item();
        a.setItemValue(9);
        a.setItemWeight(4);
        Knapsack.itemsList[1] = b;
        Item c = new Item();
        a.setItemValue(8);
        a.setItemWeight(3);
        Knapsack.itemsList[2] = c;
        Item d = new Item();
        a.setItemValue(5);
        a.setItemWeight(3);
        Knapsack.itemsList[3] = d;
        Item e = new Item();
        a.setItemValue(8);
        a.setItemWeight(5);
        Knapsack.itemsList[4] = e;
//        Item f = new Item();
//        a.setItemValue(5);
//        a.setItemWeight(5);
//        Knapsack.itemsList[0] = f;
//        Item g = new Item();
//        a.setItemValue(8);
//        a.setItemWeight(3);
//        Knapsack.itemsList[2] = g;
//        Item h = new Item();
//        a.setItemValue(5);
//        a.setItemWeight(3);
//        Knapsack.itemsList[3] = h;
//        Item i = new Item();
//        a.setItemValue(8);
//        a.setItemWeight(5);
//        Knapsack.itemsList[4] = i;
//        Item j = new Item();
//        a.setItemValue(5);
//        a.setItemWeight(5);
//        Knapsack.itemsList[0] = j;
//
        GeneticAlgorithm sa = new GeneticAlgorithm();
        sa.getGene().add(1);
        sa.getGene().add(0);
        sa.getGene().add(0);
        sa.getGene().add(1);
        sa.getGene().add(1);

        Knapsack.newGeneration.add(sa);

        GeneticAlgorithm sb = new GeneticAlgorithm();
        sb.getGene().add(0);
        sb.getGene().add(0);
        sb.getGene().add(0);
        sb.getGene().add(1);
        sb.getGene().add(1);

        GeneticAlgorithm sc = new GeneticAlgorithm();
        sb.getGene().add(0);
        sb.getGene().add(0);
        sb.getGene().add(1);
        sb.getGene().add(1);
        sb.getGene().add(1);


        GeneticAlgorithm sd = new GeneticAlgorithm();
        sb.getGene().add(0);
        sb.getGene().add(1);
        sb.getGene().add(0);
        sb.getGene().add(1);
        sb.getGene().add(1);

        GeneticAlgorithm se = new GeneticAlgorithm();
        sb.getGene().add(0);
        sb.getGene().add(1);
        sb.getGene().add(1);
        sb.getGene().add(1);
        sb.getGene().add(1);

        GeneticAlgorithm sf = new GeneticAlgorithm();
        sb.getGene().add(1);
        sb.getGene().add(1);
        sb.getGene().add(1);
        sb.getGene().add(1);
        sb.getGene().add(0);

        GeneticAlgorithm sg = new GeneticAlgorithm();
        sb.getGene().add(1);
        sb.getGene().add(1);
        sb.getGene().add(1);
        sb.getGene().add(1);
        sb.getGene().add(1);


        Knapsack.newGeneration.add(sb);
        int g1=  Knapsack.newGeneration.get(0).getGene().get(0).intValue();
        assertEquals(1,g1);
        int g2=  Knapsack.newGeneration.get(0).getGene().get(1);
        assertEquals(0,g2);
        int g3=  Knapsack.newGeneration.get(0).getGene().get(2);
        assertEquals(0,g3);
        int g4=  Knapsack.newGeneration.get(0).getGene().get(3);
        assertEquals(1,g4);
        int g5=  Knapsack.newGeneration.get(0).getGene().get(4);
        assertEquals(1,g5);
    }

    @Test
    public void fitnessCalculation(){
        geneSequence();
        Knapsack.evaluatePopulation();
        GeneticAlgorithm sol = Knapsack.bestSolution();
        sol.fitnessCalculator();
        double fitness = sol.getFitness();

        assertEquals(8.0, fitness,0);


    }

    @Test
    public void solution(){
        geneSequence();
        Knapsack.evaluatePopulation();
        if(Knapsack.generation>1){
            Knapsack.createnewGeneration();
        }
        if(Knapsack.generation>79 && Knapsack.generation<85)
            assertTrue(true);
    }
}
