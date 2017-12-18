package main.java.ch.bfh.bti7535.w2017;


import ch.bfh.bti7535.w2017.io.ReviewFiles;

public class Runner {
    public static void main(String[] args) {
        ReviewFiles reviewFiles = new ReviewFiles();
        String pos = "C:\\gitProjects\\ch.bfh.bti7535.w2017.dataGreen\\src\\main\\java\\resources\\reviws\\pos";
        String neg = "C:\\gitProjects\\ch.bfh.bti7535.w2017.dataGreen\\src\\main\\java\\resources\\reviws\\neg";
        reviewFiles.readFiles(pos,neg);



    }
}