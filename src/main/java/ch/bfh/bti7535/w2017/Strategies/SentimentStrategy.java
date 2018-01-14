package ch.bfh.bti7535.w2017.Strategies;

import weka.core.stemmers.NullStemmer;
import weka.core.stemmers.Stemmer;
import weka.core.stopwords.StopwordsHandler;
import weka.core.tokenizers.Tokenizer;
import weka.core.tokenizers.WordTokenizer;

import java.util.List;

public abstract class SentimentStrategy implements Runnable {
    protected List<StopwordsHandler> stopWordHandlers;
    private Tokenizer tokenizer;
    private Stemmer stemmer;
    private String summary;

    public SentimentStrategy(List<StopwordsHandler> stopWordHandlers) {
        this.stopWordHandlers = stopWordHandlers;
    }

    public Tokenizer getTokenizer() {
        if (this.tokenizer == null) {
            WordTokenizer tokenizer = new WordTokenizer();
            this.tokenizer = tokenizer;
        }
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

    /**
     * Runs the strategy and classifies movie reviews.
     */
    public abstract void run();

    /**
     * @return the accuracy of the chosen algorithm.
     */
    public String getSummary() {
        if (summary == null || summary.isEmpty()) {
            return "The algorithm must run before calculating an accuracy summary.";
        }
        return summary;
    }

    /**
     * @return the summary text of
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
}
