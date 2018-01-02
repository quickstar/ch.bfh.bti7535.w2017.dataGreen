package ch.bfh.bti7535.w2017.Runners;


import ch.bfh.bti7535.w2017.evaluation.Evaluator;
import ch.bfh.bti7535.w2017.features.DataGreenStopWordHandler;
import ch.bfh.bti7535.w2017.io.DirectoryLoader;
import ch.bfh.bti7535.w2017.io.ReviewParser;
import ch.bfh.bti7535.w2017.util.RessourceLoader;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.stemmers.SnowballStemmer;
import weka.filters.Filter;
import weka.filters.MultiFilter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class NaiveBayesSentiment implements Runnable {

    @Override
    public void run() {

        //get Training Data
        String path = new RessourceLoader().getRessource("reviews").getPath();
        ReviewParser reviewParser = new ReviewParser(new DirectoryLoader(), path);
        Instances data = reviewParser.getParsedFiles();

        //Create Classifier and evaluate data
        NaiveBayes naiveBayes = new NaiveBayes();
        try {
            StringToWordVector stwv = new StringToWordVector();
            stwv.setStopwordsHandler(new DataGreenStopWordHandler());
            stwv.setInputFormat(data);
            stwv.setLowerCaseTokens(true);
            stwv.setStemmer(new SnowballStemmer());

            Filter[] filters = new Filter[1];
            filters[0] = stwv;
            /*NGramTokenizer tokenizer = new NGramTokenizer();
            tokenizer.setNGramMinSize(1);
            tokenizer.setNGramMaxSize(4);*/
            // stwv.setTokenizer(tokenizer);

            MultiFilter mf = new MultiFilter();
            mf.setFilters(filters);
            mf.setInputFormat(data);

            data = Filter.useFilter(data, mf);

            naiveBayes.buildClassifier(data);

            Evaluator evaluator = new Evaluator();
            evaluator.setFolds(10);

            Evaluation evaluation = evaluator.evaluate(naiveBayes, data);
            System.out.println("Naive Bayes: " + evaluation.toSummaryString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
