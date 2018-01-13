package ch.bfh.bti7535.w2017.util;

import java.io.File;

public class ResourceLoader {
    public File getResource(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputData = new File(classLoader.getResource(path).getFile());
        return inputData;
    }
}
