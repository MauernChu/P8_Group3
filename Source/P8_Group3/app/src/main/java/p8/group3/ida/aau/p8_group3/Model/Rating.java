package p8.group3.ida.aau.p8_group3.Model;


public class Rating {
    private String timeRatingCreated;
    private String timeRatingEdited;
    private float ratingValue;
    private String reviewText;
    private int ratingLocationID;
    private int ratingParentID;


    public Rating(String timeRatingCreated, String timeRatingEdited, float ratingValue, String reviewText, int ratingLocationID, int ratingParentID) {
        this.timeRatingCreated = timeRatingCreated;
        this.timeRatingEdited = timeRatingEdited;
        this.ratingValue = ratingValue;
        this.reviewText = reviewText;
        this.ratingLocationID = ratingLocationID;
        this.ratingParentID = ratingParentID;

    }

    public String getTimeRatingCreated() {
        return timeRatingCreated;
    }

    public void setTimeRatingCreated(String timeRatingCreated) {
        this.timeRatingCreated = timeRatingCreated;
    }

    public String getTimeRatingEdited() {
        return timeRatingEdited;
    }

    public void setTimeRatingEdited(String timeRatingEdited) {
        this.timeRatingEdited = timeRatingEdited;
    }

    public float getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(float ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRatingLocationID() {
        return ratingLocationID;
    }

    public void setRatingLocationID(int ratingLocationID) {
        this.ratingLocationID = ratingLocationID;
    }

    public int getRatingParentID() {
        return ratingParentID;
    }

    public void setRatingParentID(int ratingParentID) {
        this.ratingParentID = ratingParentID;
    }
}

