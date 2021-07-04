package br.com.devmos.reddit.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.devmos.reddit.dto.CommentsDto;
import br.com.devmos.reddit.exceptions.PostNotFoundException;
import br.com.devmos.reddit.mapper.CommentMapper;
import br.com.devmos.reddit.models.Comment;
import br.com.devmos.reddit.models.NotificationEmail;
import br.com.devmos.reddit.models.Post;
import br.com.devmos.reddit.models.User;
import br.com.devmos.reddit.repository.CommentRepository;
import br.com.devmos.reddit.repository.PostRepository;
import br.com.devmos.reddit.repository.UserRepository;

import static java.util.stream.Collectors.toList;


@Service
public class CommentService {
    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    
    public CommentService(PostRepository postRepository, UserRepository userRepository, AuthService authService,
			CommentMapper commentMapper, CommentRepository commentRepository, MailContentBuilder mailContentBuilder,
			MailService mailService) {
		super();
		this.postRepository = postRepository;
		this.userRepository = userRepository;
		this.authService = authService;
		this.commentMapper = commentMapper;
		this.commentRepository = commentRepository;
		this.mailContentBuilder = mailContentBuilder;
		this.mailService = mailService;
	}

	public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(authService.getCurrentUser() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUserName() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).collect(toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }
}
