package org.closure.app.commentModule.mapper;

import org.closure.app.commentModule.dto.CommentRequest;
import org.closure.app.commentModule.dto.CommentResponse;
import org.closure.app.entities.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CommentMapper {
    
    public static CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    // @Mapping(target = "userID", source = "entity.uentity.id")
    @Mapping(target = "postID", expression = "java(entity.getPentity().getId())")
    public abstract CommentResponse commentToResponse(CommentEntity entity);

    // @Mapping(target = "uEntity", source  = "uentity")
   
    public abstract CommentEntity requestToComment(CommentRequest request);
}
