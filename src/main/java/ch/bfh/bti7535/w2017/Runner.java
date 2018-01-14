package ch.bfh.bti7535.w2017;

import ch.bfh.bti7535.w2017.Strategies.BaseLine.BaseLineStrategy;
import ch.bfh.bti7535.w2017.Strategies.NaiveBayes.NaiveBayesSentimentStrategy;
import ch.bfh.bti7535.w2017.Strategies.SentimentStrategy;
import ch.bfh.bti7535.w2017.features.DataGreenStopWordHandler;
import weka.core.stopwords.StopwordsHandler;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        List<StopwordsHandler> stopwordsHandlers = new ArrayList<>();
        stopwordsHandlers.add(new DataGreenStopWordHandler());

        List<SentimentStrategy> strategies = new ArrayList<>();

        strategies.add(new NaiveBayesSentimentStrategy(stopwordsHandlers));
        strategies.add(new BaseLineStrategy(stopwordsHandlers));

        for(SentimentStrategy strategy : strategies) {
            strategy.run();
            System.out.println(strategy.getSummary());
        }
    }
}