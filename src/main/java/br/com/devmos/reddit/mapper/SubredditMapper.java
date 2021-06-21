package br.com.devmos.reddit.mapper;

import java.util.List;

import org.springframework.web.bind.annotation.Mapping;

import br.com.devmos.reddit.dto.SubredditDto;
import br.com.devmos.reddit.models.Post;
import br.com.devmos.reddit.models.Subreddit;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDto subredditDto);

}
