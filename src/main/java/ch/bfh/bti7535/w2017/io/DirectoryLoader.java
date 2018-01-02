package ch.bfh.bti7535.w2017.io;

import weka.core.converters.TextDirectoryLoader;

import java.io.File;
import java.io.IOException;

public class DirectoryLoader extends TextDirectoryLoader {

    @Override
    public void setDirectory(File dir) throws IOException {
        super.setDirectory(dir);
    }

    @Override
    public void setOutputFilename(boolean value) {
        super.setOutputFilename(value);
    }
}
