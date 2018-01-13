package ch.bfh.bti7535.w2017.Strategies.NaiveBayes;


import ch.bfh.bti7535.w2017.Strategies.SentimentStrategy;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.core.stopwords.StopwordsHandler;
import weka.core.tokenizers.WordTokenizer;

import java.util.List;

public class NaiveBayesSentimentStrategy extends SentimentStrategy {
    public NaiveBayesSentimentStrategy(List<StopwordsHandler> stopWordHandlers) {
        super(stopWordHandlers);
    }

    @Override
    public AbstractClassifier createClassifierInstance() {
        NaiveBayesMultinomial classifier = new NaiveBayesMultinomial();
        this.setTokenizer(new WordTokenizer());
        return classifier;
    }
}
