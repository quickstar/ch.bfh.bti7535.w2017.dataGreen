package ch.bfh.bti7535.w2017.io;

import weka.core.Instances;
import weka.core.converters.TextDirectoryLoader;

import java.io.File;
import java.io.IOException;

public class ReviewParser {
    private TextDirectoryLoader loader;
    private String path;

    public ReviewParser(TextDirectoryLoader loader, String path) {
        this.loader = loader;
        this.path = path;
        try {
            this.loader.setDirectory(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Instances getParsedFiles() {
        Instances parsedFiles = null;
        try {
            parsedFiles = this.loader.getDataSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsedFiles;
    }
}
