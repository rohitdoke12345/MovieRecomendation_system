package MRSRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import MRSConfig.DBHelper;
import MRSModel.MovieModel;
import MRSModel.UserModel;
import MRSService.BCrypt;

public class UserRepository extends DBHelper {
    
	Scanner sc = new Scanner(System.in);
	
	public boolean registerUser(UserModel umodel) {
		
		try {
			stmt=conn.prepareStatement("insert into usermaster values('0',?,?,?,?)");
			stmt.setString(1, umodel.getUsername());
			stmt.setString(2, umodel.getPassword());
			stmt.setString(3, umodel.getEmail());
			stmt.setString(4, umodel.getContact());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		}catch(Exception ex) {
			System.out.println("error is:"+ex);
			return false;
		}
		
	}
	public List <UserModel>getRegisteredUser()
	{     
		List<UserModel> list = new ArrayList<UserModel>();
		try {
			stmt = conn.prepareStatement("select *from usermaster");
			rs = stmt.executeQuery();
			while (rs.next()) {
				UserModel umodel = new UserModel();
				umodel.setUser_id(rs.getInt(1));
				umodel.setUsername(rs.getString(2));
				umodel.setPassword(rs.getString(3));
				umodel.setEmail(rs.getString(4));
				umodel.setContact(rs.getString(5));
				list.add(umodel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return list.size() > 0 ? list : null;
	}
	public boolean deleteUserById(int uid) {
		try {
			stmt = conn.prepareStatement("delete from usermaster where user_id=?");
			stmt.setInt(1, uid);
			  int value =stmt.executeUpdate();
			  return value>0?true:false;
		}catch(Exception ex) {
			System.out.println("error is :"+ex);
			return false;
		}
	
	}
	
	public int updateUserDetails(String useremail) throws SQLException {
		int value = -1;
		if (useremail != null) {
			
			stmt = conn.prepareStatement("SELECT email FROM UserMaster WHERE email=?");
			stmt.setString(1, useremail);
			rs = stmt.executeQuery();

			if (rs.next()) {
			
				System.out.println("User authenticated successfully");

				int choice;
				do {
					System.out.println("What do you want to update? Please select one of the options below:");
					System.out.println("1. User Name\n2. Change Email\n3. Change Contact");
					System.out.println("Enter your choice:");
					choice = sc.nextInt();

					switch (choice) {
					case 1: {
						System.out.println("Enter new user name:");
						String username = sc.next();
						stmt = conn.prepareStatement("UPDATE UserMaster SET username=? WHERE email=?");
						stmt.setString(1, username);
						stmt.setString(2, useremail);
						value = stmt.executeUpdate();
						System.out.println(value > 0 ? "User name updated successfully" : "User name not updated");
						break;
					}
					case 2: {
						System.out.println("Enter new email:");
						String email = sc.next();
						stmt = conn.prepareStatement("UPDATE UserMaster SET email=? WHERE email=?");
						stmt.setString(1, email);
						stmt.setString(2, useremail);
						value = stmt.executeUpdate();
						System.out.println(value > 0 ? "User email updated successfully" : "User email not updated");
						break;
					}
					case 3: {
						System.out.println("Enter new contact number:");
						String contact = sc.next();
						stmt = conn.prepareStatement("UPDATE UserMaster SET contact=? WHERE email=?");
						stmt.setString(1, contact);
						stmt.setString(2, useremail);
						value = stmt.executeUpdate();
						System.out
								.println(value > 0 ? "User contact updated successfully" : "User contact not updated");
						break;
					}
					default:
						System.out.println("Please enter a valid choice for updating.");
					}

					System.out.println("To continue updating, press 1 (Press any other key to exit)");
					choice = sc.nextInt();
				} while (choice == 1);

			} else {
				System.out.println("Please Enter Correct email");
				System.out.println("if you want to change email press 1(Press any other key to exit)");
				int key = sc.nextInt();
				if (key == 1) {
					System.out.println("Enter your contact number:");
					String contact = sc.next();
					System.out.println("Enter your new eamil:");
					String newemail = sc.next();
					System.out.println("Enter the 4-digit OTP:");
					int otp = sc.nextInt();
					if (otp == 4555) {
						stmt = conn.prepareStatement("select contact from UserMaster where contact=?");
						stmt.setString(1, contact);
						rs = stmt.executeQuery();
						while (rs.next()) {
							String c = rs.getString("contact");
							if (c.equalsIgnoreCase(contact)) {
								stmt = conn.prepareStatement("update UserMaster set email=? where contact=?");
								stmt.setString(1, newemail);
								stmt.setString(2, contact);
								value = stmt.executeUpdate();
								System.out
										.println(value > 0 ? "email updated successfully" : "email not updated");

							} else {
								System.out.println("Incorrect contact number.");
							}
						}
					} else {
						System.out.println("Invalid OTP.");
					}
				}

			}
		} else {
			System.out.println("email cannot be null 😒");
		}
		return value;
	}
	public int authenticateuser(String username, String userpassword) {
		if (username != null && userpassword != null) {
	        try {
	            stmt = conn.prepareStatement("SELECT user_id,password FROM UserMaster WHERE username = ?");
	            
	            stmt.setString(1, username);
	            rs = stmt.executeQuery();
	            if (rs.next()) {
	            	
	                String dbPass = rs.getString("password");
	                return BCrypt.checkpw(userpassword, dbPass)?rs.getInt(1):0;
	            } else {
	                System.out.println("Invalid username or password.");
	                return 0;
	            }
	        } catch (SQLException e) {
	            System.out.println("Error: " + e);
	            return 0;
	        }
	    } else {
	        System.out.println("Please enter correct username and password.");
	        return 0;
	    }
		
		
	}
}
