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
public abstract class CommentMapper {
    
    public static CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    // @Mapping(target = "userID", expression = "java(entity.getUentity().getId())")
    // @Mapping(target = "postID", expression = "java(entity.getPentity().getId())")
    public abstract CommentResponse commentToResponse(CommentEntity entity);

    // @Mapping(target = "uEntity", expression  = "java(uEntity)")
    // @Mapping(target = "pEntity", expression  = "java(pEntity)")
    public abstract CommentEntity responseToComment(CommentRequest request);
}
