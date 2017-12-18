package ch.bfh.bti7535.w2017.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ReviewFiles {
    List<String> pos;
    List<String> neg;

    public ReviewFiles() {
        pos = new ArrayList<>();
        neg = new ArrayList<>();
    }

    public void readFiles(String posPath, String negPath){

        try  {
            Files.walk(Paths.get(posPath))
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        try {
                            String content = new String(Files.readAllBytes(file));
                            pos.add(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
            Files.walk(Paths.get(negPath))
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        try {
                            String content = new String(Files.readAllBytes(file));
                            neg.add(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getPos() {
        return pos;
    }

    public List<String> getNeg() {
        return neg;
    }
}
