package ch.bfh.bti7535.w2017.io;

import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ARFFFileLoader {

    public Instances getInstances(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        Instances data = new Instances(reader);
        reader.close();
        // setting class attribute
        data.setClassIndex(data.numAttributes() - 1);
        return data;
    }
}
