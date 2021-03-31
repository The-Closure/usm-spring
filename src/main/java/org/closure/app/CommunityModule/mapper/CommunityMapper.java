package org.closure.app.CommunityModule.mapper;

import org.closure.app.CommunityModule.dto.CommunityResponse;
import org.closure.app.CommunityModule.models.CommunityModel;
import org.closure.app.commentModule.dto.CommentRequest;
import org.closure.app.entities.CommunityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommunityMapper {

    public static CommunityMapper INSTANCE = Mappers.getMapper( CommunityMapper.class );
    
    
    public CommunityResponse communityToResponse(CommunityEntity entity);
    
    public CommunityModel communityToModel(CommunityEntity entity);

    public CommunityEntity modelToCommunity(CommunityModel model);

    public CommunityEntity requestToCommunity(CommentRequest request);
    



}
