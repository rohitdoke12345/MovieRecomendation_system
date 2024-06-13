package MRSModel;

import java.sql.Date;

public class MovieModel extends UserModel{
	private int mov_id;
	private String mov_name;
	private String mov_actor;

	public String getMov_actor() {
		return mov_actor;
	}

	public void setMov_actor(String mov_actor) {
		this.mov_actor = mov_actor;
	}

	public MovieModel(String mov_name, String mov_lang, String mov_actor, int mov_year) {
		super();

		this.mov_name = mov_name;
		this.mov_lang = mov_lang;
		this.mov_actor = mov_actor;
		this.mov_year = mov_year;
	}

	private String mov_lang;

	private int mov_year;

	public MovieModel() {

	}

	public int getMov_id() {
		return mov_id;
	}

	public void setMov_id(int mov_id) {
		this.mov_id = mov_id;
	}

	public String getMov_name() {
		return mov_name;
	}

	public void setMov_name(String mov_name) {
		this.mov_name = mov_name;
	}

	public String getMov_lang() {
		return mov_lang;
	}

	public void setMov_lang(String mov_lang) {
		this.mov_lang = mov_lang;
	}

	public int getMov_year() {
		return mov_year;
	}

	public void setMov_year(int mov_year) {
		this.mov_year = mov_year;
	}
}
