
package ch.bfh.bti7535.w2017.evaluation;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.Random;

public class Evaluator {

    private int folds;

    public Evaluation evaluate(Classifier classifier, Instances data) throws Exception {
        Evaluation evaluation = new Evaluation(data);
        evaluation.crossValidateModel(classifier,data, folds, new Random());
        return evaluation;
    }

    public void setFolds(int folds) {
        this.folds = folds;
    }
}
