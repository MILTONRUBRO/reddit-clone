package br.com.devmos.reddit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmos.reddit.models.Comment;
import br.com.devmos.reddit.models.Post;
import br.com.devmos.reddit.models.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPost(Post post);

	List<Comment> findAllByUser(User user);

}
