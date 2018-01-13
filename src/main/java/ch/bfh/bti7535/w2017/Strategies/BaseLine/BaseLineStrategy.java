package ch.bfh.bti7535.w2017.Strategies.BaseLine;

import ch.bfh.bti7535.w2017.io.*;
import weka.core.stemmers.Stemmer;

import java.util.List;


public class BaseLineStrategy {
    private Stemmer stemmer;

    public BaseLineStrategy(Stemmer stemmer) {
        this.stemmer = stemmer;
    }

    public void prepare() {
        List<BaseClassifier> baseReviews = new ReviewReader().analyzeFile();
        long correctClassified = 0;
        long unclassifiable = 0;
        long negativeClassified = 0;
        long total = 2000;
        for (BaseClassifier r : baseReviews) {
            if (r instanceof CorrectlyClassified) {
                correctClassified++;
            }
            if (r instanceof UnclassifiableReview) {
                unclassifiable++;
            }
            if (r instanceof WrongClassified) {
                negativeClassified++;
            }
        }
        long percentage = correctClassified * 100 / (total - unclassifiable);
    }
}

