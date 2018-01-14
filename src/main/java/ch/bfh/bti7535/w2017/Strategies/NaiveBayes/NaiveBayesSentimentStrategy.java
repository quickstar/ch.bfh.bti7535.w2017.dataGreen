package ch.bfh.bti7535.w2017.Strategies.NaiveBayes;


import ch.bfh.bti7535.w2017.Strategies.SentimentStrategy;
import ch.bfh.bti7535.w2017.evaluation.Evaluator;
import ch.bfh.bti7535.w2017.io.DirectoryLoader;
import ch.bfh.bti7535.w2017.io.ReviewParser;
import ch.bfh.bti7535.w2017.util.ResourceLoader;
import weka.attributeSelection.ClassifierAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.core.Instances;
import weka.core.stopwords.StopwordsHandler;
import weka.core.tokenizers.WordTokenizer;
import weka.filters.Filter;
import weka.filters.MultiFilter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.util.ArrayList;
import java.util.List;

public class NaiveBayesSentimentStrategy extends SentimentStrategy {
    public NaiveBayesSentimentStrategy(List<StopwordsHandler> stopWordHandlers) {
        super(stopWordHandlers);
    }

    /**
     * @return the amount of Folds for the K-Fold algorithm.
     */
    public int getFoldsCount() {
        return 10;
    }

    /**
     * @return creates an instance of a text classifier.
     */
    public AbstractClassifier createClassifierInstance() {
        NaiveBayesMultinomial classifier = new NaiveBayesMultinomial();
        this.setTokenizer(new WordTokenizer());
        return classifier;
    }

    @Override
    public void run() {
        Instances data = loadGoldStandard();
        AbstractClassifier classifier = createClassifierInstance();
        List<Filter> filters = new ArrayList<>(this.stopWordHandlers.size());
        try {
            for (StopwordsHandler stopHandler : this.stopWordHandlers) {
                StringToWordVector wordVector = createWordVector(stopHandler, data);
                filters.add(wordVector);
            }

            AttributeSelection attributeSelection = new AttributeSelection();
            attributeSelection.setEvaluator(new ClassifierAttributeEval());
            attributeSelection.setSearch(new Ranker());
            filters.add(attributeSelection);

            MultiFilter mf = new MultiFilter();
            mf.setFilters(filters.toArray(new Filter[filters.size()]));
            mf.setInputFormat(data);

            data = Filter.useFilter(data, mf);

            classifier.buildClassifier(data);

            Evaluator evaluator = new Evaluator();
            evaluator.setFolds(getFoldsCount());

            Evaluation evaluation = evaluator.evaluate(classifier, data);
            setSummary(evaluation.toSummaryString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Instances loadGoldStandard() {
        //get Training Data
        String path = new ResourceLoader().getResource("reviews").getPath();
        ReviewParser reviewParser = new ReviewParser(new DirectoryLoader(), path);
        Instances data = reviewParser.getParsedFiles();
        return data;
    }


    private StringToWordVector createWordVector(StopwordsHandler handler, Instances data) throws Exception {
        StringToWordVector wordVector = new StringToWordVector();
        wordVector.setStopwordsHandler(handler);
        wordVector.setInputFormat(data);

        //wordVector.setStemmer(this.getStemmer());
        wordVector.setIDFTransform(true);
        wordVector.setTFTransform(true);
        wordVector.setOutputWordCounts(true);
        wordVector.setWordsToKeep(3000);
        wordVector.setPeriodicPruning(-1);
        wordVector.setTokenizer(this.getTokenizer());

        return wordVector;
    }
}
