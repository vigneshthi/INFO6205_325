import java.util.Arrays;
import java.util.Comparator;

public class Population {


    private double fitnessValue;
    private double meanFitnessValue;
    private GeneticAlgorithm ga = new GeneticAlgorithm();


    public GeneticAlgorithm getGa() {
        return ga;
    }

    public void setGa(GeneticAlgorithm ga) {
        this.ga = ga;
    }

    public double getFitnessValue() {
        return fitnessValue;
    }

    public void setFitnessValue(double fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    public double getMeanFitnessValue() {
        return meanFitnessValue;
    }

    public void setMeanFitnessValue(double meanFitnessValue) {
        this.meanFitnessValue = meanFitnessValue;
    }



}
