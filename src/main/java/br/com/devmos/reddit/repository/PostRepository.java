package br.com.devmos.reddit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmos.reddit.models.Post;
import br.com.devmos.reddit.models.Subreddit;
import br.com.devmos.reddit.models.User;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findAllBySubreddit(Subreddit subreddit);
	List<Post> findByUser(User user);

}
