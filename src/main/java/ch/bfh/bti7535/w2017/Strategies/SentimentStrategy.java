package ch.bfh.bti7535.w2017.Strategies;

import ch.bfh.bti7535.w2017.evaluation.Evaluator;
import ch.bfh.bti7535.w2017.io.DirectoryLoader;
import ch.bfh.bti7535.w2017.io.ReviewParser;
import ch.bfh.bti7535.w2017.util.RessourceLoader;
import weka.attributeSelection.ClassifierAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.stemmers.NullStemmer;
import weka.core.stemmers.Stemmer;
import weka.core.stopwords.StopwordsHandler;
import weka.core.tokenizers.NGramTokenizer;
import weka.core.tokenizers.Tokenizer;
import weka.core.tokenizers.WordTokenizer;
import weka.filters.Filter;
import weka.filters.MultiFilter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.util.ArrayList;
import java.util.List;

public abstract class SentimentStrategy implements Runnable {
    protected List<StopwordsHandler> stopWordHandlers;
    private Tokenizer tokenizer;
    private Stemmer stemmer;

    public SentimentStrategy(List<StopwordsHandler> stopWordHandlers) {
        this.stopWordHandlers = stopWordHandlers;
    }

    public Tokenizer getTokenizer() {
        if(this.tokenizer == null) {
            WordTokenizer tokenizer = new WordTokenizer();
            tokenizer.setDelimiters(" \r \n \t.,;:'\"()?!");
            this.tokenizer = tokenizer;
        }
        /**
        if (this.tokenizer == null) {
            NGramTokenizer gramTokenizer = new NGramTokenizer();
            gramTokenizer.setDelimiters(" \r \n \t.,;:'\"()?!");
            gramTokenizer.setNGramMinSize(1);
            gramTokenizer.setNGramMaxSize(3);
            this.tokenizer = gramTokenizer;
        }
         */
        return this.tokenizer;

    }

    public void setTokenizer(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public Stemmer getStemmer() {
        if (this.stemmer == null) {
            this.stemmer = new NullStemmer();
        }
        return this.stemmer;
    }

    public void setStemmer(Stemmer stemmer) {
        this.stemmer = stemmer;
    }

    public abstract AbstractClassifier createClassifierInstance();

    public Instances loadGoldStandard() {
        //get Training Data
        String path = new RessourceLoader().getRessource("reviews").getPath();
        ReviewParser reviewParser = new ReviewParser(new DirectoryLoader(), path);
        Instances data = reviewParser.getParsedFiles();
        return data;
    }

    public int getFoldsCount() {
        return 10;
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
            System.out.println("Naive Bayes: " + evaluation.toSummaryString());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
