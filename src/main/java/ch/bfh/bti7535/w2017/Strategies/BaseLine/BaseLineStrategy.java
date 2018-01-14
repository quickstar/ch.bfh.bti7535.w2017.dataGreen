package ch.bfh.bti7535.w2017.Strategies.BaseLine;

import ch.bfh.bti7535.w2017.Strategies.SentimentStrategy;
import ch.bfh.bti7535.w2017.io.BaseClassifier;
import ch.bfh.bti7535.w2017.io.CorrectlyClassified;
import ch.bfh.bti7535.w2017.io.ReviewReader;
import ch.bfh.bti7535.w2017.io.Unclassifiable;
import weka.core.stopwords.StopwordsHandler;

import java.util.List;


public class BaseLineStrategy extends SentimentStrategy {
    /**
     * Initializes the class.
     *
     * @param stopWordHandlers
     */
    public BaseLineStrategy(List<StopwordsHandler> stopWordHandlers) {
        super(stopWordHandlers);
    }

    @Override
    public void run() {
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
        setSummary("Baseline accuracy: " + percentage);
    }
}

