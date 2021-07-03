package br.com.devmos.reddit.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmos.reddit.dto.PostRequest;
import br.com.devmos.reddit.dto.PostResponse;
import br.com.devmos.reddit.exceptions.PostNotFoundException;
import br.com.devmos.reddit.exceptions.SubredditNotFoundException;
import br.com.devmos.reddit.mapper.PostMapper;
import br.com.devmos.reddit.models.Post;
import br.com.devmos.reddit.models.Subreddit;
import br.com.devmos.reddit.models.User;
import br.com.devmos.reddit.repository.PostRepository;
import br.com.devmos.reddit.repository.SubredditRepository;
import br.com.devmos.reddit.repository.UserRepository;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    
    

    public PostService(PostRepository postRepository, SubredditRepository subredditRepository,
			UserRepository userRepository, AuthService authService, PostMapper postMapper) {
		this.postRepository = postRepository;
		this.subredditRepository = subredditRepository;
		this.userRepository = userRepository;
		this.authService = authService;
		this.postMapper = postMapper;
	}

	public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }


}
