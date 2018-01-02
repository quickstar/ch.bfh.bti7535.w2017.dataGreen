package ch.bfh.bti7535.w2017.Runners;


import ch.bfh.bti7535.w2017.evaluation.Evaluator;
import ch.bfh.bti7535.w2017.io.ReviewParser;
import ch.bfh.bti7535.w2017.util.RessourceLoader;
import main.java.ch.bfh.bti7535.w2017.io.DirectoryLoader;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayesMultinomialText;
import weka.core.Instances;

public class NaiveBayesSentiment implements Runnable {
    Instances data;

    @Override
    public void run() {

        //get Training Data
        String path = new RessourceLoader().getRessource("reviews").getPath();
        ReviewParser reviewParser = new ReviewParser(new DirectoryLoader(),path);
        data = reviewParser.getParsedFiles();


        //Create Classifier and evaluate data
        NaiveBayesMultinomialText naiveBayes = new NaiveBayesMultinomialText();
        try {
            naiveBayes.buildClassifier(data);
            Evaluator evaluator = new Evaluator();
            evaluator.setFolds(10);
            Evaluation evaluation =  evaluator.evaluate(naiveBayes,data);
            System.out.println("Naive Bayes: " + evaluation.toSummaryString());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
