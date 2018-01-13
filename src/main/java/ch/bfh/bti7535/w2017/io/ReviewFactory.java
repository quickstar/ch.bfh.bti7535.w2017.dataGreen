package ch.bfh.bti7535.w2017.io;

public class ReviewFactory {
    public static BaseClassifier createReview(int positiveCounter, int negativeCounter, boolean shouldBePositive) {
        if (shouldBePositive) {
            if (positiveCounter > negativeCounter) {
                return new CorrectlyClassified(shouldBePositive);
            }
            if (negativeCounter > positiveCounter) {
                return new WrongClassified(shouldBePositive);
            }
        } else {
            if (negativeCounter > positiveCounter) {
                return new CorrectlyClassified(shouldBePositive);
            }
            if (positiveCounter > negativeCounter) {
                return new WrongClassified(shouldBePositive);
            }
        }
        return new UnclassifiableReview(shouldBePositive);
    }
}
