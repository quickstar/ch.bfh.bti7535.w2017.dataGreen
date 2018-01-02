package ch.bfh.bti7535.w2017;

import ch.bfh.bti7535.w2017.io.ReviewParser;
import ch.bfh.bti7535.w2017.util.RessourceLoader;
import weka.core.Instances;
import weka.core.converters.TextDirectoryLoader;

public class Runner {
    public static void main(String[] args) {
        String path = new RessourceLoader().getRessource("reviews").getPath();
        ReviewParser p = new ReviewParser(new TextDirectoryLoader(), path);
        Instances i = p.getParsedFiles();
    }
}