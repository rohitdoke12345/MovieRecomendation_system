package MRSService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import MRSModel.MovieModel;
import MRSRepository.MovieRepository;

public class MovieService {
     MovieRepository movierepo=new MovieRepository();
     public boolean isAddMovie(MovieModel model) {
    	 return movierepo.isAddMovie(model);
     }
     public List<MovieModel> getAllMovies(){
    	 return movierepo.getAllMovies();
     }
     public boolean updatemoviedetails(int id){
    	 return movierepo.updatemoviedetails(id);
     }
     public boolean deletemoviedetails(int id){
    	 return movierepo.deletemoviedetails(id);
     }
	public boolean assignMovie(String moviename) {
		
		return movierepo.assignMovie(moviename);
	}
	
	public LinkedHashMap<Integer,ArrayList<String>> getUserWiseMovieName(){
		return this.movierepo.getUserWiseMovieName();
	}
	public  ArrayList<String> getMovieName(int uid) {
		return movierepo.getMovieName(uid);
	}
	public int getMovieId(String watchedmovie) {
		return movierepo.getMovieId(watchedmovie);
	}

}
