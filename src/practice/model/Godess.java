package practice.model;

import java.util.Date;

public class Godess {
	//属性
	private Integer id;
	private String username;
	private String sex;
	private Integer age;
	private Date birthday;
	private String email;
	private String mobile;
	private String create_user;
	private Date create_date;
	private String update_user;
	private Date update_date;
	private boolean isDel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String reate_user) {
		this.create_user = reate_user;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date reate_date) {
		this.create_date = reate_date;
	}


	public boolean isDel() {
		return isDel;
	}

	public void setDel(boolean isDel) {
		this.isDel = isDel;
	}

	public String getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	@Override
	public String toString() {
		return "\nGodess [id=" + id + ", username=" + username + ", sex=" + sex + ", age=" + age + ", \nbirthday="
				+ birthday + ", email=" + email + ", mobile=" + mobile + ", create_user=" + create_user
				+ ", \ncreate_date=" + create_date + ", update_user=" + update_user + ", \nupdate_date=" + update_date
				+ ", isDel=" + isDel + "]\n";
	}
}
