    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;
    import java.util.Random;
    import org.apache.log4j.Logger;


        public class Knapsack {

            final static Logger logger = Logger.getLogger(Knapsack.class);

            public static int generation = 100;
            public static final int totalItems = 5;
            public static final int capacity = 13;
            public static final int populationCount = 100;


            public static int currentGeneration = 0;
            public static double crossoverValue = 0.6;
            public static double cullingValue = 0.9;
            public static double mutationValue = 0.02;

            public static Item[] itemsList = new Item[totalItems];

            public static ArrayList<Population> totalPopulation = new ArrayList<Population>();
            public static ArrayList<GeneticAlgorithm> newGeneration = new ArrayList<GeneticAlgorithm>();
            public static ArrayList<GeneticAlgorithm> breedPopulation = new ArrayList<GeneticAlgorithm>();
            public static Population currentPopulation;

            public static int populationFitness() {
                int popFitness = 0;
                for (GeneticAlgorithm aNewGeneration : newGeneration) {
                    if (capacity <= aNewGeneration.getWeight())
                        popFitness = (int) (popFitness + aNewGeneration.getFitness());

                }
                return popFitness;
            }

            public static GeneticAlgorithm bestSolution() {
                int index = 0;
                double currentFittness = 0;
                double best = 0;
                for (int i = 0; i < newGeneration.size(); i++) {
                    currentFittness = newGeneration.get(i).getFitness();
                    if (best<currentFittness) {
                        best = currentFittness;
                        index = i;
                    }

                }
                return newGeneration.get(index);
            }

            private static double newPopulation() {
                double mean = 0;
                double total = 0;
                for (GeneticAlgorithm aNewGeneration : newGeneration) {
                    total = total + aNewGeneration.getFitness();

                }
                mean = total/populationCount;
                return mean;
            }


            public static void evaluatePopulation() {
                currentPopulation = new Population();
                currentPopulation.setFitnessValue(populationFitness());

                currentPopulation.setGa(bestSolution());
                currentPopulation.setMeanFitnessValue(newPopulation());
                totalPopulation.add(currentPopulation);
            }

            private static int randomGene() {

                double number = Math.random()*currentPopulation.getFitnessValue();
                for(int i = 0; i < populationCount; i++) {
                    if(number <= newGeneration.get(i).getFitness()) {
                        return i;
                    }
                    number = number - newGeneration.get(i).getFitness();
                }
                return 0;
            }

            //CrossOver population

            private static void crossover(int gene1, int gene2) {
                ArrayList<Integer> c1 = new ArrayList<>();
                ArrayList<Integer> c2 = new ArrayList<>();
                double rand = Math.random();
                //System.out.println("crossover Involved!");
                logger.info("crossover Involved!");
                if(rand<=crossoverValue)
                {
                    Random generator = new Random();
                    int divide = generator.nextInt(totalItems) + 1;
                    //  int divide = totalItems/2;
                    c1.addAll(newGeneration.get(gene1).getGene().subList(0, divide));
                    c1.addAll(newGeneration.get(gene2).getGene().subList(divide, newGeneration.get(gene2).getGene().size()));


                    c2.addAll(newGeneration.get(gene2).getGene().subList(0, divide));
                    c2.addAll(newGeneration.get(gene1).getGene().subList(divide, newGeneration.get(gene2).getGene().size()));


                    breedPopulation.add(new GeneticAlgorithm(c1));
                    breedPopulation.add(new GeneticAlgorithm(c2));
                }
                else {
                    breedPopulation.add(newGeneration.get(gene1));
                    breedPopulation.add(newGeneration.get(gene2));
                }
            }


            private static void mutuation() {
                double randMutation = Math.random();
                if(randMutation >= mutationValue){
                    //System.out.println("Mutation involved...");
                    logger.info("Mutation Involved....");
                    int mutationIndex = (int)(Math.random() * totalItems);
                    int mutationSolution = (int)(Math.random() * breedPopulation.size());

                    if(breedPopulation.get(mutationSolution).getGene().get(mutationIndex) == 1)
                        breedPopulation.get(mutationSolution).getGene().set(mutationIndex, 0);
                    else
                        breedPopulation.get(mutationSolution).getGene().set(mutationIndex, 1);
                }
            }


            //create new Generation
            public static void createnewGeneration() {
                for(int i=1;i<generation;i++)
                {
                    breedPopulation = new ArrayList<>();

                    if((i > (generation * 0.6) && (generation >= 3))) {
                        double a = totalPopulation.get(i-1).getGa().getFitness();
                        double b = totalPopulation.get(i-2).getGa().getFitness();
                        double c = totalPopulation.get(i-3).getGa().getFitness();
                        if(a == b && b == c){
                            System.out.println("Stop condition meet at generation: " + (i-1));
                            System.out.print("Total Value Possible - "+ (totalPopulation.get(i-1).getGa().getFitness()) + " With inclusion of items -:");
                            for(int x:totalPopulation.get(i-1).getGa().getGene())
                                System.out.print(x);
                            System.out.println("");
                            generation = i;
                            break;
                        }
                    }
                    System.out.println("Generation" + i);


                    //Breed new gen
                    for(int co = 0; co< generation/2;co++){
                        int gene1;
                        int gene2;
                        currentGeneration = currentGeneration+1;
                        if(populationCount%2 == 1){
                            breedPopulation.add(totalPopulation.get(currentGeneration-1).getGa());
                        }
                        gene1 = randomGene();
                        gene2 = randomGene();
                        crossover(gene1,gene2);
                    }


                    //mutation of the population
                    mutuation();

                    //fitness of the new breed
                    for (GeneticAlgorithm aBreedPopulation : breedPopulation) {
                        aBreedPopulation.fitnessCalculator();
                    }

                    //copying to population
                    for(int k = 0; k < breedPopulation.size(); k++) {
                        newGeneration.add(k,breedPopulation.get(k));
                    }

                    logger.info("Gen seq           Fitness");


                    for (int i1 = 0; i1 < newGeneration.size(); i1++) {
                        GeneticAlgorithm aNewGeneration = newGeneration.get(i1);
                        for (int z : aNewGeneration.getGene()) {
                            //System.out.print(z);
                            logger.info(z);
                        }
                        //System.out.println("        " + aNewGeneration.getFitness());
                        logger.info("        " + aNewGeneration.getFitness());

                    }


                    Collections.sort(newGeneration);
                    double tot = 0.0, avg =0.0 ;
                    for(int av = 0; av < newGeneration.size(); av++){
                        tot += newGeneration.get(av).getFitness();
                    }
                    avg = tot/newGeneration.size();

                    logger.info("Gen:"+ i +" Gen Seq: "+ newGeneration.get(0).getGene() + " BestFitness: "+ newGeneration.get(0).getFitness()+" Avg: "+avg);

                    System.out.println("Gen:"+ i +"  Best Gen-Seq based on fitness: "+ newGeneration.get(0).getGene());




                    newGeneration = culling(newGeneration);
                    currentPopulation = new Population();
                    currentPopulation.setFitnessValue(populationFitness());
                    currentPopulation.setGa(bestSolution());
                    currentPopulation.setMeanFitnessValue(newPopulation());
                    totalPopulation.add(currentPopulation);
                    //evaluatePopulation();
                }
            }

            private static void randomgeneratedItems(){
                Random r = new Random();
                for(int i =0; i< totalItems; i++){
                    Item item = new Item();
                    item.setItemValue(r.nextInt(10));
                    item.setItemWeight(r.nextInt(10));
                    itemsList[i] = item;
                }

            }

            private static void randomgeneratedPopulation() {

                for (int i =0;i<populationCount;i++){
                    GeneticAlgorithm x = new GeneticAlgorithm();
                    x.generatingGene();
                    newGeneration.add(x);

                }
            }

            public static ArrayList<GeneticAlgorithm> culling(ArrayList<GeneticAlgorithm> toBeCulled) {

                double check = Math.random();
                if (check > 0.5)
                {
                    Collections.sort(toBeCulled);

                    int cutoff = (int) (cullingValue * toBeCulled.size());
                    ArrayList<GeneticAlgorithm> pop = new ArrayList<>();
                    for (int i = 0; i <= cutoff; i++) {
                        pop.add(toBeCulled.get(i));
                    }
                    for (int j = 0; j < pop.size(); j++) {
                        for (int z : pop.get(j).getGene()) {
                            //System.out.print(z);
                            logger.info(z);
                        }
                        logger.info("        " + pop.get(j).getFitness());
                    }


                return pop;
                } else {
                    return toBeCulled;
                }
            }

            public static void main(String[] args) {

                randomgeneratedItems();
                System.out.println("Items -  values & Weights.....");
                for (int i = 0; i < totalItems; i++) {
                    System.out.print(itemsList[i].getItemValue() + "  ");

                }
                System.out.println();
                for (int i = 0; i < totalItems; i++) {
                    System.out.print(itemsList[i].getItemWeight() + "  ");

                }

                System.out.println();

                randomgeneratedPopulation();
                System.out.println("generation  Zero!");
                System.out.println("GeneSeq    Value  Weight");
                for (int i = 0; i < populationCount; i++) {

                    for (int z : newGeneration.get(i).getGene()) {
                        System.out.print(z);
                    }
                    System.out.println("   " + newGeneration.get(i).getValue() + "  " + newGeneration.get(i).getWeight());
                }
                System.out.println();

                evaluatePopulation();

                if (generation > 1) {
                    System.out.println("Generation creation:");
                    createnewGeneration();

                }
            }
        }

