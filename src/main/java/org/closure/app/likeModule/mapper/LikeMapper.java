package org.closure.app.likeModule.mapper;

import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.UserModule.services.UserService;
import org.closure.app.entities.LikeEntity;
import org.closure.app.entities.UserEntity;
import org.closure.app.likeModule.dto.LikeRequest;
import org.closure.app.likeModule.dto.LikeResponse;
import org.closure.app.likeModule.models.LikeModel;
import org.closure.app.likeModule.repositories.LikeRepo;
import org.closure.app.likeModule.services.LikeService;
import org.closure.app.postModule.repositories.PostRepo;
import org.closure.app.postModule.services.PostService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",uses = LikeMapper.class,injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class LikeMapper {
    
    @Autowired
    PostService postService;

    @Autowired
    UserService userService;


    public static LikeMapper INSTANCE = Mappers.getMapper( LikeMapper.class );
    
    @Mapping(target = "userID", expression = "java(like.getUentity().getId())")
    @Mapping(target = "postID", expression = "java(like.getPentity().getId())")
    public abstract LikeModel likeToModel(LikeEntity like);

    @Mapping(target = "likerName", expression = "java(like.getUentity().getName())")
    @Mapping(target = "likerImg", expression = "java(like.getUentity().getImg())")
    @Mapping(target = "likerId", expression = "java(like.getUentity().getId())")
    public abstract LikeResponse likeToResponse(LikeEntity like);

    @Mapping(target = "uentity", expression="java(userService.getUser(request.getUserID()))")
    @Mapping(target = "pentity", expression="java(postService.getPost(request.getPostID()))")
    public abstract LikeEntity requestToLike(LikeRequest request);

    
}
