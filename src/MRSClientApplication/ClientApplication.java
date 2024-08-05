package MRSClientApplication;

import java.sql.SQLException;
import java.util.*;

import MRSModel.MovieModel;
import MRSModel.RatingReviewModel;
import MRSModel.UserModel;
import MRSService.MovieService;
import MRSService.RatingReviewService;
import MRSService.UserService;

public class ClientApplication {
	public static int uid;

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		MovieService mservice = new MovieService();
		UserService uservice = new UserService();
		RatingReviewService rrservice = new RatingReviewService();

		do {
			System.out.println("MOVIE RECOMENDATION STSTEM");
			System.out.println("select below option");
			System.out.println("1.Admin🧓\n2.User👤\n3.register here🖋️");
			int choice = sc.nextInt();
			boolean exits = false;
			switch (choice) {
			case 1:
				sc.nextLine();
				System.out.println("enter your name and password");
				String adminname = sc.nextLine();
				String adminpassword = sc.nextLine();
				boolean b=rrservice.checkadmin(adminname,adminpassword);
				if (b) {
					System.out.println(" Admin Login successfully..😁");
					do {
						System.out.println("1.add new movies ");
						System.out.println("2.show All movies");
						System.out.println("3.update movie");
						System.out.println("4.delete movie");
						System.out.println("5.show all registred user");
						System.out.println("6.show user_id wise movies");
						System.out.println("7.show the overall rating movie wise");
						System.out.println("8.delete user using its id");
						System.out.println("9.🔙exits");
						System.out.println("ENTER YOUR CHOICE");
						choice = sc.nextInt();
						switch (choice) {
						case 1:
							sc.nextLine();
							System.out.println("Enter the movie name");
							String mname = sc.nextLine();
							System.out.println("Enter movie language");
							String lang = sc.nextLine();
							System.out.println("enter the actore name");
							String actor = sc.nextLine();
							System.out.println("enter the movie year");
							int year = sc.nextInt();
							MovieModel mmodel = new MovieModel(mname, lang, actor, year);
							 b = mservice.isAddMovie(mmodel);
							if (b) {
								System.out.println("movie added successfully...😊");
							} else {
								System.out.println("some problem is there..❌");
							}

							break;
						case 2:
							List<MovieModel> list = mservice.getAllMovies();
							if (list != null) {
								for (MovieModel m : list) {
									System.out.println(m.getMov_id() + "\t" + m.getMov_name() + "\t" + m.getMov_lang()
											+ "\t" + m.getMov_actor() + "\t" + m.getMov_year());
								}
							} else {
								System.out.println("no movie present ❌");
							}
							break;
						case 3:
							System.out.println("enter the id of movie for updation");
							int id = sc.nextInt();
							b = mservice.updatemoviedetails(id);
							System.out.println(b == true ? "details update successfully..😊" : "some problem is there..😒");
							break;
						case 4:
							System.out.println("enter the id of movie for deletion");
							id = sc.nextInt();
							b = mservice.deletemoviedetails(id);
							System.out.println(b == true ? "details delete successfully..😊" : "some problem is there..😒");
							break;
						case 5:
							List<UserModel> userlist = uservice.getRegisteredUser();
							if (userlist != null) {
								for (UserModel u : userlist) {
									System.out.println(u.getUser_id() + "\t" + u.getUsername() + "\t" + u.getPassword()
											+ "\t" + u.getEmail() + "\t" + u.getContact());
								}
							} else {
								System.out.println("there is no registered user present");
							}
							break;
						case 6:
							// show user wise movies
							LinkedHashMap<Integer, ArrayList<String>> map1 = mservice.getUserWiseMovieName();
							Set<Map.Entry<Integer, ArrayList<String>>> set = map1.entrySet();
							for (Map.Entry<Integer, ArrayList<String>> d : set) {

								ArrayList<String> values = d.getValue();
								if (values.size() > 0) {
									System.out.println(d.getKey());
									System.out.println("===============================");
									for (String val : values) {
										System.out.println(val);
									}
									System.out.println("===============================\n\n");
								}

							}
							break;
						case 7:
//							show the overall rating of all movies
						
							LinkedHashMap <String,Float> map=rrservice.GetOverallRating();
							if(map!=null) {
							Set<Map.Entry<String, Float>> data=map.entrySet();
							System.out.println("moviename\tOvertallrating");
							for(Map.Entry<String, Float>d:data)
							{
								System.out.println(d.getKey()+"\t\t"+d.getValue());
							}
							}else {
								System.out.println("There is no such data present");
							}
							break;
						case 8:
							// delete user using its id
							System.out.println("enter the id of user which you want to delete from records");
							int uid = sc.nextInt();

							b = uservice.deleteUserById(uid);
							if (b) {
								System.out.println("user deleted successfully..😊");
							} else {
								System.out.println("some problem is there...😒");
							}
							break;
						case 9:
							exits = true;
							break;
						default:
							System.out.println("invalid input..");
						}

					} while (!exits);

				} else {
					System.out.println("Please enter correct details..");
				}

				break;
			case 2:
				System.out.println("..Wel-Come...");
				sc.nextLine();
				System.out.println("enter your name and password");
				String username = sc.nextLine();
				String userpassword = sc.nextLine();

				uid = uservice.authenticateuser(username, userpassword);

				if (uid > 0) {
					System.out.println(" User Login successfully...");

					boolean exit = false;
					do {
						System.out.println("1.show all movies");
						System.out.println("2.udpate user details here");
						System.out.println("3.watch movie");
						System.out.println("4.recomended top five movies");
						System.out.println("5.see the review of movie");
						System.out.println("6.give rating and review to movie which you watched");
						System.out.println("7.show movie list which user already watched");
						System.out.println("8.🔙exits");
						System.out.println("ENTER YOUR CHOICE");
						choice = sc.nextInt();

						switch (choice) {
						case 1:
							List<MovieModel> list = mservice.getAllMovies();
							if (list != null) {
								for (MovieModel m : list) {
									System.out.println(m.getMov_id() + "\t" + m.getMov_name() + "\t" + m.getMov_lang()
											+ "\t" + m.getMov_actor() + "\t" + m.getMov_year());
								}
							} else {
								System.out.println("no movie present");
							}
							break;
						case 2:
//						update user dertails
							sc.nextLine();
							System.out.println("if you want to update any fields then enter your email..");
							String email = sc.nextLine();
							int value = uservice.updateUserDetails(email);
							System.out.println(
									value > 0 ? "user deatails updated successfully.." : "user details not updated");
							break;
						case 3:
							sc.nextLine();
							System.out.println("enter the movie name which you want to watch");
							String moviename = sc.nextLine();
							 b = mservice.assignMovie(moviename);
							if (b) {
								System.out.println("movie is assign to user successfully..😁");
							} else {
								System.out.println("some problem is there");
							}
							break;
						case 4:
							//recomend top five movie to user 
							LinkedHashMap <String,Float> map=rrservice.GetOverallRating();
							if(map!=null) {
							Set<Map.Entry<String, Float>> data=map.entrySet();
							System.out.println("moviename\tOvertallrating");
							int count =0;
							for(Map.Entry<String, Float>d:data)
							{
								System.out.println(d.getKey()+"\t\t"+d.getValue());
								count ++;
								if(count==5) {
									break;
								}
							}
							}else {
								System.out.println("There is no such data present");
							}
							break;
						case 5 :
							//see review of movie
							sc.nextLine();
							System.out.println("Enter the movie name to see review of movie");
							String movname=sc.nextLine();
							 map=rrservice.GetOverallRating();
							if(map!=null) {
							Set<Map.Entry<String, Float>> data=map.entrySet();
						
							   int flag=1;
							for(Map.Entry<String, Float>d:data)
							{
								if(movname.equalsIgnoreCase(d.getKey())) {
									if(d.getValue()>3) {
										System.out.println("movie was good with rating 😁:"+d.getValue());
									}else if(d.getValue()==3) {
										System.out.println("movie was neutral with rating 😊:"+d.getValue());
									}else {
										System.out.println("movie was not good with rating 😒:"+d.getValue());
									}
									flag=0;
									break;
								}
							
							}if(flag==1) {
								System.out.println("review not found");
							}
							}else {
								System.out.println("There is no such data present");
							}
							break;
						case 6:
							// give rating and review to movie
							sc.nextLine();
							System.out.println("Enter the movie you watched before and gave rating and review to  movie");
							String watchedmovie = sc.nextLine();
							ArrayList<String> watchedmovies = mservice.getMovieName(uid);
							if (watchedmovies != null) {
                                        boolean ispresent=false;
								for (String movie : watchedmovies) {
									if (movie.equals(watchedmovie)) {
										ispresent=true;
										break;
								         }
								}if(ispresent) {
									System.out.println("enter the rating (1 to 5)");
									int rating = sc.nextInt();
									if (rating >= 1 && rating <= 5) {
										sc.nextLine();
										System.out.println("select below option for  text review");
										System.out.println("1.GOOD👍\n2.BAD👎\n3.NEUTRAL😑");
										String review = sc.nextLine();
										if (review.equalsIgnoreCase("good") || review.equalsIgnoreCase("bad")
												|| review.equalsIgnoreCase("neutral")) {
											int movieid = mservice.getMovieId(watchedmovie);
										
											RatingReviewModel rrmodel=new RatingReviewModel();
											rrmodel.setMov_id(movieid);
											rrmodel.setRating(rating);
											rrmodel.setReview(review);
											rrmodel.setUser_id(uid);
											 b=rrservice.AddRatingReview(rrmodel);
											if(b) {
												System.out.println("Thank you for rating and review .....");
											}else {
												System.out.println("some problem is there...");
											}
										} else {
											System.out.println("enter the valid option... ");
										}
									} else {
										System.out.println("enter rating between range 1 to 5");
									}
								}else {
									System.out.println("enter the correct movie name  which you watched..and gave rating ");
								}

							} else {
								System.out.println("there is no movie in watched history");
							}
//							
//							 
							break;
						case 7:
							// show watched movielist of user

							 watchedmovies = mservice.getMovieName(uid);
							if (watchedmovies != null) {
								System.out.println("watched movies of user");
								System.out.println("==============================");
								for (String movie : watchedmovies) {
									System.out.println(movie);
								}
								System.out.println("==============================");
							} else {
								System.out.println("there is no movie in watched history");
							}
							break;
						case 8:
							exit = true;
							break;
						default:
							System.out.println("Invalid input..");
						}

					} while (!exit);
				} else {
					System.out.println(
							"enter correct username and password or if not registered then type 'YES' for register and  type 'NO' if allready registered(check username and password)");
					String option = sc.nextLine();
					if (option.equalsIgnoreCase("yes")) {
						System.out.println("enter the username");
						username = sc.nextLine();
						System.out.println("enter the password");
						userpassword = sc.nextLine();
						System.out.println("enter your email");
						String email = sc.nextLine();
						System.out.println("enter contact number");
						String contact = sc.nextLine();
						String pass=uservice.returnHash(userpassword);
						UserModel umodel = new UserModel(username, pass, email, contact);
						 b = uservice.registerUser(umodel);
						if (b) {
							System.out.println("register successfully...😁");
						} else {
							System.out.println("some problem is there..😒");
						}
					} else {
						System.out.println("please enter correct details for further process");
					}
				}
				break;
			case 3:
				sc.nextLine();
				System.out.println("enter the username");
				username = sc.nextLine();
				System.out.println("enter the password");
				userpassword = sc.nextLine();
				System.out.println("enter your email");
				String email = sc.nextLine();
				System.out.println("enter contact number");
				String contact = sc.nextLine();
				String pass=uservice.returnHash(userpassword);
				UserModel umodel = new UserModel(username, pass, email, contact);
				 b = uservice.registerUser(umodel);
				if (b) {
					System.out.println("register successfully...thank you..😊");
				} else {
					System.out.println("some problem is there..😒");
				}
				break;
			}

		} while (true);

	}
}
