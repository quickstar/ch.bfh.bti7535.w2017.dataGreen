package ch.bfh.bti7535.w2017.io;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reads and analyzes the reviews.
 */
public class ReviewReader {
    private static final String POSITIVE_FOLDER = "reviews/pos";
    private static final String NEGATIVE_FOLDER = "reviews/neg";
    private List<String> positiveWords;
    private List<String> negativeWords;

    /**
     * Initializes the class and loads the positive and negative word list into memory.
     */
    public ReviewReader() {
        this.positiveWords = new SimpleWordReader("words/positive.txt").readAllWords();
        this.negativeWords = new SimpleWordReader("words/negative.txt").readAllWords();
    }

    /**
     * Returns the occurrences of the given word, in the source.
     * @param source
     * @param word
     * @return word occurrences in source
     */
    public static int countWord(String source, String word) {
        int i;
        int last = 0;
        int count = 0;
        do {
            i = source.indexOf(word, last);
            if (i != -1) count++;
            last = i + word.length();
        } while (i != -1);
        return count;
    }

    /**
     * @param path
     * @return a list of all Files in the given path.
     */
    private List<File> getFilesInFolder(String path) {
        URL url = getClass().getClassLoader().getResource(path);
        List<File> filesInFolder = null;
        try {
            filesInFolder = Files.walk(Paths.get(url.toURI()))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return filesInFolder;
    }

    public List<BaseClassifier> analyzeFile() {
        List<BaseClassifier> reviews = new ArrayList<>();
        for (File f : getFilesInFolder(this.POSITIVE_FOLDER)) {
            reviews.add(analyzeReview(f, true));
        }
        for (File f : getFilesInFolder(this.NEGATIVE_FOLDER)) {
            reviews.add(analyzeReview(f, false));
        }
        return reviews;
    }

    /**
     * Reads the review and count's all the words.
     * @param file
     * @param shouldBePositive
     * @return a classified instance.
     */
    private BaseClassifier analyzeReview(File file, boolean shouldBePositive) {
        StringBuilder sb = new StringBuilder();
        int positiveCounter = 0;
        int negativeCounter = 0;

        try {
            Files.newBufferedReader(file.toPath(), Charset.defaultCharset()).lines().forEach(s -> sb.append(s.toLowerCase()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fullReview = sb.toString();
        for (String word : this.positiveWords) {
            positiveCounter += countWord(fullReview, word);
        }

        for (String word : this.negativeWords) {
            negativeCounter += countWord(fullReview, word);
        }

        return ReviewFactory.createReview(positiveCounter, negativeCounter, shouldBePositive);
    }

}
