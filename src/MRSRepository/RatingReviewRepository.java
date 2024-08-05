package MRSRepository;

import MRSConfig.DBHelper;
import MRSModel.RatingReviewModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RatingReviewRepository extends DBHelper {

	public boolean AddRatingReview(RatingReviewModel rrmodel) {
		try {
			CallableStatement cstmt = conn.prepareCall("{call  saveratingreview(?,?,?,?)}");
			cstmt.setInt(1, rrmodel.getRating());
			cstmt.setString(2, rrmodel.getReview());
			cstmt.setInt(3, rrmodel.getMov_id());
			cstmt.setInt(4, rrmodel.getUser_id());

			boolean b = cstmt.execute();
			if (!b) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.out.println("error is :" + ex);
			return false;
		}

	}

	public LinkedHashMap<String, Float> GetOverallRating() {
		LinkedHashMap<String, Float> map = new LinkedHashMap<String, Float>();
		String movname = null;
		try {
			stmt = conn.prepareStatement("select distinct mov_id from rating");
			rs = stmt.executeQuery();
			while (rs.next()) {
				int movid = rs.getInt(1);
				PreparedStatement stmt1 = conn.prepareStatement("select mov_name from moviemaster where mov_id=?");
				stmt1.setInt(1, movid);
				ResultSet rs2 = stmt1.executeQuery();
				if (rs2.next()) {
					movname = rs2.getString(1);
				}
				PreparedStatement pstmt = conn
						.prepareStatement("select count(rating),sum(rating) from rating where mov_id=?");
				pstmt.setInt(1, movid);
				ResultSet rs1 = pstmt.executeQuery();
				while (rs1.next()) {
					int usercount = rs1.getInt(1);
					int ratingsum = rs1.getInt(2);
					float overallrating = (float) (ratingsum) / (float) (usercount);

					map.put(movname, overallrating);
				}

			}

			// Sort the map by values (overall ratings) in descending order
			LinkedHashMap<String, Float> sortedMap = map.entrySet().stream()
					.sorted(Map.Entry.<String, Float>comparingByValue().reversed()) // Sort by value in descending order
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
							LinkedHashMap::new));

			return sortedMap;
		} catch (Exception e) {
			System.out.println("error is :" + e);
			return null;

		}

	}

	public boolean checkadmin(String adminname, String adminpassword) {
                 try {
                	 stmt=conn.prepareStatement("select *from checkadmin where adminusername=? and adminpassword=?");
                	 stmt.setString(1, adminname);
                	 stmt.setString(2, adminpassword);
                	 rs=stmt.executeQuery();
                	 if(rs.next()) {
                		 return true;
                	 }else {
                		 return false;
                	 }
                 }catch(Exception ex) {
                	 System.out.println("error is:"+ex);
                	 return false;
                 }
		
	}

	


}
