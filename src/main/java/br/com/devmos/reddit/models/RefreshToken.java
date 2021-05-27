package br.com.devmos.reddit.models;

import java.time.Instant;

public class RefreshToken {
	
    private Long id;
    private String token;
    private Instant createdDate;
    
	public RefreshToken(String token, Instant createdDate) {
		this.token = token;
		this.createdDate = createdDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Instant getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

}
