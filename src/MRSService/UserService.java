package MRSService;

import java.sql.SQLException;
import java.util.List;

import MRSModel.UserModel;
import MRSRepository.UserRepository;

public class UserService {
     UserRepository userrepo=new UserRepository();
     
     public boolean registerUser(UserModel umodel) {
    	 return userrepo.registerUser(umodel);
     }
     public List<UserModel>getRegisteredUser(){
    	 return userrepo.getRegisteredUser();
     }
	public boolean deleteUserById(int uid) {
		
		return userrepo.deleteUserById(uid);
	}
	
	public int updateUserDetails(String email) throws SQLException {	
			return userrepo.updateUserDetails(email);
	}
	public int authenticateuser(String username, String userpassword) {
		return userrepo.authenticateuser(username,userpassword);
	}
	public String returnHash(String userpassword) {
		String hashPassword=BCrypt.hashpw(userpassword,BCrypt.gensalt());
		return hashPassword;
	}
}
