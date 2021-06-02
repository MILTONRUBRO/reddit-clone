package br.com.devmos.reddit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devmos.reddit.dto.PostRequest;
import br.com.devmos.reddit.exceptions.SubredditNotFoundException;
import br.com.devmos.reddit.mapper.PostMapper;
import br.com.devmos.reddit.models.Subreddit;
import br.com.devmos.reddit.repository.PostRepository;
import br.com.devmos.reddit.repository.SubredditRepository;

@Service
public class PostService {
	
	private final SubredditRepository subredditRepository;
	private final PostRepository postRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
	private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
	public PostService(SubredditRepository subredditRepository, PostRepository postRepository,
			AuthService authService, PostMapper postMapper) {
		this.subredditRepository = subredditRepository;
		this.postRepository = postRepository;
		this.authService = authService;
		this.postMapper = postMapper;
	}


	public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
        logger.info("Save a post " + postRequest.getPostName());
    }


}
