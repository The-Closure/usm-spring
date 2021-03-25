package org.closure.app.postModule.mapper;

import org.closure.app.entities.PostEntity;
import org.closure.app.postModule.dto.PostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {   
    PostMapper INSTANCE = Mappers.getMapper( PostMapper.class );
        
    PostResponse PostToResponse(PostEntity car);
}
