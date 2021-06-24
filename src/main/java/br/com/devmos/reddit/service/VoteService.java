package br.com.devmos.reddit.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.devmos.reddit.dto.VoteDto;
import br.com.devmos.reddit.exceptions.PostNotFoundException;
import br.com.devmos.reddit.exceptions.SpringRedditException;
import br.com.devmos.reddit.models.Post;
import br.com.devmos.reddit.models.Vote;
import br.com.devmos.reddit.repository.PostRepository;
import br.com.devmos.reddit.repository.VoteRepository;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    
    public VoteService(VoteRepository voteRepository, PostRepository postRepository, AuthService authService) {
		super();
		this.voteRepository = voteRepository;
		this.postRepository = postRepository;
		this.authService = authService;
	}

	@Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }

}
