package br.com.devmos.reddit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devmos.reddit.models.Post;
import br.com.devmos.reddit.models.User;
import br.com.devmos.reddit.models.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{
	Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);

}
