package br.com.devmos.reddit.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
	private Long id;
	
    @NotBlank(message = "Post Name cannot be empty or Null")
    private String postName;
    @Nullable
    private String url;
    @Nullable
    @Lob
    private String description;
    private Integer voteCount = 0;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    private Instant createdDate;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Subreddit subreddit;
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
