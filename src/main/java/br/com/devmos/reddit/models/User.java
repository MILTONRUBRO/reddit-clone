package br.com.devmos.reddit.models;

import java.time.Instant;

public class User {
	
	private Long id;
	private String userName;
	private String password;
	private String email;
	private Instant created;
	private boolean enabled;
	
	public User(String userName, String password, String email, Instant created, boolean enabled) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.created = created;
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
