package zsp_2701170229.bean;
//用户对象
public class User {

	private int id;
	private String username;//用户名
	private String pwd;//密码



	public User(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}
	
	
	public User(){
		
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}




	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
