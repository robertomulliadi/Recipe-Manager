package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class Review {
    private final int ReviewID;
    private final String ReviewText;
    private final int Date;

    public Review(int ReviewID, String ReviewText, int Date) {
        this.ReviewID = ReviewID;
        this.ReviewText = ReviewText;
        this.Date = Date;
    }


    public int getReviewID() {
        return ReviewID;
    }
    public String getReviewText() {
        return ReviewText;
    }
    public int getDate() {
        return Date;
    }
}
