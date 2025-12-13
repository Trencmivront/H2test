package data;

public class UserData {
	
	private String url;
 	private String user;
 	private String password;
	
 	public UserData(String url, String user, String password) {
 		this.url = url;
 		this.user = user;
 		this.password = password;
 	}
	
 	public String getUser() {
 		return user;
 	}
 	
 	public String getUrl() {
 		return url;
 	}
 	
}
