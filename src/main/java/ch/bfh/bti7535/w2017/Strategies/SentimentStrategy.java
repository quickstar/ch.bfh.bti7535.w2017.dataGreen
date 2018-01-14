package ch.bfh.bti7535.w2017.Strategies;

import weka.core.stemmers.NullStemmer;
import weka.core.stemmers.Stemmer;
import weka.core.stopwords.StopwordsHandler;
import weka.core.tokenizers.Tokenizer;
import weka.core.tokenizers.WordTokenizer;

import java.util.List;

/**
 * Base Strategy class which shares some common functionality.
 */
public abstract class SentimentStrategy implements Runnable {
    protected List<StopwordsHandler> stopWordHandlers;
    private Tokenizer tokenizer;
    private Stemmer stemmer;
    private String summary;

    /**
     * Initializes the class.
     * @param stopWordHandlers
     */
    public SentimentStrategy(List<StopwordsHandler> stopWordHandlers) {
        this.stopWordHandlers = stopWordHandlers;
    }

    /**
     * Get's the tokenizer, if none is set, the default WordTokenizer is returned.
     * @return Tokenizer
     */
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

    /**
     * Get's the Stemmer.
     * @return Stemmer
     */
    public Stemmer getStemmer() {
        if (this.stemmer == null) {
            this.stemmer = new NullStemmer();
        }
        return this.stemmer;
    }

    /**
     * Set's the stemmer.
     * @param stemmer
     */
    public void setStemmer(Stemmer stemmer) {
        this.stemmer = stemmer;
    }

    /**
     * Runs the strategy and classifies movie reviews.
     */
    public abstract void run();

    /**
     * get's the accuracy of the chosen algorithm.
     * @return String
     */
    public String getSummary() {
        if (summary == null || summary.isEmpty()) {
            return "The algorithm must run before calculating an accuracy summary.";
        }
        return summary;
    }

    /**
     * set's the summary text of
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
}
