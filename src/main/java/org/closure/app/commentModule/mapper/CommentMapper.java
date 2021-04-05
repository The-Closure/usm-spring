package org.closure.app.commentModule.mapper;

import org.closure.app.commentModule.dto.CommentRequest;
import org.closure.app.commentModule.dto.CommentResponse;
import org.closure.app.entities.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CommentMapper {
    
    public static CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    // @Mapping(target = "userID", source = "entity.uentity.id")
    // @Mapping(target = "postID", source = "entity.pentity.id")
    public abstract CommentResponse commentToResponse(CommentEntity entity);

    // @Mapping(target = "uEntity", source  = "uentity")
    // @Mapping(target = "pEntity", source  = "pentity")
    public abstract CommentEntity requestToComment(CommentRequest request);
}
