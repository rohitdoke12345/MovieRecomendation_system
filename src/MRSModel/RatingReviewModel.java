package MRSModel;

public class RatingReviewModel extends MovieModel {
	private int ratingid;
	private int reviewid;
	private int rating;
	private String review;

	public int getRatingid() {
		return ratingid;
	}

	public void setRatingid(int ratingid) {
		this.ratingid = ratingid;
	}

	public int getReviewid() {
		return reviewid;
	}

	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public RatingReviewModel(int rating, String review) {
		this.ratingid = ratingid;
		this.reviewid = reviewid;
		this.rating = rating;
		this.review = review;
	}

	public RatingReviewModel() {

	}
}
