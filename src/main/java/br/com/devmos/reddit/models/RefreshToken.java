package br.com.devmos.reddit.models;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RefreshToken {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Instant createdDate;
    
	public RefreshToken(String token, Instant createdDate) {
		this.token = token;
		this.createdDate = createdDate;
	}
	public RefreshToken() {
		
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
