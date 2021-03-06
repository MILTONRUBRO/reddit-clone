package br.com.devmos.reddit.mapper;

import org.springframework.web.bind.annotation.Mapping;

import br.com.devmos.reddit.dto.CommentsDto;
import br.com.devmos.reddit.models.Comment;
import br.com.devmos.reddit.models.Post;
import br.com.devmos.reddit.models.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Comment map(CommentsDto commentsDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentsDto mapToDto(Comment comment);

}
