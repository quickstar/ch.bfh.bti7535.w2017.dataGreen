package ch.bfh.bti7535.w2017.features;

import weka.core.stopwords.AbstractStopwords;
import weka.core.stopwords.Rainbow;

public final class StopWordFactory {
    public static AbstractStopwords getRainbowWords() {
        return new Rainbow();
    }
}
