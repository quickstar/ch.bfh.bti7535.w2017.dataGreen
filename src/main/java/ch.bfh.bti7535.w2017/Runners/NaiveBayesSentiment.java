package ch.bfh.bti7535.w2017.Runners;

import ch.bfh.bti7535.w2017.evaluation.Evaluator;
import ch.bfh.bti7535.w2017.io.ReviewParser;
import ch.bfh.bti7535.w2017.util.RessourceLoader;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.TextDirectoryLoader;

public class NaiveBayesSentiment implements Runnable {
    Instances data;

    @Override
    public void run() {
        //get Training Data
        String path = new RessourceLoader().getRessource("reviews").getPath();

        //Create Classifier and evaluate data
        NaiveBayes naiveBayes = new NaiveBayes();
        try {
            naiveBayes.buildClassifier(data);
            Evaluator evaluator = new Evaluator();
            evaluator.setFolds(10);
            Evaluation evaluation =  evaluator.evaluate(naiveBayes,data);
            System.out.println(evaluation.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
