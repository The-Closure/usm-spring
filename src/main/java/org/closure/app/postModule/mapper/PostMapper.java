package org.closure.app.postModule.mapper;

import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.entities.PostEntity;
import org.closure.app.likeModule.mapper.LikeMapper;
import org.closure.app.postModule.dto.PostResponse;
import org.closure.app.postModule.repositories.PostRepo;
import org.closure.app.postModule.services.PostService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",uses = LikeMapper.class,injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class PostMapper {   

    // @Autowired
    // PostResponse postResponse;
    @Autowired
    protected PostRepo postRepo;

    @Autowired
    protected UserRepo userRepo;

    @Autowired
    public PostService postService;

    // public static PostMapper mapper = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "postID", expression = "java(post.getId())")
    @Mapping(target = "userID", expression = "java(post.getUEntity().getId())")
    @Mapping(target = "likeState", expression = "java(postService.checkUserLikeOnPost(userID,post.getId()))")
    @Mapping(target = "communityID", expression = "java(post.getPcommuninty().getId())")
    @Mapping(target = "likesCount", expression = "java(postService.getPostLikes(post.getId()))")
    // @Mapping(target = "likes", expression = "java()")
    public abstract PostResponse PostToResponse(PostEntity post,Long userID);
    public boolean checkUserLikeOnPost(Long userID,Long postID)
    {     
        return userRepo.findById(userID).orElseThrow(()-> new UserErrorException("no user with this id")).getLikes().stream().anyMatch((l)-> l.getPentity().getId().equals(postID)&&l.getUentity().getId().equals(userID));
    }
   

}
