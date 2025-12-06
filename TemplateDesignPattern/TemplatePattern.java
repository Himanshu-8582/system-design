public class TemplatePattern {

    // We use this pattern to define a skeleton of an algorithm in a method,
    // deferring some steps to subclasses. This allows subclasses to redefine
    // certain steps of an algorithm without changing its structure.


    public static abstract class ModelTrainer {      // Abstract class defining the template method
        // Template method
        public final void trainPipeline(String path) {
            loadData(path);
            preprocessData();
            trainModel();
            evaluateModel();
            save();
        }

        protected void loadData(String path) {
            System.out.println("[common] Loading data from: " + path);
        }

        protected void preprocessData() {
            System.out.println("[common] spliting data into training and testing sets");
        }

        protected abstract void trainModel();

        protected abstract void evaluateModel();

        protected void save() {
            System.out.println("[common] saving the trained model to disk");
        }
    }

    public static class DecisionTreeTrainer extends ModelTrainer {         // Concrete implementation for Decision Tree
        @Override
        protected void trainModel() {
            System.out.println("[DecisionTree] training Decision Tree model");
        }

        @Override
        protected void evaluateModel() {
            System.out.println("[DecisionTree] evaluating Decision Tree model");
        }
    }

    public static class NeuralNetworkTrainer extends ModelTrainer {         // Concrete implementation for Neural Network
        @Override
        protected void trainModel() {
            System.out.println("[NeuralNetwork] training Neural Network model");
        }

        @Override
        protected void evaluateModel() {
            System.out.println("[NeuralNetwork] evaluating Neural Network model");
        }

        @Override
        protected void save() {
            System.out.println("[NeuralNetwork] saving Neural Network model with additional metadata");
        }
    }
    public static void main(String[] args) {
        // Using DecisionTreeTrainer
        System.out.println("    --- Decision Tree Training Pipeline ---");
        ModelTrainer decisionTreeTrainer = new DecisionTreeTrainer();
        decisionTreeTrainer.trainPipeline("data/decision_tree_dataset.csv");

        System.out.println();

        // Using NeuralNetworkTrainer
        System.out.println("    --- Neural Network Training Pipeline ---");
        ModelTrainer neuralNetworkTrainer = new NeuralNetworkTrainer();
        neuralNetworkTrainer.trainPipeline("data/neural_network_dataset.csv");
    }
}
