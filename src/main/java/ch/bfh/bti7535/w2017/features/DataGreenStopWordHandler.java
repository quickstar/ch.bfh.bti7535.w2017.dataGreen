package ch.bfh.bti7535.w2017.features;

import weka.core.stopwords.AbstractStopwords;
import weka.core.stopwords.StopwordsHandler;

public class DataGreenStopWordHandler implements StopwordsHandler {
    private AbstractStopwords stopWordHandler;

    public DataGreenStopWordHandler() {
        stopWordHandler = StopWordFactory.getRainbowWords();
    }

    @Override
    public boolean isStopword(String s) {
        return stopWordHandler.isStopword(s);
    }
}
