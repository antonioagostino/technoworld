package model.users;

import java.util.Date;

public class User {

	private int id;
	private String username;
	private String password;
	private String name;
	private String surname;
	private String email;
	private Date birthDate;
	private String securityQuestion;
	private String securityAnswer;

	public User() {}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String name, String surname, String email, Date birthDate, String securityQuestion, String securityAnswer) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.birthDate = birthDate;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
}
