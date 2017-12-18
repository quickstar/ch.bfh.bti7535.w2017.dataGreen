package ch.bfh.bti7535.w2017;

import ch.bfh.bti7535.w2017.io.ReviewParser;
import weka.core.Instances;
import weka.core.converters.TextDirectoryLoader;

public class Runner {
    public static void main(String[] args) {
        ReviewParser p = new ReviewParser(new TextDirectoryLoader(), "C:\\git\\ch.bfh.bti7535.w2017.dataGreen\\src\\main\\java\\resources\\reviews");
        Instances i = p.getParsedFiles();
    }
}