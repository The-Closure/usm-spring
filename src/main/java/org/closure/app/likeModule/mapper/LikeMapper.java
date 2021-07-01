package org.closure.app.likeModule.mapper;

import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.entities.LikeEntity;
import org.closure.app.entities.UserEntity;
import org.closure.app.likeModule.dto.LikeResponse;
import org.closure.app.likeModule.models.LikeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    
    public static LikeMapper INSTANCE = Mappers.getMapper( LikeMapper.class );
    
    @Mapping(target = "userID", expression = "java(like.getUentity().getId())")
    @Mapping(target = "postID", expression = "java(like.getPentity().getId())")
    public LikeModel likeToModel(LikeEntity like);

    @Mapping(target = "likerName", expression = "java(like.getUentity().getName())")
    @Mapping(target = "likerImg", expression = "java(like.getUentity().getImg())")
    @Mapping(target = "likerId", expression = "java(like.getUentity().getId())")
    public LikeResponse likeToResponse(LikeEntity like);

    
}
