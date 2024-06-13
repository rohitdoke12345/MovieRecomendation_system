package MRSRepository;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import MRSClientApplication.ClientApplication;
import MRSConfig.DBHelper;
import MRSConfig.PathHelper;
import MRSModel.MovieModel;

public class MovieRepository extends DBHelper {
	Scanner sc = new Scanner(System.in);
	private LinkedHashMap<Integer, ArrayList<String>> map1;
	

	public boolean isAddMovie(MovieModel model) {
		try {
			stmt = conn.prepareStatement("insert into movieMaster values('0',?,?,?,?)");
			stmt.setString(1, model.getMov_name());
			stmt.setString(2, model.getMov_lang());
			stmt.setString(3, model.getMov_actor());
			stmt.setInt(4, model.getMov_year());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("error is:" + ex);
			return false;
		}

	}

	public List<MovieModel> getAllMovies() {
		List<MovieModel> list = new ArrayList<MovieModel>();
		try {
			stmt = conn.prepareStatement("select *from moviemaster");
			rs = stmt.executeQuery();
			while (rs.next()) {
				MovieModel mmodel = new MovieModel();
				mmodel.setMov_id(rs.getInt(1));
				mmodel.setMov_name(rs.getString(2));
				mmodel.setMov_lang(rs.getString(3));
				mmodel.setMov_actor(rs.getString(4));
				mmodel.setMov_year(rs.getInt(5));
				list.add(mmodel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list.size() > 0 ? list : null;
	}

	public boolean updatemoviedetails(int id) {
		int value = -1;
		try {
			if (id > 0) {
				stmt = conn.prepareStatement("select mov_id from moviemaster where mov_id=?");
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				while (rs.next()) {
					if (id == rs.getInt(1)) {
						boolean exit = false;
						do {
							System.out.println("what do you want to update select option");
							System.out.println("1.movie name\n2.movie actor\n3.movie language\n4.movie year\n5.exits");
							System.out.println("enter your option");
							int option = sc.nextInt();

							switch (option) {
							case 1:
								sc.nextLine();
								System.out.println("enter the new movie name for update");
								String newmoviename = sc.nextLine();
								stmt = conn.prepareStatement("update moviemaster set mov_name=? where mov_id=?");
								stmt.setString(1, newmoviename);
								stmt.setInt(2, id);
								value = stmt.executeUpdate();
								System.out.println(
										value > 0 ? "movie name update successfully.." : "some problem is there");
								break;
							case 2:
								sc.nextLine();
								System.out.println("enter the  Actor name for update");
								String newactorname = sc.nextLine();
								stmt = conn.prepareStatement("update moviemaster set mov_actor=? where mov_id=?");
								stmt.setString(1, newactorname);
								stmt.setInt(2, id);
								value = stmt.executeUpdate();
								System.out.println(
										value > 0 ? "Actor name update successfully.." : "some problem is there");
								break;
							case 3:
								sc.nextLine();
								System.out.println("enter the  movie language for update");
								String newmovielanguage = sc.nextLine();
								stmt = conn.prepareStatement("update moviemaster set mov_actor=? where mov_id=?");
								stmt.setString(1, newmovielanguage);
								stmt.setInt(2, id);
								value = stmt.executeUpdate();
								System.out.println(
										value > 0 ? "movie language update successfully.." : "some problem is there");
								break;
							case 4:
								sc.nextLine();
								System.out.println("enter the  movie year for update");
								int newmovieyear = sc.nextInt();
								stmt = conn.prepareStatement("update moviemaster set mov_year=? where mov_id=?");
								stmt.setInt(1, newmovieyear);
								stmt.setInt(2, id);
								value = stmt.executeUpdate();
								System.out.println(
										value > 0 ? "movie year update successfully.." : "some problem is there");
								break;
							case 5:
								exit = true;
								break;
							default:
								System.out.println("invalide option");
								break;
							}
						} while (!exit);
					} else {
						System.out.println("id not found");
					}
				}

			} else {
				System.out.println("please enter correct id");
			}

		} catch (Exception ex) {
			System.out.println("error is :" + ex);
//			return false;
		}

		return value > 0 ? true : false;

	}

	public boolean deletemoviedetails(int id) {
		int value = -1;
		try {
			if (id > 0) {
				stmt = conn.prepareStatement("select mov_id from moviemaster where mov_id=?");
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				while (rs.next()) {
					if (id == rs.getInt(1)) {

						stmt = conn.prepareStatement("delete from moviemaster  where mov_id=?");
						stmt.setInt(1, id);
						value = stmt.executeUpdate();
					} else {
						System.out.println("enter correct id");
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("error is :" + ex);
		}
		return value > 0 ? true : false;
	}

	public boolean assignMovie(String moviename) {
		try {
			if(moviename!=null) {
				stmt=conn.prepareStatement("select mov_name,mov_id from moviemaster where mov_name =?");
				stmt.setString(1, moviename);
			    rs= stmt.executeQuery();
			    if(rs.next()) {
			    	System.out.println("movie is present");
//			    	System.out.println(rs.getInt("mov_id"));
//			    	System.out.println(ClientApplication.uid);
			    	stmt=conn.prepareStatement("insert into movieuserjoin values(?,?)");
			    	stmt.setInt(1,rs.getInt("mov_id"));
			    	stmt.setInt(2, ClientApplication.uid);
			    	int value=stmt.executeUpdate();
			    	return value>0?true:false;
			    	
			    }else {
			    	System.out.println("movie is not present");
			    	return false;
			    }
			    
			}else {
				System.out.println("enter the valid movie name");
				return false;
			}
		}catch(Exception ex) {
			System.out.println("error is :"+ex);
			return false;
		}
		
	}
	
	public LinkedHashMap<Integer,ArrayList<String>> getUserWiseMovieName(){
		try {
			this.map1=new LinkedHashMap <Integer,ArrayList<String>>();
			stmt=conn.prepareStatement("select user_id from Usermaster");
			rs=stmt.executeQuery();
			while(rs.next()) {
				  int userid=rs.getInt(1);
				PreparedStatement stmt1=conn.prepareStatement("select mm.mov_name from moviemaster mm inner join "
						+ "movieuserjoin muj on mm.mov_id=muj.mov_id inner join usermaster um on muj.user_id=um.user_id where um.user_id=?");
			    stmt1.setInt(1, userid);
			     ResultSet rs1=stmt1.executeQuery();
			     ArrayList <String> moviename=new ArrayList<String>();
			     while(rs1.next()) {
			    	 moviename.add(rs1.getString(1));
			    	 
			     }
			     this.map1.put(userid, moviename);
			}
			return map1;
		}catch(Exception ex)
		{
			System.out.println("error is :"+ex);
			return null;
		}
	}

	public  ArrayList<String> getMovieName(int uid) {
		ArrayList <String>watchedmovie=new ArrayList<String>();
		try {
			stmt=conn.prepareStatement("select mm.mov_name from moviemaster mm inner join movieuserjoin muj on mm.mov_id=muj.mov_id inner join usermaster um on muj.user_id=um.user_id where um.user_id=?");
		   stmt.setInt(1, uid);
		   rs=stmt.executeQuery();
		   while(rs.next()) {
			   watchedmovie.add(rs.getString(1));
		   }
		 
		}catch(Exception ex) {
			System.out.println("error is :"+ex);
			return null;
		}
		  return watchedmovie.size()>0?watchedmovie:null;
	}

	public int getMovieId(String watchedmovie) {
		try {
			stmt=conn.prepareStatement("select mov_id from moviemaster where mov_name=?");
			stmt.setString(1, watchedmovie);
			rs=stmt.executeQuery();
			return rs.next()?rs.getInt(1):0;
		}catch(Exception ex) {
			System.out.println("error is :"+ex);
			return 0;
		}
		
	}

}
