package org.closure.app.UserModule.mapper;

import org.closure.app.CommunityModule.exceptions.CommunityErrorException;
import org.closure.app.CommunityModule.repositories.CommunityRepo;
import org.closure.app.UserModule.dto.UserRequest;
import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.UserModule.models.UserModel;
import org.closure.app.UserModule.services.ImgService;
import org.closure.app.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper
@Component
public abstract class UserMapper {

    @Autowired
    private CommunityRepo communityRepo;
    
    public static UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );


    public abstract UserResponse userToResponse(UserEntity user);


    public abstract UserModel userToModel(UserEntity user);
    
    public abstract UserEntity modelToUser(UserModel model);

    public UserEntity requestToUser(UserRequest request){
        return new UserEntity().
        withPassword(request.getPassword()).
        withName(request.getName()).
        withEmail(request.getEmail()).
        withImg(ImgService.generateImg(request.getName())).
        withFlag(true).            
        withCommuninty(request.getCommunity() != null ? 
            communityRepo.findById(request.getCommunity()).orElseThrow(
                () -> new CommunityErrorException("no community with this id")
            ) : null
        ).withCommunity_name(request.getCommunity() != null ? 
            communityRepo.findById(request.getCommunity()).orElseThrow(
                () -> new CommunityErrorException("no community with this id")).getName() : null);
   

    }
}
