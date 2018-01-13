package ch.bfh.bti7535.w2017.io;

public class ReviewFactory {
    public static BaseClassifier createReview(int positiveCounter, int negativeCounter, boolean shouldBePositive) {
        if (shouldBePositive) {
            if (positiveCounter > negativeCounter) {
                return new CorrectlyClassified();
            }
            if (negativeCounter > positiveCounter) {
                return new WrongClassified();
            }
        } else {
            if (negativeCounter > positiveCounter) {
                return new CorrectlyClassified();
            }
            if (positiveCounter > negativeCounter) {
                return new WrongClassified();
            }
        }
        return new Unclassifiable();
    }
}
