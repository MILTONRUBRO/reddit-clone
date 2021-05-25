package br.com.devmos.reddit.models;

import java.time.Instant;

public class Comment {
	
	private Long id;
	private String text;
	private Post post;
	private Instant createDate;
	private User user;

}
