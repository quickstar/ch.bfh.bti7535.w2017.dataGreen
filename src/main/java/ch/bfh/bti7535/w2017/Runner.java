package ch.bfh.bti7535.w2017;


import ch.bfh.bti7535.w2017.Strategies.NaiveBayes.NaiveBayesSentimentStrategy;
import ch.bfh.bti7535.w2017.features.DataGreenStopWordHandler;
import weka.core.stopwords.StopwordsHandler;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        List<StopwordsHandler> stopwordsHandlers = new ArrayList<>();
        stopwordsHandlers.add(new DataGreenStopWordHandler());

        new NaiveBayesSentimentStrategy(stopwordsHandlers).run();
    }
}