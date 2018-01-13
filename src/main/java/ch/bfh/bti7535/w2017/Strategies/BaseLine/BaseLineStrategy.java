package ch.bfh.bti7535.w2017.Strategies.BaseLine;

import ch.bfh.bti7535.w2017.io.*;
import weka.core.stemmers.Stemmer;

import java.util.List;


public class BaseLineStrategy {
    public void prepare() {
        List<BaseClassifier> baseReviews = new ReviewReader().analyzeFile();
        long correctClassified = 0;
        long unclassifiable = 0;
        long total = 2000;
        for (BaseClassifier r : baseReviews) {
            if (r instanceof CorrectlyClassified) {
                correctClassified++;
            }
            if (r instanceof Unclassifiable) {
                unclassifiable++;
            }
        }
        long percentage = correctClassified * 100 / (total - unclassifiable);
    }
}

