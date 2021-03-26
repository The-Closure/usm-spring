package org.closure.app.postModule.mapper;

import org.closure.app.entities.PostEntity;
import org.closure.app.postModule.dto.PostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public abstract class PostMapper {   
   
    public static PostMapper mapper = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "id", target = "postID")
    @Mapping(target = "userID", expression = "java(post.getUEntity().getId())")
    @Mapping(target = "communityID", expression = "java(post.getPcommuninty().getId())")
    public abstract PostResponse PostToResponse(PostEntity post);

    Long getOwnerId(PostEntity post){ return post.getUEntity().getId();}
    Long getCommunityId(PostEntity post){ return post.getPcommuninty().getId();}
}
