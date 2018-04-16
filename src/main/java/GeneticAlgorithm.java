import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm implements Comparable {

    private ArrayList<Integer> gene = new ArrayList<>();
    private double Weight = 0.0;
    private double Value = 0.0;
    private double Fitness = 0.0;


    public GeneticAlgorithm(){

    }

    public GeneticAlgorithm(ArrayList<Integer> sequence){
        this.gene = sequence;
    }

    public ArrayList<Integer> getGene() {
        return gene;
    }

    public void setGene(ArrayList<Integer> gene) {
        this.gene = gene;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        this.Weight = weight;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        this.Value = value;
    }

    public double getFitness() {
        return Fitness;
    }

    public void setFitness(double fitness) {
        this.Fitness = fitness;
    }


    public void generatingGene(){
        Random rand = new Random();
        for(int i=0; i< Knapsack.totalItems; i++){
            this.gene.add(rand.nextInt(2));
        }

        this.fitnessCalculator();
    }

    public void fitnessCalculator(){
        double tempWeight=0.0, tempValue=0.0;
        for(int i=0; i< Knapsack.totalItems; i++){
            if(this.gene.get(i) == 1){
                tempValue += Knapsack.itemsList[i].getItemValue();
                tempWeight += Knapsack.itemsList[i].getItemWeight();
            }
        }
        this.Weight = tempWeight;
        this.Value = tempValue;
        if(this.Weight <= Knapsack.capacity){
            this.Fitness = this.Value;}
        //    else {}
    }



    @Override
    public int compareTo(Object comparestu) {
        double compareFitness=((GeneticAlgorithm)comparestu).getFitness();
        /* For sorting in descending order do like this */
        //return (int)(this.Fitness-compareFitness);
        return (int)(compareFitness-this.Fitness);
    }
}
