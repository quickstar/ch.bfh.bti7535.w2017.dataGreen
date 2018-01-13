package ch.bfh.bti7535.w2017.io;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SimpleWordReader {
    private String path;

    public SimpleWordReader(String path) {
        this.path = path;
    }

    public List<String> readAllWords() {
        URL url = getClass().getClassLoader().getResource(this.path);
        List<String> wordList = null;
        try {
            Path path = Paths.get(url.toURI());
            wordList = Files.readAllLines(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordList;
    }
}
