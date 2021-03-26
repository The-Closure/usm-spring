package org.closure.app.commentModule.mapper;

import org.closure.app.commentModule.dto.CommentRequest;
import org.closure.app.commentModule.dto.CommentResponse;
import org.closure.app.entities.CommentEntity;
import org.closure.app.entities.PostEntity;
import org.closure.app.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    
    public static CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "userID", expression = "java(entity.getUEntity().getId())")
    @Mapping(target = "postID", expression = "java(entity.getPEntity().getId())")
    public CommentResponse commentToResponse(CommentEntity entity);

    @Mapping(target = "uEntity", source = "uEntity")
    @Mapping(target = "pEntity", source = "pEntity")
    public CommentEntity responseToComment(CommentRequest request, UserEntity uEntity, PostEntity pEntity);
}
