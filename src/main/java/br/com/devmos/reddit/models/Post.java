package br.com.devmos.reddit.models;

import java.time.Instant;

public class Post {
	
	private Long id;
	private String postName;
	private String url;
	private String description;
	private Integer voteCounter;
	private User user;
	private Instant creatDate;
	
	public Post(String postName, String url, String description, Integer voteCounter, User user, Instant creatDate) {
		this.postName = postName;
		this.url = url;
		this.description = description;
		this.voteCounter = voteCounter;
		this.user = user;
		this.creatDate = creatDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getVoteCounter() {
		return voteCounter;
	}

	public void setVoteCounter(Integer voteCounter) {
		this.voteCounter = voteCounter;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Instant getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Instant creatDate) {
		this.creatDate = creatDate;
	}
	
}
