package ch.bfh.bti7535.w2017.io;

/**
 * Creates instances based upon word counts.
 */
public class ReviewFactory {
    /**
     * Creates instances based upon word counts.
     * @param positiveCounter, number of positive matched words.
     * @param negativeCounter, number of negative matched words.
     * @param shouldBePositive, indicates whether this review should be positive or negative.
     * @return the instantiated Classifier.
     */
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
