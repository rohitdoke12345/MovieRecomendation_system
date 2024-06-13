package MRSService;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import MRSModel.RatingReviewModel;
import MRSRepository.RatingReviewRepository;

public class RatingReviewService {
        RatingReviewRepository rrrepo=new RatingReviewRepository();

		public boolean AddRatingReview(RatingReviewModel rrmodel) {
			
			return rrrepo.AddRatingReview(rrmodel);
		}

		public LinkedHashMap<String,Float> GetOverallRating() {
			
			return rrrepo.GetOverallRating();
		}
        
}
